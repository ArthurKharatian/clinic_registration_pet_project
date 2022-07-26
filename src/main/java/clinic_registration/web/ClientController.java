package clinic_registration.web;


import clinic_registration.dto.ClientDto;
import clinic_registration.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/client")

public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<String> createDoctor(@RequestBody ClientDto clientDto) {
        return clientService.createClient(clientDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> read(@PathVariable("id") Long id) {
        return clientService.readClient(id);
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody ClientDto clientDto) {
        return clientService.updateClient(clientDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        return clientService.deleteClient(id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClientDto>> getAllClients() {
        return clientService.getAllClients();
    }

}
