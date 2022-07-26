package clinic_registration.service;

import clinic_registration.dto.ClinicProcedureDto;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ClinicProcedureService {

    ClinicProcedureDto create(ClinicProcedureDto clinicProcedureDto);

    ClinicProcedureDto read(Long id);

    @Transactional
    ClinicProcedureDto update(ClinicProcedureDto clinicProcedureDto);

    @Transactional
    ClinicProcedureDto delete(Long id);

    List<ClinicProcedureDto> readAll();

    ResponseEntity<String> createProcedure(@RequestBody ClinicProcedureDto clinicProcedureDto);

    ResponseEntity<String> readProcedure(@PathVariable("id") Long id);

    @Transactional
    ResponseEntity<String> updateProcedure(@RequestBody ClinicProcedureDto clinicProcedureDto);

    @Transactional
    ResponseEntity<String> deleteProcedure(@PathVariable("id") Long id);

    ResponseEntity<List<ClinicProcedureDto>> getAllProcedures();

}
