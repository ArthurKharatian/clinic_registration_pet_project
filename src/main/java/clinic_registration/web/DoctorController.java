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
    public ResponseEntity<ServiceMessageDto> addDoctor(@RequestBody Doctor doctor){
        doctorService.create(doctor);
        return new ResponseEntity<>(new ServiceMessageDto(777,
                "Doctor is created!"), HttpStatus.CREATED);
    }

    @PutMapping( headers = "Accept=application/json")
    public ResponseEntity<ServiceMessageDto> update(@RequestBody Doctor doctor){
        doctorService.update(doctor);
        return new ResponseEntity<>(new ServiceMessageDto(555,
                String.format("Doctor with id %d is updated!", doctor.getId())), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ServiceMessageDto> delete(@PathVariable("id") Long id){
        doctorService.delete(id);
        return new ResponseEntity<>(new ServiceMessageDto(666,
                String.format("Doctor with id %d is deleted!", id)), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Doctor>> readAll(){
        return new ResponseEntity<>(doctorService.readAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> read(@PathVariable("id") Long id){
        Doctor doctor = doctorService.read(id);
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }
}
