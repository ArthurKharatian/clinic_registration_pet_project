package clinic_registration.service;

import clinic_registration.dto.DoctorAppointmentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface DoctorAppointmentService {

    DoctorAppointmentDto create(DoctorAppointmentDto appointmentDto);

    DoctorAppointmentDto read(Long id);

    @Transactional
    DoctorAppointmentDto update(DoctorAppointmentDto appointmentDto);

    @Transactional
    DoctorAppointmentDto delete(Long id);

    List<DoctorAppointmentDto> readAll();

    ResponseEntity<String> createAppointment(@RequestBody DoctorAppointmentDto appointmentDto);

    ResponseEntity<String> readAppointment(@PathVariable("id") Long id);

    @Transactional
    ResponseEntity<String> updateAppointment(@RequestBody DoctorAppointmentDto appointmentDto);

    @Transactional
    ResponseEntity<String> deleteAppointment(@PathVariable("id") Long id);

    ResponseEntity<List<DoctorAppointmentDto>> getAllAppointments();

}
