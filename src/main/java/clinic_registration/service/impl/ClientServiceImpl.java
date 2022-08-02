package clinic_registration.service.impl;

import clinic_registration.db.entity.Client;
import clinic_registration.db.entity.Status;
import clinic_registration.db.repository.ClientRepository;
import clinic_registration.dto.ClientDto;
import clinic_registration.exceptions.ClinicServiceException;
import clinic_registration.service.ClientService;
import clinic_registration.utils.JsonConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ObjectMapper objectMapper;
    private static final String EXC_MESSAGE = "Client with id %d is not found";

    @Override
    public ClientDto create(ClientDto clientDto) {
        if (clientDto == null) {
            throw new ClinicServiceException("Client is null");
        }

        Long id = clientDto.getId();
        if (id != null && clientRepository.findById(id).isPresent()) {
            throw new ClinicServiceException(String.format("Client with id %d already exists", id));
        }

        Client client = objectMapper.convertValue(clientDto, Client.class);
        client.setStatus(String.valueOf(Status.CREATED));
        Client save = clientRepository.save(client);
        return objectMapper.convertValue(save, ClientDto.class);
    }

    @Override
    public ClientDto read(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() ->
                new ClinicServiceException(String.format(EXC_MESSAGE, id)));
        return objectMapper.convertValue(client, ClientDto.class);
    }

    @Override
    public ClientDto update(ClientDto clientDto) {
        Long id = clientDto.getId();
        if (id == null) {
            throw new ClinicServiceException("id is null");
        }

        read(id);

        Client client = objectMapper.convertValue(clientDto, Client.class);
        client.setStatus(String.valueOf(Status.UPDATED));
        Client save = clientRepository.save(client);
        return objectMapper.convertValue(save, ClientDto.class);
    }

    @Override
    public ClientDto delete(Long id) {
        Client client = objectMapper.convertValue(read(id), Client.class);
        client.setStatus(String.valueOf(Status.DELETED));
        clientRepository.save(client);
        return objectMapper.convertValue(client, ClientDto.class);
    }

    @Override
    public List<ClientDto> readAll() {
        return clientRepository.findAll().stream()
                .map(client -> objectMapper.convertValue(client, ClientDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<String> createClient(ClientDto clientDto) {
        if (clientDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client is not created! Body is null");
        }

        ClientDto client = create(clientDto);
        String dto = JsonConverter.getString(client, objectMapper);

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @Override
    public ResponseEntity<String> readClient(Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client is not found! Id is null");
        }

        ClientDto client = read(id);
        String dto = JsonConverter.getString(client, objectMapper);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateClient(ClientDto clientDto) {
        if (clientDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client is not updated! Body is null");
        }

        ClientDto client = update(clientDto);
        String dto = JsonConverter.getString(client, objectMapper);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @Override
    public ResponseEntity<String> deleteClient(Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client assignment is not deleted! Id is null");
        }

        ClientDto client = delete(id);
        String dto = JsonConverter.getString(client, objectMapper);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @Override
    public ResponseEntity<List<ClientDto>> getAllClients() {
        return ResponseEntity.ok(readAll());
    }

}
