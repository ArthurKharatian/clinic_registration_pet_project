package clinic_registration.web;

import clinic_registration.dto.SignToProcedure;
import clinic_registration.services.SignToProcedureService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/signToProcedure")
public class SignToProcedureController {
    private final SignToProcedureService procedureService;

    public SignToProcedureController(SignToProcedureService procedureService) {
        this.procedureService = procedureService;
    }
    @PostMapping
    public String addClient(@RequestBody SignToProcedure sign){
        return procedureService.create(sign);
    }

    @PutMapping(value = "/{id}", headers = "Accept=application/json")
    public String update(@PathVariable Long id, @RequestBody SignToProcedure sign){
        return procedureService.update(id, sign);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        return procedureService.delete(id);
    }

    @GetMapping("/all")
    public List<SignToProcedure> readAll(){
        return procedureService.readAll();
    }

    @GetMapping("/{id}")
    public String read(@PathVariable("id") Long id){
        return procedureService.read(id);
    }
}
