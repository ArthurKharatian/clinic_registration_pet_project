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
    public ResponseEntity<ProcedureAssignment> addAppointment(@RequestBody ProcedureAssignment procedure){
        procedureService.create(procedure);
        return new ResponseEntity<>(procedure, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", headers = "Accept=application/json")
    public ResponseEntity<ProcedureAssignment> update(@PathVariable Long id, @RequestBody ProcedureAssignment procedure){
        procedureService.update(id, procedure);
        return new ResponseEntity<>(procedure, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProcedureAssignment> delete(@PathVariable("id") Long id){
        procedureService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<ProcedureAssignment> readAll(){
        return procedureService.readAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProcedureAssignment> read(@PathVariable("id") Long id){
        ProcedureAssignment procedure = procedureService.read(id);
        return new ResponseEntity<>(procedure, HttpStatus.OK);
    }
}
