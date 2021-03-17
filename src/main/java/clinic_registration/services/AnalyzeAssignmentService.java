package clinic_registration.services;

import clinic_registration.db.entity.ClientEntity;
import clinic_registration.db.entity.ClinicBranchEntity;
import clinic_registration.db.entity.ClinicLabEntity;
import clinic_registration.db.entity.AnalyzeAssignmentEntity;
import clinic_registration.db.repository.ClientRepository;
import clinic_registration.db.repository.ClinicBranchRepository;
import clinic_registration.db.repository.ClinicLabRepository;
import clinic_registration.db.repository.AnalyzeAssignmentEntityRepository;
import clinic_registration.dto.AnalyzeAssignment;
import clinic_registration.exceptions.ClinicServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnalyzeAssignmentService {
    private final AnalyzeAssignmentEntityRepository testRepository;
    private final ClientRepository clientRepository;
    private final ClinicLabRepository labRepository;
    private final ClinicBranchRepository branchRepository;
    private final ObjectMapper objectMapper;

    public AnalyzeAssignmentService(AnalyzeAssignmentEntityRepository testRepository, ClientRepository clientRepository,
                                    ClinicLabRepository labRepository, ClinicBranchRepository branchRepository,
                                    ObjectMapper objectMapper) {
        this.testRepository = testRepository;
        this.clientRepository = clientRepository;
        this.labRepository = labRepository;
        this.branchRepository = branchRepository;
        this.objectMapper = objectMapper;
    }

    public String create(AnalyzeAssignment sign) {
        AnalyzeAssignmentEntity signEntity;
        try {
            signEntity = objectMapper.convertValue(sign, AnalyzeAssignmentEntity.class);
            testRepository.save(signEntity);

            ClinicLabEntity labEntity = labRepository.findById(sign.getLab_id()).get();

            ClientEntity clientEntity = clientRepository.findById(sign.getClient_id()).get();
            ClinicBranchEntity branchEntity = branchRepository.findById(labEntity.getBranch_id()).get();
            return "Sign for test: " + signEntity.getName() + " on " + sign.getVisit_date()
                    + " in branch " + branchEntity.getName() + " on the " + branchEntity.getAddress()
                    + " from client " + clientEntity.getName() + " is created.";
        } catch (RuntimeException e) {
            // TODO: 3/16/21 what if its other RuntimeException Child (any of its 832 children)???
            throw new ClinicServiceException("Can not create a sign");
        }
    }

    public List<AnalyzeAssignment> readAll() {
        List<AnalyzeAssignmentEntity> signs = testRepository.findAll();
        return signs.stream().map(s -> objectMapper.convertValue(s, AnalyzeAssignment.class)).collect(Collectors.toList());
    }

    public String read(Long id) {


        AnalyzeAssignmentEntity signEntity = testRepository.findById(id).get();
        AnalyzeAssignment sign = objectMapper.convertValue(signEntity, AnalyzeAssignment.class);

        ClinicLabEntity labEntity = labRepository.findById(sign.getLab_id()).get();

        ClientEntity clientEntity = clientRepository.findById(sign.getClient_id()).get();
        ClinicBranchEntity branchEntity = branchRepository.findById(labEntity.getBranch_id()).get();
        return "Sign for test: " + signEntity.getName() + " on " + sign.getVisit_date()
                + " in branch " + branchEntity.getName() + " on the " + branchEntity.getAddress()
                + " from client " + clientEntity.getName();

    }

    public String update(Long id, AnalyzeAssignment sign) {


                if (testRepository.findById(id).isPresent()) {
                AnalyzeAssignmentEntity signEntity = objectMapper.convertValue(sign, AnalyzeAssignmentEntity.class);
                signEntity.setId(id);
                testRepository.save(signEntity);
                ClinicLabEntity labEntity = labRepository.findById(sign.getLab_id()).get();
                ClientEntity clientEntity = clientRepository.findById(sign.getClient_id()).get();
                ClinicBranchEntity branchEntity = branchRepository.findById(labEntity.getBranch_id()).get();
                return "Sign for test: " + signEntity.getName() + " on " + sign.getVisit_date()
                        + " in branch " + branchEntity.getName() + " on the " + branchEntity.getAddress()
                        + " from client " + clientEntity.getName() + " is updated.";
                }
            } catch (RuntimeException e) {
                throw new UpdateException("Sign is not found!");
            }

        return "Sign " + sign.toString() + " is not found!";
    }

    public String delete(Long id) {

            testRepository.deleteById(id);
            return "Sign with id: " + id + " was deleted!";
        } catch (RuntimeException e) {
            throw new DeleteException("Sign is not found!");
        }
    }


}
