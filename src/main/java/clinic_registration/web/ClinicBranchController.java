package clinic_registration.web;

import clinic_registration.dto.ClinicBrach;
import clinic_registration.services.ClinicBranchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clinic")
public class ClinicBranchController {
    private final ClinicBranchService branchService;

    public ClinicBranchController(ClinicBranchService branchService) {
        this.branchService = branchService;
    }

    @PostMapping
    public String addClient(@RequestBody ClinicBrach branch){
        return branchService.create(branch);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, ClinicBrach branch){
        return branchService.update(id, branch);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        return branchService.delete(id);
    }

    @GetMapping("/all")
    public List<ClinicBrach> readAll(){
        return branchService.readAll();
    }

    @GetMapping("/{id}")
    public ClinicBrach read(@PathVariable("id") Long id){
        return branchService.read(id);
    }
}

