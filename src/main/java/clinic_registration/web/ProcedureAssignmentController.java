package clinic_registration.web;

import clinic_registration.dto.ProcedureAssignment;
import clinic_registration.services.ProcedureAssignmentService;
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
    public String addClient(@RequestBody ProcedureAssignment sign){
        return procedureService.create(sign);
    }

    @PutMapping(value = "/{id}", headers = "Accept=application/json")
    public String update(@PathVariable Long id, @RequestBody ProcedureAssignment sign){
        return procedureService.update(id, sign);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        return procedureService.delete(id);
    }

    @GetMapping("/all")
    public List<ProcedureAssignment> readAll(){
        return procedureService.readAll();
    }

    @GetMapping("/{id}")
    public String read(@PathVariable("id") Long id){
        return procedureService.read(id);
    }
}
