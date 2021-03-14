package clinic_registration.services;

import clinic_registration.db.entity.ClientEntity;
import clinic_registration.db.entity.ClinicBranchEntity;
import clinic_registration.db.entity.ClinicLabEntity;
import clinic_registration.db.entity.SignToTestEntity;
import clinic_registration.db.repository.ClientRepository;
import clinic_registration.db.repository.ClinicBranchRepository;
import clinic_registration.db.repository.ClinicLabRepository;
import clinic_registration.db.repository.SignToTestRepository;
import clinic_registration.dto.SignToTest;
import clinic_registration.exceptions.CreateException;
import clinic_registration.exceptions.DeleteException;
import clinic_registration.exceptions.UpdateException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SignToTestService {
    private final SignToTestRepository testRepository;
    private final ClientRepository clientRepository;
    private final ClinicLabRepository labRepository;
    private final ClinicBranchRepository branchRepository;
    private final ObjectMapper objectMapper;

    public SignToTestService(SignToTestRepository testRepository, ClientRepository clientRepository,
                             ClinicLabRepository labRepository, ClinicBranchRepository branchRepository,
                             ObjectMapper objectMapper) {
        this.testRepository = testRepository;
        this.clientRepository = clientRepository;
        this.labRepository = labRepository;
        this.branchRepository = branchRepository;
        this.objectMapper = objectMapper;
    }

    public String create(SignToTest sign) {
        SignToTestEntity signEntity;
        try {
            signEntity = objectMapper.convertValue(sign, SignToTestEntity.class);
            testRepository.save(signEntity);

            ClinicLabEntity labEntity = labRepository.findById(sign.getLab_id()).get();

            ClientEntity clientEntity = clientRepository.findById(sign.getClient_id()).get();
            ClinicBranchEntity branchEntity = branchRepository.findById(labEntity.getBranch_id()).get();
            return "Sign for test: " + signEntity.getName() + " on " + sign.getVisit_date()
                    + " in branch " + branchEntity.getName() + " on the " + branchEntity.getAddress()
                    + " from client " + clientEntity.getName() + " is created.";
        } catch (RuntimeException e) {
            throw new CreateException("Can not create a sign");
        }
    }

    public List<SignToTest> readAll() {
        List<SignToTestEntity> signs = testRepository.findAll();
        return signs.stream().map(s -> objectMapper.convertValue(s, SignToTest.class)).collect(Collectors.toList());
    }

    public String read(Long id) {


        SignToTestEntity signEntity = testRepository.findById(id).get();
        SignToTest sign = objectMapper.convertValue(signEntity, SignToTest.class);

        ClinicLabEntity labEntity = labRepository.findById(sign.getLab_id()).get();

        ClientEntity clientEntity = clientRepository.findById(sign.getClient_id()).get();
        ClinicBranchEntity branchEntity = branchRepository.findById(labEntity.getBranch_id()).get();
        return "Sign for test: " + signEntity.getName() + " on " + sign.getVisit_date()
                + " in branch " + branchEntity.getName() + " on the " + branchEntity.getAddress()
                + " from client " + clientEntity.getName();

    }

    public String update(Long id, SignToTest sign) {
        if (testRepository.findById(id).isPresent()) {
            try {
                SignToTestEntity signEntity = objectMapper.convertValue(sign, SignToTestEntity.class);
                testRepository.save(signEntity);
                ClinicLabEntity labEntity = labRepository.findById(sign.getLab_id()).get();
                ClientEntity clientEntity = clientRepository.findById(sign.getClient_id()).get();
                ClinicBranchEntity branchEntity = branchRepository.findById(labEntity.getBranch_id()).get();
                return "Sign for test: " + signEntity.getName() + " on " + sign.getVisit_date()
                        + " in branch " + branchEntity.getName() + " on the " + branchEntity.getAddress()
                        + " from client " + clientEntity.getName() + " is updated.";
            } catch (RuntimeException e) {
                throw new UpdateException("Sign is not found!");
            }
        }
        return "Sign " + sign.toString() + " is not found!";
    }

    public String delete(Long id) {
        try {
            testRepository.deleteById(id);
            return "Sign with id: " + id + " was deleted!";
        } catch (RuntimeException e) {
            throw new DeleteException("Sign is not found!");
        }
    }


}
