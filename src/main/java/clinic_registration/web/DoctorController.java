package clinic_registration.web;

import clinic_registration.dto.DoctorDto;
import clinic_registration.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping
    public ResponseEntity<String> createDoctor(@RequestBody DoctorDto doctorDto) {
        return doctorService.createDoctor(doctorDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> read(@PathVariable("id") Long id) {
        return doctorService.readDoctor(id);
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody DoctorDto doctorDto) {
        return doctorService.updateDoctor(doctorDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        return doctorService.deleteDoctor(id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<DoctorDto>> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

}
