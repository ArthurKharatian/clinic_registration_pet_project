package clinic_registration.services;

import clinic_registration.db.entity.ProcedureAssignmentEntity;
import clinic_registration.db.repository.ProcedureAssignmentRepository;
import clinic_registration.dto.ProcedureAssignment;
import clinic_registration.exceptions.ClinicServiceException;
import clinic_registration.exceptions.ErrorMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProcedureAssignmentService {
    private final ProcedureAssignmentRepository procedureRepository;
    private final ObjectMapper objectMapper;
    private static final String EXC_MESSAGE = "The procedure with id %d is not found";

    public ProcedureAssignmentService(ProcedureAssignmentRepository procedureRepository, ObjectMapper objectMapper) {
        this.procedureRepository = procedureRepository;
        this.objectMapper = objectMapper;
    }

    public void create(ProcedureAssignment procedure) {
        if(procedure == null){throw new ClinicServiceException("procedure is null", ErrorMessage.BAD_REQUEST);}
        ProcedureAssignmentEntity appointmentEntity = objectMapper.convertValue(procedure, ProcedureAssignmentEntity.class);
        procedureRepository.save(appointmentEntity);
    }

    public List<ProcedureAssignment> readAll() {
        List<ProcedureAssignmentEntity> procedures = procedureRepository.findAll();
        return procedures.stream().map(pr -> objectMapper.convertValue(pr, ProcedureAssignment.class)).collect(Collectors.toList());
    }

    public ProcedureAssignment read(Long id) {
        ProcedureAssignmentEntity procedureEntity = procedureRepository.findById(id).orElseThrow(()->
                new ClinicServiceException(String.format(EXC_MESSAGE, id), ErrorMessage.NOT_FOUND));
        return objectMapper.convertValue(procedureEntity, ProcedureAssignment.class);
    }
    @Transactional
    public void update(ProcedureAssignment procedure) {
        if (!procedureRepository.existsById(procedure.getId())) {
            throw new ClinicServiceException(String.format(EXC_MESSAGE, procedure.getId()), ErrorMessage.NOT_FOUND);
        }
        procedureRepository.save(objectMapper.convertValue(procedure, ProcedureAssignmentEntity.class));
    }
    @Transactional
    public void delete(Long id) {
        if(!procedureRepository.existsById(id)){throw new ClinicServiceException(String.format(EXC_MESSAGE, id), ErrorMessage.NOT_FOUND);}
        procedureRepository.deleteById(id);
    }
}
