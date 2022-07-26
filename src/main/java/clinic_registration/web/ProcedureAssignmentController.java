package clinic_registration.web;

import clinic_registration.dto.ProcedureAssignmentDto;
import clinic_registration.service.ProcedureAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/signToProcedure")
public class ProcedureAssignmentController {

    private final ProcedureAssignmentService procedureService;

    @PostMapping
    public ResponseEntity<String> createAssignment(@RequestBody ProcedureAssignmentDto procedure) {
        return procedureService.createAssignment(procedure);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> readAssignment(@PathVariable("id") Long id) {
        return procedureService.readAssignment(id);
    }

    @PutMapping
    public ResponseEntity<String> updateAssignment(@RequestBody ProcedureAssignmentDto procedure) {
        return procedureService.updateAssignment(procedure);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAssignment(@PathVariable("id") Long id) {
        return procedureService.deleteAssignment(id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProcedureAssignmentDto>> getAllAssignments() {
        return procedureService.getAllAssignments();
    }

}
