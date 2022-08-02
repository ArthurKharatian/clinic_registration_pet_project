package clinic_registration.service.impl;

import clinic_registration.db.entity.Client;
import clinic_registration.db.entity.Status;
import clinic_registration.db.repository.ClientRepository;
import clinic_registration.dto.ClientDto;
import clinic_registration.exceptions.ClinicServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceImplTest {

    @InjectMocks
    private ClientServiceImpl clientService;

    @Mock
    private ClientRepository clientRepository;

    @Spy
    private ObjectMapper objectMapper;

    @Test
    public void create() {
        Client client = new Client();
        client.setId(1L);
        String status = String.valueOf(Status.CREATED);
        client.setStatus(status);

        when(clientRepository.save(client)).thenReturn(client);

        ClientDto clientDto = new ClientDto();
        clientDto.setId(1L);

        ClientDto result = clientService.create(clientDto);
        verify(clientRepository, times(1)).save(client);
        assertEquals(status, result.getStatus());
    }

    @Test(expected = ClinicServiceException.class)
    public void create_nullDto() {
        clientService.create(null);
    }

    @Test(expected = ClinicServiceException.class)
    public void create_dtoExists() {
        Client client = new Client();
        client.setId(1L);

        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(client));

        ClientDto clientDto = new ClientDto();
        clientDto.setId(1L);
        clientService.create(clientDto);
    }

    @Test
    public void read() {
        Client client = new Client();
        client.setId(1L);

        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        ClientDto result = clientService.read(1L);
        assertEquals(client.getId(), result.getId());

    }

    @Test(expected = ClinicServiceException.class)
    public void read_notFound() {
        clientService.read(1L);
    }

    @Test
    public void update() {
        Client client = new Client();
        client.setId(1L);
        String status = String.valueOf(Status.UPDATED);
        client.setStatus(status);


        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(clientRepository.save(client)).thenReturn(client);

        ClientDto clientDto = new ClientDto();
        clientDto.setId(1L);

        ClientDto result = clientService.update(clientDto);
        verify(clientRepository, times(1)).save(client);
        assertEquals(status, result.getStatus());
    }

    @Test(expected = ClinicServiceException.class)
    public void update_idNull() {
        clientService.update(new ClientDto());
    }

    @Test
    public void delete() {
        Client client = new Client();
        client.setId(1L);
        String status = String.valueOf(Status.DELETED);
        client.setStatus(status);

        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(clientRepository.save(client)).thenReturn(client);

        ClientDto result = clientService.delete(1L);
        verify(clientRepository, times(1)).save(client);
        assertEquals(status, result.getStatus());
    }

    @Test
    public void readAll() {
        List<Client> clients = Arrays.asList(new Client(), new Client());
        when(clientRepository.findAll()).thenReturn(clients);

        List<ClientDto> result = clientService.readAll();
        assertEquals(clients.size(), result.size());
        assertEquals(clients.get(0).getId(), result.get(0).getId());
    }

    @Test
    public void createClient() {
        Client client = new Client();
        client.setId(1L);
        String status = String.valueOf(Status.CREATED);
        client.setStatus(status);

        when(clientRepository.save(client)).thenReturn(client);

        ClientDto clientDto = new ClientDto();
        clientDto.setId(1L);
        ResponseEntity<String> result = clientService.createClient(clientDto);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertNotNull(result.getBody());
        assertTrue(result.getBody().contains(String.valueOf(Status.CREATED)));
    }

    @Test
    public void createClient_bodyNull() {
        ResponseEntity<String> result = clientService.createClient(null);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void readClient() {
        Client client = new Client();
        client.setId(1L);

        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        ResponseEntity<String> result = clientService.readClient(1L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
    }

    @Test
    public void readClient_bodyNull() {
        ResponseEntity<String> result = clientService.readClient(null);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void updateClient() {
        Client client = new Client();
        client.setId(1L);
        String status = String.valueOf(Status.UPDATED);
        client.setStatus(status);


        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(clientRepository.save(client)).thenReturn(client);

        ClientDto clientDto = new ClientDto();
        clientDto.setId(1L);

        ResponseEntity<String> result = clientService.updateClient(clientDto);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertTrue(result.getBody().contains(String.valueOf(Status.UPDATED)));
    }

    @Test
    public void updateClient_bodyNull() {
        ResponseEntity<String> result = clientService.updateClient(null);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void deleteClient() {
        Client client = new Client();
        client.setId(1L);
        String status = String.valueOf(Status.DELETED);
        client.setStatus(status);

        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(clientRepository.save(client)).thenReturn(client);

        ResponseEntity<String> result = clientService.deleteClient(1L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertTrue(result.getBody().contains(String.valueOf(Status.DELETED)));
    }

    @Test
    public void deleteClient_bodyNull() {
        ResponseEntity<String> result = clientService.deleteClient(null);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void getAllClients() {
        List<Client> clients = Arrays.asList(new Client(), new Client());
        when(clientRepository.findAll()).thenReturn(clients);

        ResponseEntity<List<ClientDto>> result = clientService.getAllClients();
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

}