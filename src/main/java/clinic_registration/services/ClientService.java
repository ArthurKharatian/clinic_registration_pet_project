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
        } catch (RuntimeException e) {
            throw new CreateException("Can not create a client");
        }
        return clientEntity.toString() + "is created";
    }

    public List<Client> readAll() {
        List<ClientEntity> clients = clientRepository.findAll();
        return clients.stream().map(cl -> objectMapper.convertValue(cl, Client.class)).collect(Collectors.toList());
    }

    public Client read(Long id) {
        Client client = null;
        if (clientRepository.findById(id).isPresent()) {
            ClientEntity clientEntity = clientRepository.findById(id).get();
            client = objectMapper.convertValue(clientEntity, Client.class);
        }
        return client;
    }

    public String update(Long id, Client client) {
        if (clientRepository.findById(id).isPresent()) {
            try {
                ClientEntity clientEntity = objectMapper.convertValue(client, ClientEntity.class);
                clientRepository.save(clientEntity);
                return client.toString() + " is updated!";
            } catch (RuntimeException e) {
                throw new UpdateException("Client is not found!");
            }
        }
        return "Client " + client.toString() + " is not found!";
    }

    public String delete(Long id) {
        try {
            clientRepository.deleteById(id);
            return "Client with id: " + id + " was deleted!";
        }catch (RuntimeException e) {
                throw new DeleteException("Client is not found!");
            }
    }

}
