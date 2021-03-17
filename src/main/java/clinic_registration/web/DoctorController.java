package clinic_registration.web;

import clinic_registration.dto.Doctor;
import clinic_registration.services.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Doctor> addClient(@RequestBody Doctor doctor){
        doctorService.create(doctor);
        return new ResponseEntity<>(doctor, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", headers = "Accept=application/json")
    public ResponseEntity<Doctor> update(@PathVariable Long id, @RequestBody Doctor doctor){
        doctorService.update(id, doctor);
        return new ResponseEntity<>(doctor, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Doctor> delete(@PathVariable("id") Long id){
        doctorService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<Doctor> readAll(){
        return doctorService.readAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> read(@PathVariable("id") Long id){
        Doctor doctor = doctorService.read(id);
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }
}
