package clinic_registration.services;

import clinic_registration.db.entity.AnalyzeAssignmentEntity;
import clinic_registration.db.repository.AnalyzeAssignmentEntityRepository;
import clinic_registration.dto.AnalyzeAssignment;
import clinic_registration.exceptions.ClinicServiceException;
import clinic_registration.exceptions.ErrorMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnalyzeAssignmentService {
    private final AnalyzeAssignmentEntityRepository testRepository;
    private final ObjectMapper objectMapper;
    private static final String EXC_MESSAGE = "The assignment with id %d is not found";

    public AnalyzeAssignmentService(AnalyzeAssignmentEntityRepository testRepository, ObjectMapper objectMapper) {
        this.testRepository = testRepository;
        this.objectMapper = objectMapper;
    }

    public void create(AnalyzeAssignment assigment) {
        if(assigment == null){throw new ClinicServiceException("assigment is null", ErrorMessage.BAD_REQUEST);}
        AnalyzeAssignmentEntity assignmentEntity = objectMapper.convertValue(assigment, AnalyzeAssignmentEntity.class);
        testRepository.save(assignmentEntity);
    }

    public List<AnalyzeAssignment> readAll() {
        List<AnalyzeAssignmentEntity> assignments = testRepository.findAll();
        return assignments.stream().map(as -> objectMapper.convertValue(as, AnalyzeAssignment.class)).collect(Collectors.toList());
    }

    public AnalyzeAssignment read(Long id) {
        AnalyzeAssignmentEntity assignmentEntity = testRepository.findById(id).orElseThrow(()->
                new ClinicServiceException(String.format(EXC_MESSAGE, id), ErrorMessage.NOT_FOUND));
        return objectMapper.convertValue(assignmentEntity, AnalyzeAssignment.class);
    }

    @Transactional
    public void update(AnalyzeAssignment assignment) {

        if (!testRepository.existsById(assignment.getId())) {
            throw new ClinicServiceException(String.format(EXC_MESSAGE, assignment.getId()), ErrorMessage.NOT_FOUND);
        }
        testRepository.save(objectMapper.convertValue(assignment, AnalyzeAssignmentEntity.class));

    }
    @Transactional
    public void delete(Long id) {
        if(!testRepository.existsById(id)){throw new ClinicServiceException(String.format(EXC_MESSAGE, id), ErrorMessage.NOT_FOUND);}
        testRepository.deleteById(id);
    }
}
