package clinic_registration.web;


import clinic_registration.dto.Client;
import clinic_registration.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/client")

public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<ServiceMessageDto> addClient(@RequestBody Client client){
        LocalDateTime requestTime = LocalDateTime.now();
        Client clientCreated = clientService.create(client);
        ServiceMessageDto serviceMessageDto = new ServiceMessageDto(777,
                "Client is created!");
        serviceMessageDto.setRequestTime(requestTime);
        serviceMessageDto.setDto(clientCreated);
        return new ResponseEntity<>(serviceMessageDto, HttpStatus.CREATED);
    }

    @PutMapping(headers = "Accept=application/json")
    public ResponseEntity<ServiceMessageDto> update( @RequestBody Client client){
        clientService.update(client);
        return new ResponseEntity<>(new ServiceMessageDto(555,
                String.format("Client with id %d is updated!", client.getId())), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ServiceMessageDto> delete(@PathVariable("id") Long id){
        clientService.delete(id);
        return new ResponseEntity<>(new ServiceMessageDto(666,
                String.format("Client with id %d is deleted!", id)), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Client>>readAll(){
        return new ResponseEntity<>(clientService.readAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> read(@PathVariable("id") Long id){
        Client client = clientService.read(id);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

}
