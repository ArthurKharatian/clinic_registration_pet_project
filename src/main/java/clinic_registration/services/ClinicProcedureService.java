package clinic_registration.services;

import clinic_registration.db.entity.ClinicProcedureEntity;
import clinic_registration.db.repository.ClinicProcedureRepository;
import clinic_registration.dto.ClinicProcedure;
import clinic_registration.exceptions.ClinicServiceException;
import clinic_registration.exceptions.ErrorMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClinicProcedureService {
    private final ClinicProcedureRepository procedureRepository;
    private final ObjectMapper objectMapper;


    public ClinicProcedureService(ClinicProcedureRepository procedureRepository, ObjectMapper objectMapper) {
        this.procedureRepository = procedureRepository;
        this.objectMapper = objectMapper;
    }

    public void create(ClinicProcedure procedure) {
        if(procedure == null){throw new ClinicServiceException("procedure is null", ErrorMessage.BAD_REQUEST);}
        ClinicProcedureEntity procedureEntity = objectMapper.convertValue(procedure, ClinicProcedureEntity.class);
        procedureRepository.save(procedureEntity);
    }

    public List<ClinicProcedure> readAll() {
        List<ClinicProcedureEntity> procedures = procedureRepository.findAll();
        return procedures.stream().map(pr -> objectMapper.convertValue(pr, ClinicProcedure.class)).collect(Collectors.toList());
    }

    public ClinicProcedure read(Long id) {
        ClinicProcedureEntity procedureEntity = procedureRepository.findById(id).orElseThrow(()->
                new ClinicServiceException(String.format("The procedure with id %d is not found", id), ErrorMessage.NOT_FOUND));
        return objectMapper.convertValue(procedureEntity, ClinicProcedure.class);
    }

    public void update(Long id, ClinicProcedure procedure) {
        if (procedureRepository.existsById(id)) {
            procedure.setId(id);
            procedureRepository.save(objectMapper.convertValue(procedure, ClinicProcedureEntity.class));
        } else {
            throw new ClinicServiceException(String.format("The procedure with id %d is not found", id), ErrorMessage.NOT_FOUND);
        }

    }
    public void delete(Long id) {
        if(!procedureRepository.existsById(id)){throw new ClinicServiceException(String.format("The procedure with id %d is not found", id), ErrorMessage.NOT_FOUND);}
        procedureRepository.deleteById(id);
    }

}
