package clinic_registration.services;

import clinic_registration.db.entity.ClientEntity;
import clinic_registration.db.entity.ClinicBranchEntity;
import clinic_registration.db.entity.DoctorEntity;
import clinic_registration.db.entity.SignToDoctorEntity;
import clinic_registration.db.repository.ClientRepository;
import clinic_registration.db.repository.ClinicBranchRepository;
import clinic_registration.db.repository.DoctorRepository;
import clinic_registration.db.repository.SignToDoctorRepository;
import clinic_registration.dto.SignToDoctor;
import clinic_registration.exceptions.CreateException;
import clinic_registration.exceptions.DeleteException;
import clinic_registration.exceptions.UpdateException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SignToDoctorService {
    private final SignToDoctorRepository signToDoctorRepository;
    private final ClinicBranchRepository branchRepository;
    private final ClientRepository clientRepository;
    private final DoctorRepository doctorRepository;
    private final ObjectMapper objectMapper;

    public SignToDoctorService(SignToDoctorRepository signToDoctorRepository, ClinicBranchRepository branchRepository, ClientRepository clientRepository,
                               DoctorRepository doctorRepository, ObjectMapper objectMapper) {
        this.signToDoctorRepository = signToDoctorRepository;
        this.branchRepository = branchRepository;
        this.clientRepository = clientRepository;
        this.doctorRepository = doctorRepository;
        this.objectMapper = objectMapper;
    }

    public String create(SignToDoctor sign) {
        SignToDoctorEntity signEntity;
        try {
            signEntity = objectMapper.convertValue(sign, SignToDoctorEntity.class);
            signToDoctorRepository.save(signEntity);
            DoctorEntity doctorEntity = doctorRepository.findById(sign.getDoctor_id()).get();
            ClientEntity clientEntity = clientRepository.findById(sign.getClient_id()).get();
            ClinicBranchEntity branchEntity = branchRepository.findById(signEntity.getBranch_id()).get();
            return "Sign to doctor " + doctorEntity.getPosition_name() + " " + doctorEntity.getName()
                    + " in branch " + branchEntity.getName() + " on the " + branchEntity.getAddress()
                    + " from client " + clientEntity.getName() + " is created.";
        } catch (RuntimeException e) {
            throw new CreateException("Can not create a sign");
        }
    }

    public List<SignToDoctor> readAll() {
        List<SignToDoctorEntity> signs = signToDoctorRepository.findAll();
        return signs.stream().map(s -> objectMapper.convertValue(s, SignToDoctor.class)).collect(Collectors.toList());
    }

    public String read(Long id) {
        SignToDoctor sign = new SignToDoctor();
        if (signToDoctorRepository.findById(id).isPresent()) {
            SignToDoctorEntity signEntity = signToDoctorRepository.findById(id).get();
            sign = objectMapper.convertValue(signEntity, SignToDoctor.class);
        }
        DoctorEntity doctorEntity = doctorRepository.findById(sign.getDoctor_id()).get();
        ClientEntity clientEntity = clientRepository.findById(sign.getClient_id()).get();
        ClinicBranchEntity branchEntity = branchRepository.findById(sign.getBranch_id()).get();
        return "Sign to doctor " + doctorEntity.getPosition_name() + " " + doctorEntity.getName()
                + " to branch " + branchEntity.getName() + " on the " + branchEntity.getAddress()
                +" on " + sign.getVisit_date()
                + " from client " + clientEntity.getName();

    }

    public String update(Long id, SignToDoctor sign) {

            try {
                if (signToDoctorRepository.findById(id).isPresent()) {
                    SignToDoctorEntity signEntity = objectMapper.convertValue(sign, SignToDoctorEntity.class);
                    signToDoctorRepository.save(signEntity);
                    DoctorEntity doctorEntity = doctorRepository.findById(sign.getDoctor_id()).get();
                    ClientEntity clientEntity = clientRepository.findById(sign.getClient_id()).get();
                    ClinicBranchEntity branchEntity = branchRepository.findById(signEntity.getBranch_id()).get();
                    return "Sign to doctor " + doctorEntity.getPosition_name() + " " + doctorEntity.getName()
                            + " to branch " + branchEntity.getName() + " on the " + branchEntity.getAddress()
                            + " on " + sign.getVisit_date()
                            + " from client " + clientEntity.getName() + " is updated.";
                }
            } catch (RuntimeException e) {
                throw new UpdateException("Sign is not found!");
            }

        return "Sign " + sign.toString() + " is not found!";
    }

    public String delete(Long id) {
        try {
            signToDoctorRepository.deleteById(id);
            return "Sign with id: " + id + " was deleted!";
        } catch (RuntimeException e) {
            throw new DeleteException("Sign is not found!");
        }
    }
}
