package clinic_registration.web;


import clinic_registration.dto.Client;
import clinic_registration.services.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/client")

public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public String addClient(Client  client){
        return clientService.create(client);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Integer id, Client client){
       if(clientService.update(client, id)){
          return "Client " + client.getName() + " with ID: " + id + " is updated!";
       }
       return "Client is not found!";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id){
        return clientService.delete(id);
    }

}
