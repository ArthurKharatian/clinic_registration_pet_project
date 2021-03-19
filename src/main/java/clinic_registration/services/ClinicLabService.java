package clinic_registration.services;

import clinic_registration.db.entity.ClinicLabEntity;
import clinic_registration.db.repository.ClinicLabRepository;
import clinic_registration.dto.ClinicLab;
import clinic_registration.exceptions.ClinicServiceException;
import clinic_registration.exceptions.ErrorMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClinicLabService {
    private final ClinicLabRepository labRepository;
    private final ObjectMapper objectMapper;


    public ClinicLabService(ClinicLabRepository labRepository, ObjectMapper objectMapper) {
        this.labRepository = labRepository;
        this.objectMapper = objectMapper;
    }
    public void create(ClinicLab brach) {
        if(brach == null){throw new ClinicServiceException("laboratory is null", ErrorMessage.UNKNOWN);}
        ClinicLabEntity labEntity = objectMapper.convertValue(brach, ClinicLabEntity.class);
        labRepository.save(labEntity);
    }

    public List<ClinicLab> readAll() {
        List<ClinicLabEntity> labs = labRepository.findAll();
        return labs.stream().map(lab -> objectMapper.convertValue(lab, ClinicLab.class)).collect(Collectors.toList());
    }

    public ClinicLab read(Long id) {
        ClinicLabEntity labEntity = labRepository.findById(id).orElseThrow(()->
                new ClinicServiceException(String.format("The laboratory with id %d is not found", id), ErrorMessage.NOT_FOUND));
        return objectMapper.convertValue(labEntity, ClinicLab.class);
    }

    public void update(Long id, ClinicLab lab) {
        if (labRepository.existsById(id)) {
            lab.setId(id);
            labRepository.save(objectMapper.convertValue(lab, ClinicLabEntity.class));
        } else {
            throw new ClinicServiceException(String.format("The laboratory with id %d is not found", id), ErrorMessage.NOT_FOUND);
        }

    }
    public void delete(Long id) {
        if(!labRepository.existsById(id)){throw new ClinicServiceException(String.format("The laboratory with id %d is not found", id), ErrorMessage.NOT_FOUND);}
        labRepository.deleteById(id);
    }
}
