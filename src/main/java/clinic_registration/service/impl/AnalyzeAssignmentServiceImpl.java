package clinic_registration.service.impl;

import clinic_registration.db.entity.AnalyzeAssignment;
import clinic_registration.db.entity.Status;
import clinic_registration.db.repository.AnalyzeAssignmentEntityRepository;
import clinic_registration.dto.AnalyzeAssignmentDto;
import clinic_registration.exceptions.ClinicServiceException;
import clinic_registration.service.AnalyzeAssignmentService;
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
public class AnalyzeAssignmentServiceImpl implements AnalyzeAssignmentService {

    private final AnalyzeAssignmentEntityRepository analyzeRepository;
    private final ObjectMapper objectMapper;
    private static final String EXC_MESSAGE = "Analyze with id %d is not found";

    @Override
    public AnalyzeAssignmentDto create(AnalyzeAssignmentDto analyzeAssignmentDto) {
        if (analyzeAssignmentDto == null) {
            throw new ClinicServiceException("Analyze is null");
        }

        Long id = analyzeAssignmentDto.getId();
        if (id != null && analyzeRepository.findById(id).isPresent()) {
            throw new ClinicServiceException(String.format("Analyze with id %d already exists", id));
        }

        AnalyzeAssignment analyze = objectMapper.convertValue(analyzeAssignmentDto, AnalyzeAssignment.class);
        analyze.setStatus(String.valueOf(Status.CREATED));
        analyzeRepository.save(analyze);
        return analyzeAssignmentDto;
    }

    @Override
    public AnalyzeAssignmentDto read(Long id) {
        AnalyzeAssignment analyze = analyzeRepository.findById(id).orElseThrow(() ->
                new ClinicServiceException(String.format(EXC_MESSAGE, id)));
        return objectMapper.convertValue(analyze, AnalyzeAssignmentDto.class);
    }

    @Override
    public AnalyzeAssignmentDto update(AnalyzeAssignmentDto analyzeAssignmentDto) {
        Long doctorId = analyzeAssignmentDto.getId();
        if (doctorId == null || analyzeRepository.findById(doctorId).isEmpty()) {
            throw new ClinicServiceException(String.format(EXC_MESSAGE, doctorId));
        }
        AnalyzeAssignment analyze = objectMapper.convertValue(analyzeAssignmentDto, AnalyzeAssignment.class);
        analyze.setStatus(String.valueOf(Status.UPDATED));
        analyzeRepository.save(analyze);
        return analyzeAssignmentDto;
    }

    @Override
    public AnalyzeAssignmentDto delete(Long id) {
        AnalyzeAssignment analyze = analyzeRepository.findById(id).orElseThrow(() ->
                new ClinicServiceException(String.format(EXC_MESSAGE, id)));
        analyze.setStatus(String.valueOf(Status.DELETED));
        analyzeRepository.save(analyze);
        return objectMapper.convertValue(analyze, AnalyzeAssignmentDto.class);
    }

    @Override
    public List<AnalyzeAssignmentDto> readAll() {
        List<AnalyzeAssignment> analyzes = analyzeRepository.findAll();
        return analyzes.stream().map(analyze ->
                        objectMapper.convertValue(analyze, AnalyzeAssignmentDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<String> createAnalyze(AnalyzeAssignmentDto analyzeAssignmentDto) {
        if (analyzeAssignmentDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Analyze is not created! Body is null");
        }

        AnalyzeAssignmentDto analyze = create(analyzeAssignmentDto);
        String dto = JsonConverter.getString(analyze, objectMapper);

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @Override
    public ResponseEntity<String> readAnalyze(Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Analyze is not found! Id is null");
        }

        AnalyzeAssignmentDto analyze = read(id);
        String dto = JsonConverter.getString(analyze, objectMapper);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateAnalyze(AnalyzeAssignmentDto analyzeAssignmentDto) {
        if (analyzeAssignmentDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Analyze is not updated! Body is null");
        }

        AnalyzeAssignmentDto analyze = update(analyzeAssignmentDto);
        String dto = JsonConverter.getString(analyze, objectMapper);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @Override
    public ResponseEntity<String> deleteAnalyze(Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Analyze assignment is not deleted! Id is null");
        }

        AnalyzeAssignmentDto analyze = delete(id);
        String dto = JsonConverter.getString(analyze, objectMapper);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @Override
    public ResponseEntity<List<AnalyzeAssignmentDto>> getAllAnalyzes() {
        return ResponseEntity.ok(readAll());
    }

}
