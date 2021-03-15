package clinic_registration.web;


import clinic_registration.dto.Client;
import clinic_registration.services.ClientService;
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
    public String addClient(@RequestBody Client  client){
        return clientService.create(client);
    }

    @PutMapping(value = "/{id}", headers = "Accept=application/json")
    public String update(@PathVariable Long id, @RequestBody Client client){
       return clientService.update(id, client);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        return clientService.delete(id);
    }

    @GetMapping("/all")
    public List<Client> readAll(){
        return clientService.readAll();
    }

    @GetMapping("/{id}")
    public Client read(@PathVariable("id") Long id){
        return clientService.read(id);
    }

}
