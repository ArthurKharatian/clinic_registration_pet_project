package clinic_registration.service.impl;

import clinic_registration.db.entity.ClinicProcedure;
import clinic_registration.db.entity.Status;
import clinic_registration.db.repository.ClinicProcedureRepository;
import clinic_registration.dto.ClinicProcedureDto;
import clinic_registration.exceptions.ClinicServiceException;
import clinic_registration.service.ClinicProcedureService;
import clinic_registration.utils.JsonConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClinicProcedureServiceImpl implements ClinicProcedureService {

    private final ClinicProcedureRepository procedureRepository;
    private final ObjectMapper objectMapper;
    private static final String EXC_MESSAGE = "Procedure with id %d is not found";


    @Override
    public ClinicProcedureDto create(ClinicProcedureDto clinicProcedureDto) {
        if (clinicProcedureDto == null) {
            throw new ClinicServiceException("Procedure is null");
        }

        Long id = clinicProcedureDto.getId();
        if (id != null && procedureRepository.findById(id).isPresent()) {
            throw new ClinicServiceException(String.format("Procedure with id %d already exists", id));
        }

        ClinicProcedure procedure = objectMapper.convertValue(clinicProcedureDto, ClinicProcedure.class);
        procedure.setStatus(String.valueOf(Status.CREATED));
        procedureRepository.save(procedure);
        return clinicProcedureDto;
    }

    @Override
    public ClinicProcedureDto read(Long id) {
        ClinicProcedure procedure = procedureRepository.findById(id).orElseThrow(() ->
                new ClinicServiceException(String.format(EXC_MESSAGE, id)));
        return objectMapper.convertValue(procedure, ClinicProcedureDto.class);
    }

    @Override
    public ClinicProcedureDto update(ClinicProcedureDto clinicProcedureDto) {
        Long doctorId = clinicProcedureDto.getId();
        if (doctorId == null || procedureRepository.findById(doctorId).isEmpty()) {
            throw new ClinicServiceException(String.format(EXC_MESSAGE, doctorId));
        }
        ClinicProcedure procedure = objectMapper.convertValue(clinicProcedureDto, ClinicProcedure.class);
        procedure.setStatus(String.valueOf(Status.UPDATED));
        procedureRepository.save(procedure);
        return clinicProcedureDto;
    }

    @Override
    public ClinicProcedureDto delete(Long id) {
        ClinicProcedure procedure = procedureRepository.findById(id).orElseThrow(() ->
                new ClinicServiceException(String.format(EXC_MESSAGE, id)));
        procedure.setStatus(String.valueOf(Status.DELETED));
        procedureRepository.save(procedure);
        return objectMapper.convertValue(procedure, ClinicProcedureDto.class);
    }

    @Override
    public List<ClinicProcedureDto> readAll() {
        List<ClinicProcedure> procedures = procedureRepository.findAll();
        return procedures.stream().map(procedure ->
                        objectMapper.convertValue(procedure, ClinicProcedureDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<String> createProcedure(ClinicProcedureDto clinicProcedureDto) {
        if (clinicProcedureDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Procedure is not created! Body is null");
        }

        ClinicProcedureDto procedure = create(clinicProcedureDto);
        String dto = JsonConverter.getString(procedure, objectMapper);

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @Override
    public ResponseEntity<String> readProcedure(Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Procedure is not found! Id is null");
        }

        ClinicProcedureDto procedure = read(id);
        String dto = JsonConverter.getString(procedure, objectMapper);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateProcedure(ClinicProcedureDto clinicProcedureDto) {
        if (clinicProcedureDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Procedure is not updated! Body is null");
        }

        ClinicProcedureDto procedure = update(clinicProcedureDto);
        String dto = JsonConverter.getString(procedure, objectMapper);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @Override
    public ResponseEntity<String> deleteProcedure(Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Procedure is not deleted! Id is null");
        }

        ClinicProcedureDto procedure = delete(id);
        String dto = JsonConverter.getString(procedure, objectMapper);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @Override
    public ResponseEntity<List<ClinicProcedureDto>> getAllProcedures() {
        return ResponseEntity.ok(readAll());
    }
}
