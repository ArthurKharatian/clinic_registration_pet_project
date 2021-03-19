package clinic_registration.services;

import clinic_registration.db.entity.ClientEntity;
import clinic_registration.db.repository.ClientRepository;
import clinic_registration.dto.Client;
import clinic_registration.exceptions.ClinicServiceException;
import clinic_registration.exceptions.ErrorMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final ObjectMapper objectMapper;


    public ClientService(ClientRepository clientRepository, ObjectMapper objectMapper) {
        this.clientRepository = clientRepository;
        this.objectMapper = objectMapper;
    }

    public void create(Client client) {
        if(client == null){throw new ClinicServiceException("client is null", ErrorMessage.UNKNOWN);}
        ClientEntity clientEntity = objectMapper.convertValue(client, ClientEntity.class);
        clientRepository.save(clientEntity);
    }

    public List<Client> readAll() {
        List<ClientEntity> clients = clientRepository.findAll();
        return clients.stream().map(cl -> objectMapper.convertValue(cl, Client.class)).collect(Collectors.toList());
    }

    public Client read(Long id) {
        ClientEntity clientEntity = clientRepository.findById(id).orElseThrow(()->
                new ClinicServiceException(String.format("Client with id %d is not found", id), ErrorMessage.NOT_FOUND));
        return objectMapper.convertValue(clientEntity, Client.class);
    }

    public void update(Long id, Client client) {
        if (clientRepository.existsById(id)) {
            client.setId(id);
            clientRepository.save(objectMapper.convertValue(client, ClientEntity.class));
        } else {
            throw new ClinicServiceException(String.format("Client with id %d is not found", id), ErrorMessage.NOT_FOUND);
        }

    }
    public void delete(Long id) {
        if(!clientRepository.existsById(id)){throw new ClinicServiceException(String.format("Client with id %d is not found", id), ErrorMessage.NOT_FOUND);}
        clientRepository.deleteById(id);
    }

}
