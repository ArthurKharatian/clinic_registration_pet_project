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
    public ResponseEntity<ClinicProcedure> addProcedure(@RequestBody ClinicProcedure procedure){
        procedureService.create(procedure);
        return new ResponseEntity<>(procedure, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", headers = "Accept=application/json")
    public ResponseEntity<ClinicProcedure> update(@PathVariable Long id, @RequestBody ClinicProcedure procedure){
        procedureService.update(id, procedure);
        return new ResponseEntity<>(procedure, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClinicProcedure> delete(@PathVariable("id") Long id){
        procedureService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<ClinicProcedure> readAll(){
        return procedureService.readAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicProcedure> read(@PathVariable("id") Long id){
        ClinicProcedure procedure = procedureService.read(id);

        return new ResponseEntity<>(procedure, HttpStatus.OK);
    }
}

