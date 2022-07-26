package clinic_registration.web;

import clinic_registration.dto.AnalyzeAssignmentDto;
import clinic_registration.service.AnalyzeAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/signToTest")
public class AnalyzeAssignmentController {

    private final AnalyzeAssignmentService assigmentService;

    @PostMapping
    public ResponseEntity<String> createDoctor(@RequestBody AnalyzeAssignmentDto analyzeAssignmentDto) {
        return assigmentService.createAnalyze(analyzeAssignmentDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> read(@PathVariable("id") Long id) {
        return assigmentService.readAnalyze(id);
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody AnalyzeAssignmentDto analyzeAssignmentDto) {
        return assigmentService.updateAnalyze(analyzeAssignmentDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        return assigmentService.deleteAnalyze(id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AnalyzeAssignmentDto>> getAllAnalyzes() {
        return assigmentService.getAllAnalyzes();
    }

}
