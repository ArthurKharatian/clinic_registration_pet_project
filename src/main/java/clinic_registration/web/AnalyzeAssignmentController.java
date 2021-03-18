package clinic_registration.web;

import clinic_registration.dto.AnalyzeAssignment;
import clinic_registration.services.AnalyzeAssignmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/signToTest")
public class AnalyzeAssignmentController {
    private final AnalyzeAssignmentService assigmentService;

    public AnalyzeAssignmentController(AnalyzeAssignmentService assigmentService) {
        this.assigmentService = assigmentService;
    }

    @PostMapping
    public ResponseEntity<AnalyzeAssignment> addAssigment(@RequestBody AnalyzeAssignment assignment){
        assigmentService.create(assignment);
        return new ResponseEntity<>(assignment, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", headers = "Accept=application/json")
    public ResponseEntity<AnalyzeAssignment> update(@PathVariable Long id, @RequestBody AnalyzeAssignment assignment){
        assigmentService.update(id, assignment);
        return new ResponseEntity<>(assignment, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AnalyzeAssignment> delete(@PathVariable("id") Long id){
        assigmentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<AnalyzeAssignment> readAll(){
        return assigmentService.readAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnalyzeAssignment> read(@PathVariable("id") Long id){
        AnalyzeAssignment assignment = assigmentService.read(id);
        return new ResponseEntity<>(assignment, HttpStatus.OK);
    }
}
