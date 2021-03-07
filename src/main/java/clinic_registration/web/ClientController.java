package clinic_registration.web;


import clinic_registration.dto.Client;
import clinic_registration.services.ClientService;
import org.springframework.web.bind.annotation.*;


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

    @PutMapping("/{id}")
    public String update(@PathVariable Integer id, Client client){
       return clientService.update(id, client);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id){
        return clientService.delete(id);
    }

}
