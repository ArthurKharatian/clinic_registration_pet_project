package clinic_registration.service;

import clinic_registration.dto.ClinicLabDto;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ClinicLabService {

    ClinicLabDto create(ClinicLabDto clinicLabDto);

    ClinicLabDto read(Long id);

    @Transactional
    ClinicLabDto update(ClinicLabDto clinicLabDto);

    @Transactional
    ClinicLabDto delete(Long id);

    List<ClinicLabDto> readAll();

    ResponseEntity<String> createLab(@RequestBody ClinicLabDto clinicLabDto);

    ResponseEntity<String> readLab(@PathVariable("id") Long id);

    @Transactional
    ResponseEntity<String> updateLab(@RequestBody ClinicLabDto clinicLabDto);

    @Transactional
    ResponseEntity<String> deleteLab(@PathVariable("id") Long id);

    ResponseEntity<List<ClinicLabDto>> getAllLabs();

}
