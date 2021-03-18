package clinic_registration.web;

import clinic_registration.dto.DoctorAppointment;
import clinic_registration.services.DoctorAppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/signToDoc")
public class DoctorAppointmentController {
    private final DoctorAppointmentService appointmentService;

    public DoctorAppointmentController(DoctorAppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<DoctorAppointment> addAppointment(@RequestBody DoctorAppointment appointment){
        appointmentService.create(appointment);
        return new ResponseEntity<>(appointment, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", headers = "Accept=application/json")
    public ResponseEntity<DoctorAppointment> update(@PathVariable Long id, @RequestBody DoctorAppointment appointment){
        appointmentService.update(id, appointment);
        return new ResponseEntity<>(appointment, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DoctorAppointment> delete(@PathVariable("id") Long id){
        appointmentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<DoctorAppointment> readAll(){
        return appointmentService.readAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorAppointment> read(@PathVariable("id") Long id){
        DoctorAppointment appointment = appointmentService.read(id);
        return new ResponseEntity<>(appointment, HttpStatus.OK);
    }
}
