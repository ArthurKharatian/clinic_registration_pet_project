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
    private static final String EXC_MESSAGE = "Client with id %d is not found";


    public ClientService(ClientRepository clientRepository, ObjectMapper objectMapper) {
        this.clientRepository = clientRepository;
        this.objectMapper = objectMapper;
    }

    public Client create(Client client) {
        if(client == null){throw new ClinicServiceException("client is null", ErrorMessage.BAD_REQUEST);}
        ClientEntity clientEntity = objectMapper.convertValue(client, ClientEntity.class);
        ClientEntity save = clientRepository.save(clientEntity);
        return objectMapper.convertValue(save, Client.class);
    }

    public List<Client> readAll() {
        List<ClientEntity> clients = clientRepository.findAll();
        return clients.stream().map(cl -> objectMapper.convertValue(cl, Client.class)).collect(Collectors.toList());
    }

    public Client read(Long id) {
        ClientEntity clientEntity = clientRepository.findById(id).orElseThrow(()->
                new ClinicServiceException(String.format(EXC_MESSAGE, id), ErrorMessage.NOT_FOUND));
        return objectMapper.convertValue(clientEntity, Client.class);
    }

    public void update(Client client) {
        if (!clientRepository.existsById(client.getId())) {
            throw new ClinicServiceException(String.format(EXC_MESSAGE, client.getId()), ErrorMessage.NOT_FOUND);
        }
        clientRepository.save(objectMapper.convertValue(client, ClientEntity.class));
    }
    public void delete(Long id) {
        if(!clientRepository.existsById(id)){throw new ClinicServiceException(String.format(EXC_MESSAGE, id), ErrorMessage.NOT_FOUND);}
        clientRepository.deleteById(id);
    }

}
