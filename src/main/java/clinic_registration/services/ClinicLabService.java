package clinic_registration.services;

import clinic_registration.db.entity.ClinicLabEntity;
import clinic_registration.db.repository.ClinicLabRepository;
import clinic_registration.dto.ClinicLab;
import clinic_registration.exceptions.ClinicServiceException;
import clinic_registration.exceptions.ErrorMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClinicLabService {
    private final ClinicLabRepository labRepository;
    private final ObjectMapper objectMapper;
    private static final String EXC_MESSAGE = "The laboratory with id %d is not found";

    public ClinicLabService(ClinicLabRepository labRepository, ObjectMapper objectMapper) {
        this.labRepository = labRepository;
        this.objectMapper = objectMapper;
    }

    public void create(ClinicLab brach) {
        if (brach == null) {
            throw new ClinicServiceException("laboratory is null", ErrorMessage.BAD_REQUEST);
        }
        ClinicLabEntity labEntity = objectMapper.convertValue(brach, ClinicLabEntity.class);
        labRepository.save(labEntity);
    }

    public List<ClinicLab> readAll() {
        List<ClinicLabEntity> labs = labRepository.findAll();
        return labs.stream().map(lab -> objectMapper.convertValue(lab, ClinicLab.class)).collect(Collectors.toList());
    }

    public ClinicLab read(Long id) {
        ClinicLabEntity labEntity = labRepository.findById(id).orElseThrow(() ->
                new ClinicServiceException(String.format(EXC_MESSAGE, id), ErrorMessage.NOT_FOUND));
        return objectMapper.convertValue(labEntity, ClinicLab.class);
    }
    @Transactional
    public void update(ClinicLab lab) {
        if (!labRepository.existsById(lab.getId())) {
            throw new ClinicServiceException(String.format(EXC_MESSAGE, lab.getId()), ErrorMessage.NOT_FOUND);
        }
        labRepository.save(objectMapper.convertValue(lab, ClinicLabEntity.class));
    }
    @Transactional
    public void delete(Long id) {
        if (!labRepository.existsById(id)) {
            throw new ClinicServiceException(String.format(EXC_MESSAGE, id), ErrorMessage.NOT_FOUND);
        }
        labRepository.deleteById(id);
    }
}
