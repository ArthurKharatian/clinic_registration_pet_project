package clinic_registration.services;

import clinic_registration.db.entity.ClinicBranchEntity;
import clinic_registration.db.repository.ClinicBranchRepository;
import clinic_registration.dto.ClinicBrach;
import clinic_registration.exceptions.ClinicServiceException;
import clinic_registration.exceptions.ErrorMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClinicBranchService {
    private final ClinicBranchRepository branchRepository;
    private final ObjectMapper objectMapper;


    public ClinicBranchService(ClinicBranchRepository branchRepository, ObjectMapper objectMapper) {
        this.branchRepository = branchRepository;
        this.objectMapper = objectMapper;
    }

    public void create(ClinicBrach brach) {
        if(brach == null){throw new ClinicServiceException("brach is null", ErrorMessage.UNKNOWN);}
        ClinicBranchEntity branchEntity = objectMapper.convertValue(brach, ClinicBranchEntity.class);
        branchRepository.save(branchEntity);
    }

    public List<ClinicBrach> readAll() {
        List<ClinicBranchEntity> branches = branchRepository.findAll();
        return branches.stream().map(branch -> objectMapper.convertValue(branch, ClinicBrach.class)).collect(Collectors.toList());
    }

    public ClinicBrach read(Long id) {
        ClinicBranchEntity branchEntity = branchRepository.findById(id).orElseThrow(()->
                new ClinicServiceException(String.format("The branch with id %d is not found", id), ErrorMessage.NOT_FOUND));
        return objectMapper.convertValue(branchEntity, ClinicBrach.class);
    }

    public void update(Long id, ClinicBrach brach) {
        if (branchRepository.existsById(id)) {
            branchRepository.save(objectMapper.convertValue(brach, ClinicBranchEntity.class));
        } else {
            throw new ClinicServiceException(String.format("The branch with id %d is not found", id), ErrorMessage.NOT_FOUND);
        }

    }
    public void delete(Long id) {
        if(!branchRepository.existsById(id)){throw new ClinicServiceException(String.format("The branch with id %d is not found", id), ErrorMessage.NOT_FOUND);}
        branchRepository.deleteById(id);
    }
}
