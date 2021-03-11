package clinic_registration.services;

import clinic_registration.db.entity.AdminEntity;
import clinic_registration.db.entity.ClinicBranchEntity;
import clinic_registration.db.repository.AdminRepository;
import clinic_registration.db.repository.ClinicBranchRepository;
import clinic_registration.dto.Admin;
import clinic_registration.dto.CliniсBranch;
import clinic_registration.exceptions.CreateException;
import clinic_registration.exceptions.DeleteException;
import clinic_registration.exceptions.UpdateException;
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

    public String create(CliniсBranch branch) {
        ClinicBranchEntity branchEntity;
        try {
            branchEntity = objectMapper.convertValue(branch, ClinicBranchEntity.class);
            branchRepository.save(branchEntity);
        } catch (RuntimeException e) {
            throw new CreateException("Can not create a branch");
        }
        return branchEntity.toString() + "is created";
    }

    public List<CliniсBranch> readAll() {
        List<ClinicBranchEntity> branches = branchRepository.findAll();
        return branches.stream().map(br -> objectMapper.convertValue(br, CliniсBranch.class)).collect(Collectors.toList());
    }

    public CliniсBranch read(Long id) {
        CliniсBranch branch = null;
        if (branchRepository.findById(id).isPresent()) {
            ClinicBranchEntity branchEntity = branchRepository.findById(id).get();
            branch = objectMapper.convertValue(branchEntity, CliniсBranch.class);
        }
        return branch;
    }

    public String update(Long id, CliniсBranch branch) {
        if (branchRepository.findById(id).isPresent()) {
            try {
                ClinicBranchEntity branchEntity = objectMapper.convertValue(branch, ClinicBranchEntity.class);
                branchRepository.save(branchEntity);
                return branch.toString() + " is updated!";
            } catch (RuntimeException e) {
                throw new UpdateException("Branch is not found!");
            }
        }
        return "Branch " + branch.toString() + " is not found!";
    }

    public String delete(Long id) {
        try {
            branchRepository.deleteById(id);
            return "Branch with id: " + id + " was deleted!";
        }catch (RuntimeException e) {
            throw new DeleteException("Branch is not found!");
        }
    }
}
