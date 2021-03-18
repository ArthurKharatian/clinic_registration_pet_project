package clinic_registration.services;

import clinic_registration.db.entity.ClinicProcedureEntity;
import clinic_registration.db.repository.ClinicProcedureRepository;
import clinic_registration.dto.ClinicProcedure;
import clinic_registration.exceptions.ClinicServiceException;
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

    public String create(ClinicProcedure procedure) {
        ClinicProcedureEntity procedureEntity;

            procedureEntity = objectMapper.convertValue(procedure, ClinicProcedureEntity.class);
            procedureRepository.save(procedureEntity);

        return procedureEntity.toString() + "is created";
    }

    public List<ClinicProcedure> readAll() {
        List<ClinicProcedureEntity> procedures = procedureRepository.findAll();
        return procedures.stream().map(procedure -> objectMapper.convertValue(procedure, ClinicProcedure.class)).collect(Collectors.toList());
    }

    public ClinicProcedure read(Long id) {
        ClinicProcedure procedure = null;
        if (procedureRepository.findById(id).isPresent()) {
            ClinicProcedureEntity procedureEntity = procedureRepository.findById(id).get();
            procedure = objectMapper.convertValue(procedureEntity, ClinicProcedure.class);
        }
        return procedure;
    }

    public String update(Long id, ClinicProcedure procedure) {
        if (procedureRepository.findById(id).isPresent()) {

                ClinicProcedureEntity procedureEntity = objectMapper.convertValue(procedure, ClinicProcedureEntity.class);
                procedureRepository.save(procedureEntity);
                return procedure.toString() + " is updated!";

        }
        return "Procedure " + procedure.toString() + " is not found!";
    }

    public String delete(Long id) {

            procedureRepository.deleteById(id);
            return "Procedure with id: " + id + " was deleted!";

    }
}
