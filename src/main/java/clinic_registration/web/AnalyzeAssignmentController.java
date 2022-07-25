package clinic_registration.web;

import clinic_registration.dto.AnalyzeAssignment;
import clinic_registration.dto.ServiceMessageDto;
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
    public ResponseEntity<ServiceMessageDto> addAssigment(@RequestBody AnalyzeAssignment assignment){
        assigmentService.create(assignment);
        return new ResponseEntity<>(new ServiceMessageDto(777,
                "Analyze assignment is created!"), HttpStatus.CREATED);
    }

    @PutMapping(headers = "Accept=application/json")
    public ResponseEntity<ServiceMessageDto> update( @RequestBody AnalyzeAssignment assignment){
        assigmentService.update(assignment);
        return new ResponseEntity<>(new ServiceMessageDto(555,
                String.format("Analyze assignment with id %d is updated!", assignment.getId())), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ServiceMessageDto> delete(@PathVariable("id") Long id){
        assigmentService.delete(id);
        return new ResponseEntity<>(new ServiceMessageDto(666,
                String.format("Analyze assignment with id %d is deleted!", id)), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AnalyzeAssignment>> readAll(){
        return new ResponseEntity<>(assigmentService.readAll(), HttpStatus.OK) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnalyzeAssignment> read(@PathVariable("id") Long id){
        AnalyzeAssignment assignment = assigmentService.read(id);
        return new ResponseEntity<>(assignment, HttpStatus.OK);
    }
}
