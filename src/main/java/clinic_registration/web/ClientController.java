package clinic_registration.web;


import clinic_registration.dto.Client;
import clinic_registration.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/client")

public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<Client> addClient(@RequestBody Client client){
        clientService.create(client);
        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", headers = "Accept=application/json")
    public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody Client client){
        clientService.update(id, client);
        return new ResponseEntity<>(client, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Client> delete(@PathVariable("id") Long id){
        clientService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<Client> readAll(){
        return clientService.readAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> read(@PathVariable("id") Long id){
        Client client = clientService.read(id);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

}
