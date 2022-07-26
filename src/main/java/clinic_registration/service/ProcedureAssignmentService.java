package clinic_registration.service;

import clinic_registration.dto.ProcedureAssignmentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProcedureAssignmentService {

    ProcedureAssignmentDto create(ProcedureAssignmentDto procedure);

    ProcedureAssignmentDto read(Long id);

    @Transactional
    ProcedureAssignmentDto update(ProcedureAssignmentDto procedure);

    @Transactional
    ProcedureAssignmentDto delete(Long id);

    List<ProcedureAssignmentDto> readAll();

    ResponseEntity<String> createAssignment(@RequestBody ProcedureAssignmentDto procedure);

    ResponseEntity<String> readAssignment(@PathVariable("id") Long id);

    @Transactional
    ResponseEntity<String> updateAssignment(@RequestBody ProcedureAssignmentDto procedure);

    @Transactional
    ResponseEntity<String> deleteAssignment(@PathVariable("id") Long id);

    ResponseEntity<List<ProcedureAssignmentDto>> getAllAssignments();

}
