package clinic_registration.service.impl;

import clinic_registration.db.entity.ProcedureAssignment;
import clinic_registration.db.entity.Status;
import clinic_registration.db.repository.ProcedureAssignmentRepository;
import clinic_registration.dto.ProcedureAssignmentDto;
import clinic_registration.exceptions.ClinicServiceException;
import clinic_registration.service.ProcedureAssignmentService;
import clinic_registration.utils.JsonConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProcedureAssignmentServiceImpl implements ProcedureAssignmentService {

    private final ProcedureAssignmentRepository procedureRepository;
    private final ObjectMapper objectMapper;
    private static final String EXC_MESSAGE = "Procedure with id %d is not found";

    @Override
    public ProcedureAssignmentDto create(ProcedureAssignmentDto procedure) {
        if (procedure == null) {
            throw new ClinicServiceException("Procedure is null");
        }

        Long id = procedure.getId();
        if (id != null && procedureRepository.findById(id).isPresent()) {
            throw new ClinicServiceException(String.format("Procedure with id %d already exists", id));
        }

        ProcedureAssignment assignment = objectMapper.convertValue(procedure, ProcedureAssignment.class);
        assignment.setStatus(String.valueOf(Status.CREATED));
        ProcedureAssignment save = procedureRepository.save(assignment);
        return objectMapper.convertValue(save, ProcedureAssignmentDto.class);
    }

    @Override
    public ProcedureAssignmentDto read(Long id) {
        ProcedureAssignment assignment = procedureRepository.findById(id).orElseThrow(() ->
                new ClinicServiceException(String.format(EXC_MESSAGE, id)));
        return objectMapper.convertValue(assignment, ProcedureAssignmentDto.class);
    }

    @Override
    public ProcedureAssignmentDto update(ProcedureAssignmentDto procedure) {
        Long id = procedure.getId();
        if (id == null) {
            throw new ClinicServiceException("id is null");
        }

        read(id);

        ProcedureAssignment assignment = objectMapper.convertValue(procedure, ProcedureAssignment.class);
        assignment.setStatus(String.valueOf(Status.UPDATED));
        ProcedureAssignment save = procedureRepository.save(assignment);
        return objectMapper.convertValue(save, ProcedureAssignmentDto.class);
    }

    @Override
    public ProcedureAssignmentDto delete(Long id) {
        ProcedureAssignment assignment = objectMapper.convertValue(read(id), ProcedureAssignment.class);
        assignment.setStatus(String.valueOf(Status.DELETED));
        procedureRepository.save(assignment);
        return objectMapper.convertValue(assignment, ProcedureAssignmentDto.class);
    }

    @Override
    public List<ProcedureAssignmentDto> readAll() {
        return procedureRepository.findAll().stream()
                .map(assignment -> objectMapper.convertValue(assignment, ProcedureAssignmentDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<String> createAssignment(@RequestBody ProcedureAssignmentDto procedure) {
        if (procedure == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Procedure assignment is not created! Body is null");
        }

        ProcedureAssignmentDto assignment = create(procedure);
        String dto = JsonConverter.getString(assignment, objectMapper);

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @Override
    public ResponseEntity<String> readAssignment(@PathVariable("id") Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Procedure assignment is not found! Id is null");
        }

        ProcedureAssignmentDto assignment = read(id);
        String dto = JsonConverter.getString(assignment, objectMapper);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateAssignment(@RequestBody ProcedureAssignmentDto procedure) {
        if (procedure == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Procedure assignment is not updated! Body is null");
        }

        ProcedureAssignmentDto assignment = update(procedure);
        String dto = JsonConverter.getString(assignment, objectMapper);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @Override
    public ResponseEntity<String> deleteAssignment(@PathVariable("id") Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Procedure assignment is not deleted! Id is null");
        }

        ProcedureAssignmentDto assignment = delete(id);
        String dto = JsonConverter.getString(assignment, objectMapper);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @Override
    public ResponseEntity<List<ProcedureAssignmentDto>> getAllAssignments() {
        return ResponseEntity.ok(readAll());
    }
}
