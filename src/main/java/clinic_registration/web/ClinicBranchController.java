package clinic_registration.web;

import clinic_registration.dto.ClinicBranchDto;
import clinic_registration.service.ClinicBranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clinic")
public class ClinicBranchController {

    private final ClinicBranchService labService;

    @PostMapping
    public ResponseEntity<String> createDoctor(@RequestBody ClinicBranchDto clinicBranchDto) {
        return labService.createBranch(clinicBranchDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> read(@PathVariable("id") Long id) {
        return labService.readBranch(id);
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody ClinicBranchDto clinicBranchDto) {
        return labService.updateBranch(clinicBranchDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        return labService.deleteBranch(id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClinicBranchDto>> getAllBranches() {
        return labService.getAllBranches();
    }

}

