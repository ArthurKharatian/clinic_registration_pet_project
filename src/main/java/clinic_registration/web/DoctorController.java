package clinic_registration.web;

import clinic_registration.dto.Doctor;
import clinic_registration.services.DoctorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public String addClient(@RequestBody Doctor doctor){
        return doctorService.create(doctor);
    }

    @PutMapping(value = "/{id}", headers = "Accept=application/json")
    public String update(@PathVariable Long id, @RequestBody Doctor doctor){
        return doctorService.update(id, doctor);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        return doctorService.delete(id);
    }

    @GetMapping("/all")
    public List<Doctor> readAll(){
        return doctorService.readAll();
    }

    @GetMapping("/{id}")
    public Doctor read(@PathVariable("id") Long id){
        return doctorService.read(id);
    }
}
