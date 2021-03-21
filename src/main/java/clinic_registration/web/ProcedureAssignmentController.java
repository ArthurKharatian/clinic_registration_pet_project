package clinic_registration.web;

import clinic_registration.dto.ProcedureAssignment;
import clinic_registration.services.ProcedureAssignmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/signToProcedure")
public class ProcedureAssignmentController {
    private final ProcedureAssignmentService procedureService;

    public ProcedureAssignmentController(ProcedureAssignmentService procedureService) {
        this.procedureService = procedureService;
    }
    @PostMapping
    public ResponseEntity<ServiceMessageDto> addAppointment(@RequestBody ProcedureAssignment procedure){
        procedureService.create(procedure);
        return new ResponseEntity<>(new ServiceMessageDto(777,
                "Procedure assignment is created!"), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", headers = "Accept=application/json")
    public ResponseEntity<ServiceMessageDto> update(@PathVariable Long id, @RequestBody ProcedureAssignment procedure){
        procedureService.update(id, procedure);
        return new ResponseEntity<>(new ServiceMessageDto(555,
                String.format("Procedure assignment with id %d is updated!", id)), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ServiceMessageDto> delete(@PathVariable("id") Long id){
        procedureService.delete(id);
        return new ResponseEntity<>(new ServiceMessageDto(666,
                String.format("Procedure assignment with id %d is deleted!", id)), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProcedureAssignment>> readAll(){
        return new ResponseEntity<>(procedureService.readAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProcedureAssignment> read(@PathVariable("id") Long id){
        ProcedureAssignment procedure = procedureService.read(id);
        return new ResponseEntity<>(procedure, HttpStatus.OK);
    }
}
