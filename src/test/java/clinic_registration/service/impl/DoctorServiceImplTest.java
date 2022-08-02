package clinic_registration.service.impl;

import clinic_registration.db.entity.Doctor;
import clinic_registration.db.entity.Status;
import clinic_registration.db.repository.DoctorRepository;
import clinic_registration.dto.DoctorDto;
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
public class DoctorServiceImplTest {

    @InjectMocks
    private DoctorServiceImpl doctorService;

    @Mock
    private DoctorRepository doctorRepository;

    @Spy
    private ObjectMapper objectMapper;

    @Test
    public void create() {
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        String status = String.valueOf(Status.CREATED);
        doctor.setStatus(status);

        when(doctorRepository.save(doctor)).thenReturn(doctor);

        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setId(1L);

        DoctorDto result = doctorService.create(doctorDto);
        verify(doctorRepository, times(1)).save(doctor);
        assertEquals(status, result.getStatus());
    }

    @Test(expected = ClinicServiceException.class)
    public void create_nullDto() {
        doctorService.create(null);
    }

    @Test(expected = ClinicServiceException.class)
    public void create_dtoExists() {
        Doctor doctor = new Doctor();
        doctor.setId(1L);

        when(doctorRepository.findById(anyLong())).thenReturn(Optional.of(doctor));

        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setId(1L);
        doctorService.create(doctorDto);
    }

    @Test
    public void read() {
        Doctor doctor = new Doctor();
        doctor.setId(1L);

        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));

        DoctorDto result = doctorService.read(1L);
        assertEquals(doctor.getId(), result.getId());

    }

    @Test(expected = ClinicServiceException.class)
    public void read_notFound() {
        doctorService.read(1L);
    }

    @Test
    public void update() {
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        String status = String.valueOf(Status.UPDATED);
        doctor.setStatus(status);


        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));
        when(doctorRepository.save(doctor)).thenReturn(doctor);

        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setId(1L);

        DoctorDto result = doctorService.update(doctorDto);
        verify(doctorRepository, times(1)).save(doctor);
        assertEquals(status, result.getStatus());
    }

    @Test(expected = ClinicServiceException.class)
    public void update_idNull() {
        doctorService.update(new DoctorDto());
    }

    @Test
    public void delete() {
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        String status = String.valueOf(Status.DELETED);
        doctor.setStatus(status);

        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));
        when(doctorRepository.save(doctor)).thenReturn(doctor);

        DoctorDto result = doctorService.delete(1L);
        verify(doctorRepository, times(1)).save(doctor);
        assertEquals(status, result.getStatus());
    }

    @Test
    public void readAll() {
        List<Doctor> doctors = Arrays.asList(new Doctor(), new Doctor());
        when(doctorRepository.findAll()).thenReturn(doctors);

        List<DoctorDto> result = doctorService.readAll();
        assertEquals(doctors.size(), result.size());
        assertEquals(doctors.get(0).getId(), result.get(0).getId());
    }

    @Test
    public void createDoctor() {
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        String status = String.valueOf(Status.CREATED);
        doctor.setStatus(status);

        when(doctorRepository.save(doctor)).thenReturn(doctor);

        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setId(1L);
        ResponseEntity<String> result = doctorService.createDoctor(doctorDto);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertNotNull(result.getBody());
        assertTrue(result.getBody().contains(String.valueOf(Status.CREATED)));
    }

    @Test
    public void createDoctor_bodyNull() {
        ResponseEntity<String> result = doctorService.createDoctor(null);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void readDoctor() {
        Doctor doctor = new Doctor();
        doctor.setId(1L);

        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));

        ResponseEntity<String> result = doctorService.readDoctor(1L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
    }

    @Test
    public void readDoctor_bodyNull() {
        ResponseEntity<String> result = doctorService.readDoctor(null);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void updateDoctor() {
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        String status = String.valueOf(Status.UPDATED);
        doctor.setStatus(status);


        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));
        when(doctorRepository.save(doctor)).thenReturn(doctor);

        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setId(1L);

        ResponseEntity<String> result = doctorService.updateDoctor(doctorDto);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertTrue(result.getBody().contains(String.valueOf(Status.UPDATED)));
    }

    @Test
    public void updateDoctor_bodyNull() {
        ResponseEntity<String> result = doctorService.updateDoctor(null);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void deleteDoctor() {
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        String status = String.valueOf(Status.DELETED);
        doctor.setStatus(status);

        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));
        when(doctorRepository.save(doctor)).thenReturn(doctor);

        ResponseEntity<String> result = doctorService.deleteDoctor(1L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertTrue(result.getBody().contains(String.valueOf(Status.DELETED)));
    }

    @Test
    public void deleteDoctor_bodyNull() {
        ResponseEntity<String> result = doctorService.deleteDoctor(null);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void getAllDoctors() {
        List<Doctor> doctors = Arrays.asList(new Doctor(), new Doctor());
        when(doctorRepository.findAll()).thenReturn(doctors);

        ResponseEntity<List<DoctorDto>> result = doctorService.getAllDoctors();
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

}