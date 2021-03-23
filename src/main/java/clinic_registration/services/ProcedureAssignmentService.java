package clinic_registration.services;

import clinic_registration.db.entity.ProcedureAssignmentEntity;
import clinic_registration.db.repository.ProcedureAssignmentRepository;
import clinic_registration.dto.ProcedureAssignment;
import clinic_registration.exceptions.ClinicServiceException;
import clinic_registration.exceptions.ErrorMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProcedureAssignmentService {
    private final ProcedureAssignmentRepository procedureRepository;
    private final ObjectMapper objectMapper;

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
                new ClinicServiceException(String.format("The procedure with id %d is not found", id), ErrorMessage.NOT_FOUND));
        return objectMapper.convertValue(procedureEntity, ProcedureAssignment.class);
    }

    public void update(Long id, ProcedureAssignment procedure) {
        if (procedureRepository.existsById(id)) {
            procedure.setId(id);
            procedureRepository.save(objectMapper.convertValue(procedure, ProcedureAssignmentEntity.class));
        } else {
            throw new ClinicServiceException(String.format("The procedure with id %d is not found", id), ErrorMessage.NOT_FOUND);
        }

    }
    public void delete(Long id) {
        if(!procedureRepository.existsById(id)){throw new ClinicServiceException(String.format("The procedure with id %d is not found", id), ErrorMessage.NOT_FOUND);}
        procedureRepository.deleteById(id);
    }
}
