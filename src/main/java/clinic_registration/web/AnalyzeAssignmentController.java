package clinic_registration.web;

import clinic_registration.dto.AnalyzeAssignment;
import clinic_registration.services.AnalyzeAssignmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/signToTest")
public class AnalyzeAssignmentController {
    private final AnalyzeAssignmentService testService;

    public AnalyzeAssignmentController(AnalyzeAssignmentService testService) {
        this.testService = testService;
    }

    @PostMapping
    public String addClient(@RequestBody AnalyzeAssignment test){
        return testService.create(test);
    }

    @PutMapping(value = "/{id}", headers = "Accept=application/json")
    public String update(@PathVariable Long id, @RequestBody AnalyzeAssignment test){
        return testService.update(id, test);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        return testService.delete(id);
    }

    @GetMapping("/all")
    public List<AnalyzeAssignment> readAll(){
        return testService.readAll();
    }

    @GetMapping("/{id}")
    public String read(@PathVariable("id") Long id){
        return testService.read(id);
    }
}
