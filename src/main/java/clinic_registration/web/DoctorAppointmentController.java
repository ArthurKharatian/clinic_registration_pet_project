package clinic_registration.web;

import clinic_registration.dto.DoctorAppointment;
import clinic_registration.dto.ServiceMessageDto;
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
    public ResponseEntity<ServiceMessageDto> addAppointment(@RequestBody DoctorAppointment appointment){
        appointmentService.create(appointment);
        return new ResponseEntity<>(new ServiceMessageDto(777,
                "Appointment to doctor is created!"), HttpStatus.CREATED);
    }

    @PutMapping(headers = "Accept=application/json")
    public ResponseEntity<ServiceMessageDto> update(@RequestBody DoctorAppointment appointment){
        appointmentService.update(appointment);
        return new ResponseEntity<>(new ServiceMessageDto(555,
                String.format("Appointment to doctor with id %d is updated!", appointment.getId())), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ServiceMessageDto> delete(@PathVariable("id") Long id){
        appointmentService.delete(id);
        return new ResponseEntity<>(new ServiceMessageDto(666,
                String.format("Appointment to doctor with id %d is deleted!", id)), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<DoctorAppointment>> readAll(){
        return new ResponseEntity<>(appointmentService.readAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorAppointment> read(@PathVariable("id") Long id){
        DoctorAppointment appointment = appointmentService.read(id);
        return new ResponseEntity<>(appointment, HttpStatus.OK);
    }
}
