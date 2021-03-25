package clinic_registration.services;

import clinic_registration.db.entity.ClinicBranchEntity;
import clinic_registration.db.repository.ClinicBranchRepository;
import clinic_registration.dto.ClinicBranch;
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
    private static final String EXC_MESSAGE = "The branch with id %d is not found";

    public ClinicBranchService(ClinicBranchRepository branchRepository, ObjectMapper objectMapper) {
        this.branchRepository = branchRepository;
        this.objectMapper = objectMapper;
    }

    public void create(ClinicBranch brach) {
        if(brach == null){throw new ClinicServiceException("brach is null", ErrorMessage.BAD_REQUEST);}
        ClinicBranchEntity branchEntity = objectMapper.convertValue(brach, ClinicBranchEntity.class);
        branchRepository.save(branchEntity);
    }

    public List<ClinicBranch> readAll() {
        List<ClinicBranchEntity> branches = branchRepository.findAll();
        return branches.stream().map(branch -> objectMapper.convertValue(branch, ClinicBranch.class)).collect(Collectors.toList());
    }

    public ClinicBranch read(Long id) {
        ClinicBranchEntity branchEntity = branchRepository.findById(id).orElseThrow(()->
                new ClinicServiceException(String.format(EXC_MESSAGE, id), ErrorMessage.NOT_FOUND));
        return objectMapper.convertValue(branchEntity, ClinicBranch.class);
    }

    public void update(ClinicBranch brach) {
        if (!branchRepository.existsById(brach.getId())) {
            throw new ClinicServiceException(String.format(EXC_MESSAGE, brach.getId()), ErrorMessage.NOT_FOUND);
        }
        branchRepository.save(objectMapper.convertValue(brach, ClinicBranchEntity.class));

    }
    public void delete(Long id) {
        if(!branchRepository.existsById(id)){throw new ClinicServiceException(String.format(EXC_MESSAGE, id), ErrorMessage.NOT_FOUND);}
        branchRepository.deleteById(id);
    }
}
