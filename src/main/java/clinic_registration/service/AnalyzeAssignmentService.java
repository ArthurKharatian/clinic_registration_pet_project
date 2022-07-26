package clinic_registration.service;

import clinic_registration.dto.AnalyzeAssignmentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface AnalyzeAssignmentService {

    AnalyzeAssignmentDto create(AnalyzeAssignmentDto analyzeAssignmentDto);

    AnalyzeAssignmentDto read(Long id);

    @Transactional
    AnalyzeAssignmentDto update(AnalyzeAssignmentDto analyzeAssignmentDto);

    @Transactional
    AnalyzeAssignmentDto delete(Long id);

    List<AnalyzeAssignmentDto> readAll();

    ResponseEntity<String> createAnalyze(@RequestBody AnalyzeAssignmentDto analyzeAssignmentDto);

    ResponseEntity<String> readAnalyze(@PathVariable("id") Long id);

    @Transactional
    ResponseEntity<String> updateAnalyze(@RequestBody AnalyzeAssignmentDto analyzeAssignmentDto);

    @Transactional
    ResponseEntity<String> deleteAnalyze(@PathVariable("id") Long id);

    ResponseEntity<List<AnalyzeAssignmentDto>> getAllAnalyzes();

}
