package clinic_registration.services;

import clinic_registration.db.entity.ClientEntity;
import clinic_registration.db.repository.ClientRepository;
import clinic_registration.dto.Client;
import clinic_registration.exceptions.CreateException;
import clinic_registration.exceptions.DeleteException;
import clinic_registration.exceptions.UpdateException;
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

    public String create(Client client) {
        ClientEntity clientEntity;
        try {
            clientEntity = objectMapper.convertValue(client, ClientEntity.class);
            clientRepository.save(clientEntity);
        } catch (CreateException e) {
            throw new CreateException(e.getMessage());
        }
        return clientEntity.toString() + "is created";
    }

    public List<Client> readAll() {
        List<ClientEntity> clients = clientRepository.findAll();
        return clients.stream().map(cl -> objectMapper.convertValue(cl, Client.class)).collect(Collectors.toList());
    }

    public Client read(Integer id) {
        Client client = null;
        if (clientRepository.findById(id).isPresent()) {
            ClientEntity clientEntity = clientRepository.findClientEntityByQuery(id).get(0);
            client = objectMapper.convertValue(clientEntity, Client.class);
        }
        return client;
    }

    public String update(Integer id, Client client) {
        if (clientRepository.findById(id).isPresent()) {
            try {
                ClientEntity clientEntity = objectMapper.convertValue(client, ClientEntity.class);
                clientRepository.save(clientEntity);
                return client.toString() + " is updated!";
            } catch (UpdateException e) {
                throw new UpdateException(e.getMessage());
            }
        }
        return "Client is not found!";
    }

    public String delete(Integer id) {

//        clientRepository.deleteById(id);
//        return "Client with id: " + id + " was deleted!";

        if (clientRepository.findById(id).isPresent()) {
            try {
                clientRepository.findAll().removeIf(cl -> cl.getId().equals(id));
                return "Client with id: " + id + " was deleted!";
            } catch (DeleteException e) {
                throw new DeleteException(e.getMessage());
            }
        }
        return "Client is not found!";
    }

}
