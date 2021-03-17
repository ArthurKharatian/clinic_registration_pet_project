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

    public String create(ClinicBrach branch) {
        ClinicBranchEntity branchEntity;
        try {
            branchEntity = objectMapper.convertValue(branch, ClinicBranchEntity.class);
            branchRepository.save(branchEntity);
        } catch (RuntimeException e) {
            // TODO: 3/16/21 what if its other RuntimeException Child (any of its 832 children)???
            throw new ClinicServiceException("Can not create a branch");
        }
        return branchEntity.toString() + "is created";
    }

    public List<ClinicBrach> readAll() {
        List<ClinicBranchEntity> branches = branchRepository.findAll();
        return branches.stream().map(br -> objectMapper.convertValue(br, ClinicBrach.class)).collect(Collectors.toList());
    }

    public ClinicBrach read(Long id) {
        ClinicBrach branch = null;
        if (branchRepository.findById(id).isPresent()) {
            ClinicBranchEntity branchEntity = branchRepository.findById(id).get();
            branch = objectMapper.convertValue(branchEntity, ClinicBrach.class);
        }
        return branch;
    }

    public String update(Long id, ClinicBrach branch) {
        if (branchRepository.findById(id).isPresent()) {

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

            branchRepository.deleteById(id);
            return "Branch with id: " + id + " was deleted!";

    }
}
