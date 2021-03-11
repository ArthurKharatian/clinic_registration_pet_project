package clinic_registration.services;

import clinic_registration.db.entity.ClinicLabEntity;
import clinic_registration.db.repository.ClinicLabRepository;
import clinic_registration.dto.ClinicLab;
import clinic_registration.exceptions.CreateException;
import clinic_registration.exceptions.DeleteException;
import clinic_registration.exceptions.UpdateException;
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

    public String create(ClinicLab laboratory) {
        ClinicLabEntity labEntity;
        try {
            labEntity = objectMapper.convertValue(laboratory, ClinicLabEntity.class);
            labRepository.save(labEntity);
        } catch (RuntimeException e) {
            throw new CreateException("Can not create a laboratory");
        }
        return labEntity.toString() + "is created";
    }

    public List<ClinicLab> readAll() {
        List<ClinicLabEntity> labs = labRepository.findAll();
        return labs.stream().map(lab -> objectMapper.convertValue(lab, ClinicLab.class)).collect(Collectors.toList());
    }

    public ClinicLab read(Long id) {
        ClinicLab lab = null;
        if (labRepository.findById(id).isPresent()) {
            ClinicLabEntity labEntity = labRepository.findById(id).get();
            lab = objectMapper.convertValue(labEntity, ClinicLab.class);
        }
        return lab;
    }

    public String update(Long id, ClinicLab lab) {
        if (labRepository.findById(id).isPresent()) {
            try {
                ClinicLabEntity labEntity = objectMapper.convertValue(lab, ClinicLabEntity.class);
                labRepository.save(labEntity);
                return lab.toString() + " is updated!";
            } catch (RuntimeException e) {
                throw new UpdateException("Laboratory is not found!");
            }
        }
        return "Laboratory " + lab.toString() + " is not found!";
    }

    public String delete(Long id) {
        try {
            labRepository.deleteById(id);
            return "Laboratory with id: " + id + " was deleted!";
        }catch (RuntimeException e) {
            throw new DeleteException("Laboratory is not found!");
        }
    }


}
