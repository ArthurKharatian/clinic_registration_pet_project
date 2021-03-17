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

            signEntity = objectMapper.convertValue(sign, ProcedureAssignmentEntity.class);
            procedureRepository.save(signEntity);
            ClinicProcedureEntity procedureEntity = clinicProcedureRepository.findById(sign.getProcedure_id()).get();
            ClientEntity clientEntity = clientRepository.findById(sign.getClient_id()).get();
            ClinicBranchEntity branchEntity = branchRepository.findById(signEntity.getBranch_id()).get();
            return "Sign for procedure: " + procedureEntity.getName() + " with a duration of " + procedureEntity.getDuration()
                    + " minute(s), in branch " + branchEntity.getName() + " on the " + branchEntity.getAddress() + " on " + sign.getVisit_date()
                    + " from client " + clientEntity.getName() + " is created.";

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


        return "Sign " + sign.toString() + " is not found!";
    }

    public String delete(Long id) {

            procedureRepository.deleteById(id);
            return "Sign with id: " + id + " was deleted!";

    }
}
