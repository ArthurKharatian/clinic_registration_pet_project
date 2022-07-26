package clinic_registration.service;

import clinic_registration.dto.DoctorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface DoctorService {
    DoctorDto create(DoctorDto doctorDto);

    DoctorDto read(Long id);

    @Transactional
    DoctorDto update(DoctorDto doctorDto);

    @Transactional
    DoctorDto delete(Long id);

    List<DoctorDto> readAll();

    ResponseEntity<String> createDoctor(@RequestBody DoctorDto doctorDto);

    ResponseEntity<String> readDoctor(@PathVariable("id") Long id);

    @Transactional
    ResponseEntity<String> updateDoctor(@RequestBody DoctorDto doctorDto);

    @Transactional
    ResponseEntity<String> deleteDoctor(@PathVariable("id") Long id);

    ResponseEntity<List<DoctorDto>> getAllDoctors();

}
