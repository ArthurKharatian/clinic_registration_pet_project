package clinic_registration.web;

import clinic_registration.dto.ClinicProcedure;
import clinic_registration.services.ClinicProcedureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/procedure")
public class ClinicProcedureController {
    private final ClinicProcedureService procedureService;

    public ClinicProcedureController(ClinicProcedureService procedureService) {
        this.procedureService = procedureService;
    }
    @PostMapping
    public String addClient(@RequestBody ClinicProcedure procedure){
        return procedureService.create(procedure);
    }

    @PutMapping(value = "/{id}", headers = "Accept=application/json")
    public String update(@PathVariable Long id, @RequestBody ClinicProcedure procedure){
        return procedureService.update(id, procedure);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        return procedureService.delete(id);
    }

    @GetMapping("/all")
    public List<ClinicProcedure> readAll(){
        return procedureService.readAll();
    }

    @GetMapping("/{id}")
    public ClinicProcedure read(@PathVariable("id") Long id){
        return procedureService.read(id);
    }

}
