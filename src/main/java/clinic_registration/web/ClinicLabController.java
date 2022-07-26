package clinic_registration.web;

import clinic_registration.dto.ClinicLabDto;
import clinic_registration.service.ClinicLabService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lab")
public class ClinicLabController {

    private final ClinicLabService labService;

    @PostMapping
    public ResponseEntity<String> createDoctor(@RequestBody ClinicLabDto clinicLabDto) {
        return labService.createLab(clinicLabDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> read(@PathVariable("id") Long id) {
        return labService.readLab(id);
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody ClinicLabDto clinicLabDto) {
        return labService.updateLab(clinicLabDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        return labService.deleteLab(id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClinicLabDto>> getAllLabs() {
        return labService.getAllLabs();
    }

}
