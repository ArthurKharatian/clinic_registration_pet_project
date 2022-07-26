package clinic_registration.web;

import clinic_registration.dto.DoctorAppointmentDto;
import clinic_registration.service.DoctorAppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/signToDoc")
public class DoctorAppointmentController {

    private final DoctorAppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<String> createDoctor(@RequestBody DoctorAppointmentDto doctorAppointmentDto) {
        return appointmentService.createAppointment(doctorAppointmentDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> read(@PathVariable("id") Long id) {
        return appointmentService.readAppointment(id);
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody DoctorAppointmentDto doctorAppointmentDto) {
        return appointmentService.updateAppointment(doctorAppointmentDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        return appointmentService.deleteAppointment(id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<DoctorAppointmentDto>> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

}
