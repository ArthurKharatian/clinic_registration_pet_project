package clinic_registration.service.impl;

import clinic_registration.db.entity.DoctorAppointment;
import clinic_registration.db.entity.Status;
import clinic_registration.db.repository.DoctorAppointmentRepository;
import clinic_registration.dto.DoctorAppointmentDto;
import clinic_registration.exceptions.ClinicServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DoctorAppointmentServiceImplTest {

    @InjectMocks
    private DoctorAppointmentServiceImpl appointmentService;

    @Mock
    private DoctorAppointmentRepository appointmentRepository;

    @Spy
    private ObjectMapper objectMapper;

    @Test
    public void create() {
        DoctorAppointment appointment = new DoctorAppointment();
        appointment.setId(1L);
        String status = String.valueOf(Status.CREATED);
        appointment.setStatus(status);

        when(appointmentRepository.save(appointment)).thenReturn(appointment);

        DoctorAppointmentDto appointmentDto = new DoctorAppointmentDto();
        appointmentDto.setId(1L);

        DoctorAppointmentDto result = appointmentService.create(appointmentDto);
        verify(appointmentRepository, times(1)).save(appointment);
        assertEquals(status, result.getStatus());
    }

    @Test(expected = ClinicServiceException.class)
    public void create_nullDto() {
        appointmentService.create(null);
    }

    @Test(expected = ClinicServiceException.class)
    public void create_dtoExists() {
        DoctorAppointment appointment = new DoctorAppointment();
        appointment.setId(1L);

        when(appointmentRepository.findById(anyLong())).thenReturn(Optional.of(appointment));

        DoctorAppointmentDto appointmentDto = new DoctorAppointmentDto();
        appointmentDto.setId(1L);
        appointmentService.create(appointmentDto);
    }

    @Test
    public void read() {
        DoctorAppointment appointment = new DoctorAppointment();
        appointment.setId(1L);

        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appointment));

        DoctorAppointmentDto result = appointmentService.read(1L);
        assertEquals(appointment.getId(), result.getId());

    }

    @Test(expected = ClinicServiceException.class)
    public void read_notFound() {
        appointmentService.read(1L);
    }

    @Test
    public void update() {
        DoctorAppointment appointment = new DoctorAppointment();
        appointment.setId(1L);
        String status = String.valueOf(Status.UPDATED);
        appointment.setStatus(status);


        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appointment));
        when(appointmentRepository.save(appointment)).thenReturn(appointment);

        DoctorAppointmentDto appointmentDto = new DoctorAppointmentDto();
        appointmentDto.setId(1L);

        DoctorAppointmentDto result = appointmentService.update(appointmentDto);
        verify(appointmentRepository, times(1)).save(appointment);
        assertEquals(status, result.getStatus());
    }

    @Test(expected = ClinicServiceException.class)
    public void update_idNull() {
        appointmentService.update(new DoctorAppointmentDto());
    }

    @Test
    public void delete() {
        DoctorAppointment appointment = new DoctorAppointment();
        appointment.setId(1L);
        String status = String.valueOf(Status.DELETED);
        appointment.setStatus(status);

        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appointment));
        when(appointmentRepository.save(appointment)).thenReturn(appointment);

        DoctorAppointmentDto result = appointmentService.delete(1L);
        verify(appointmentRepository, times(1)).save(appointment);
        assertEquals(status, result.getStatus());
    }

    @Test
    public void readAll() {
        List<DoctorAppointment> appointments = Arrays.asList(new DoctorAppointment(), new DoctorAppointment());
        when(appointmentRepository.findAll()).thenReturn(appointments);

        List<DoctorAppointmentDto> result = appointmentService.readAll();
        assertEquals(appointments.size(), result.size());
        assertEquals(appointments.get(0).getId(), result.get(0).getId());
    }

    @Test
    public void createDoctorAppointment() {
        DoctorAppointment appointment = new DoctorAppointment();
        appointment.setId(1L);
        String status = String.valueOf(Status.CREATED);
        appointment.setStatus(status);

        when(appointmentRepository.save(appointment)).thenReturn(appointment);

        DoctorAppointmentDto appointmentDto = new DoctorAppointmentDto();
        appointmentDto.setId(1L);
        ResponseEntity<String> result = appointmentService.createAppointment(appointmentDto);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertNotNull(result.getBody());
        assertTrue(result.getBody().contains(String.valueOf(Status.CREATED)));
    }

    @Test
    public void createDoctorAppointment_bodyNull() {
        ResponseEntity<String> result = appointmentService.createAppointment(null);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void readDoctorAppointment() {
        DoctorAppointment appointment = new DoctorAppointment();
        appointment.setId(1L);

        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appointment));

        ResponseEntity<String> result = appointmentService.readAppointment(1L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
    }

    @Test
    public void readDoctorAppointment_bodyNull() {
        ResponseEntity<String> result = appointmentService.readAppointment(null);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void updateDoctorAppointment() {
        DoctorAppointment appointment = new DoctorAppointment();
        appointment.setId(1L);
        String status = String.valueOf(Status.UPDATED);
        appointment.setStatus(status);


        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appointment));
        when(appointmentRepository.save(appointment)).thenReturn(appointment);

        DoctorAppointmentDto appointmentDto = new DoctorAppointmentDto();
        appointmentDto.setId(1L);

        ResponseEntity<String> result = appointmentService.updateAppointment(appointmentDto);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertTrue(result.getBody().contains(String.valueOf(Status.UPDATED)));
    }

    @Test
    public void updateDoctorAppointment_bodyNull() {
        ResponseEntity<String> result = appointmentService.updateAppointment(null);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void deleteDoctorAppointment() {
        DoctorAppointment appointment = new DoctorAppointment();
        appointment.setId(1L);
        String status = String.valueOf(Status.DELETED);
        appointment.setStatus(status);

        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appointment));
        when(appointmentRepository.save(appointment)).thenReturn(appointment);

        ResponseEntity<String> result = appointmentService.deleteAppointment(1L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertTrue(result.getBody().contains(String.valueOf(Status.DELETED)));
    }

    @Test
    public void deleteDoctorAppointment_bodyNull() {
        ResponseEntity<String> result = appointmentService.deleteAppointment(null);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void getAllDoctorAppointments() {
        List<DoctorAppointment> appointments = Arrays.asList(new DoctorAppointment(), new DoctorAppointment());
        when(appointmentRepository.findAll()).thenReturn(appointments);

        ResponseEntity<List<DoctorAppointmentDto>> result = appointmentService.getAllAppointments();
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

}