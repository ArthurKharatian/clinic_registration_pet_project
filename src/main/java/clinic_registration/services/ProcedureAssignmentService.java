package clinic_registration.services;

import clinic_registration.db.entity.ClientEntity;
import clinic_registration.db.entity.ClinicBranchEntity;
import clinic_registration.db.entity.ClinicProcedureEntity;
import clinic_registration.db.entity.ProcedureAssignmentEntity;
import clinic_registration.db.repository.ClientRepository;
import clinic_registration.db.repository.ClinicBranchRepository;
import clinic_registration.db.repository.ClinicProcedureRepository;
import clinic_registration.db.repository.ProcedureAssignmentRepository;
import clinic_registration.dto.ProcedureAssignment;
import clinic_registration.exceptions.CreateException;
import clinic_registration.exceptions.DeleteException;
import clinic_registration.exceptions.UpdateException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProcedureAssignmentService {
    private final ProcedureAssignmentRepository procedureRepository;
    private final ClinicBranchRepository branchRepository;
    private final ClientRepository clientRepository;
    private final ClinicProcedureRepository clinicProcedureRepository;
    private final ObjectMapper objectMapper;

    public ProcedureAssignmentService(ProcedureAssignmentRepository procedureRepository, ClinicBranchRepository branchRepository, ClientRepository clientRepository,
                                      ClinicProcedureRepository clinicProcedureRepository, ObjectMapper objectMapper) {
        this.procedureRepository = procedureRepository;
        this.branchRepository = branchRepository;
        this.clientRepository = clientRepository;
        this.clinicProcedureRepository = clinicProcedureRepository;
        this.objectMapper = objectMapper;
    }

    public String create(ProcedureAssignment sign) {
        ProcedureAssignmentEntity signEntity;
        try {
            signEntity = objectMapper.convertValue(sign, ProcedureAssignmentEntity.class);
            procedureRepository.save(signEntity);
            ClinicProcedureEntity procedureEntity = clinicProcedureRepository.findById(sign.getProcedure_id()).get();
            ClientEntity clientEntity = clientRepository.findById(sign.getClient_id()).get();
            ClinicBranchEntity branchEntity = branchRepository.findById(signEntity.getBranch_id()).get();
            return "Sign for procedure: " + procedureEntity.getName() + " with a duration of " + procedureEntity.getDuration()
                    + " minute(s), in branch " + branchEntity.getName() + " on the " + branchEntity.getAddress() + " on " + sign.getVisit_date()
                    + " from client " + clientEntity.getName() + " is created.";
        } catch (RuntimeException e) {
            // TODO: 3/16/21 what if its other RuntimeException Child (any of its 832 children)???
            throw new CreateException("Can not create a sign");
        }
    }

    public List<ProcedureAssignment> readAll() {
        List<ProcedureAssignmentEntity> signs = procedureRepository.findAll();
        return signs.stream().map(s -> objectMapper.convertValue(s, ProcedureAssignment.class)).collect(Collectors.toList());
    }

    public String read(Long id) {
        ProcedureAssignment sign = new ProcedureAssignment();
        if (procedureRepository.findById(id).isPresent()) {
            ProcedureAssignmentEntity signEntity = procedureRepository.findById(id).get();
            sign = objectMapper.convertValue(signEntity, ProcedureAssignment.class);
        }
        // TODO: 3/16/21 fix 'Optional.get()' without 'isPresent()' check here and down below
        ClinicProcedureEntity procedureEntity = clinicProcedureRepository.findById(sign.getProcedure_id()).get();
        ClientEntity clientEntity = clientRepository.findById(sign.getClient_id()).get();
        ClinicBranchEntity branchEntity = branchRepository.findById(sign.getBranch_id()).get();
        return "Sign for procedure: " + procedureEntity.getName() + " with a duration of " + procedureEntity.getDuration()
                + " minute(s), in branch " + branchEntity.getName() + " on the " + branchEntity.getAddress()
                +" on " + sign.getVisit_date()
                + " from client " + clientEntity.getName();

    }

    public String update(Long id, ProcedureAssignment sign) {

            try {
                if (procedureRepository.findById(id).isPresent()) {
                    ProcedureAssignmentEntity signEntity = objectMapper.convertValue(sign, ProcedureAssignmentEntity.class);
                    procedureRepository.save(signEntity);
                    ClinicProcedureEntity procedureEntity = clinicProcedureRepository.findById(sign.getProcedure_id()).get();
                    ClientEntity clientEntity = clientRepository.findById(sign.getClient_id()).get();
                    ClinicBranchEntity branchEntity = branchRepository.findById(signEntity.getBranch_id()).get();
                    return "Sign for procedure: " + procedureEntity.getName() + " with a duration of " + procedureEntity.getDuration()
                            + " minute(s), in branch " + branchEntity.getName() + " on the " + branchEntity.getAddress()
                            + " from client " + clientEntity.getName() + " is updated.";
                }
            } catch (RuntimeException e) {
            // TODO: 3/16/21 what if its other RuntimeException Child (any of its 832 children)???
                throw new UpdateException("Sign is not found!");
            }

        return "Sign " + sign.toString() + " is not found!";
    }

    public String delete(Long id) {
        try {
            procedureRepository.deleteById(id);
            return "Sign with id: " + id + " was deleted!";
        } catch (RuntimeException e) {
            // TODO: 3/16/21 what if its other RuntimeException Child (any of its 832 children)???
            throw new DeleteException("Sign is not found!");
        }
    }
}
