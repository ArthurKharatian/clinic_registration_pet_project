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
    public ResponseEntity<ServiceMessageDto> addProcedure(@RequestBody ClinicProcedure procedure){
        procedureService.create(procedure);
        return new ResponseEntity<>(new ServiceMessageDto(777,
                "Procedure is created!"), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", headers = "Accept=application/json")
    public ResponseEntity<ServiceMessageDto> update(@PathVariable Long id, @RequestBody ClinicProcedure procedure){
        procedureService.update(id, procedure);
        return new ResponseEntity<>(new ServiceMessageDto(555,
                String.format("Procedure with id %d is updated!", id)), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ServiceMessageDto> delete(@PathVariable("id") Long id){
        procedureService.delete(id);
        return new ResponseEntity<>(new ServiceMessageDto(666,
                String.format("Procedure with id %d is deleted!", id)), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClinicProcedure>> readAll(){
        return new ResponseEntity<>(procedureService.readAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicProcedure> read(@PathVariable("id") Long id){
        ClinicProcedure procedure = procedureService.read(id);

        return new ResponseEntity<>(procedure, HttpStatus.OK);
    }
}

