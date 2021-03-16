package clinic_registration.services;

import clinic_registration.db.entity.ClientEntity;
import clinic_registration.db.entity.ClinicBranchEntity;
import clinic_registration.db.entity.DoctorEntity;
import clinic_registration.db.entity.DoctorAppointmentEntity;
import clinic_registration.db.repository.ClientRepository;
import clinic_registration.db.repository.ClinicBranchRepository;
import clinic_registration.db.repository.DoctorRepository;
import clinic_registration.db.repository.DoctorAppointmentRepository;
import clinic_registration.dto.DoctorAppointment;
import clinic_registration.exceptions.CreateException;
import clinic_registration.exceptions.DeleteException;
import clinic_registration.exceptions.UpdateException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorAppointmentService {
    private final DoctorAppointmentRepository doctorAppointmentRepository;
    private final ClinicBranchRepository branchRepository;
    private final ClientRepository clientRepository;
    private final DoctorRepository doctorRepository;
    private final ObjectMapper objectMapper;

    public DoctorAppointmentService(DoctorAppointmentRepository doctorAppointmentRepository, ClinicBranchRepository branchRepository, ClientRepository clientRepository,
                                    DoctorRepository doctorRepository, ObjectMapper objectMapper) {
        this.doctorAppointmentRepository = doctorAppointmentRepository;
        this.branchRepository = branchRepository;
        this.clientRepository = clientRepository;
        this.doctorRepository = doctorRepository;
        this.objectMapper = objectMapper;
    }

    public String create(DoctorAppointment sign) {
        DoctorAppointmentEntity signEntity;
        try {
            signEntity = objectMapper.convertValue(sign, DoctorAppointmentEntity.class);
            doctorAppointmentRepository.save(signEntity);
            // TODO: 3/16/21 fix 'Optional.get()' without 'isPresent()' check here and down below
            // TODO: 3/16/21 example
//            doctorRepository.findById(sign.getDoctor_id()).orElseThrow(()->
//                            new CreateException(String.format("No doctor with id %d found", sign.getDoctor_id()), ErrorMessage.NOT_FOUND));
            DoctorEntity doctorEntity = doctorRepository.findById(sign.getDoctor_id()).get();
            ClientEntity clientEntity = clientRepository.findById(sign.getClient_id()).get();
            ClinicBranchEntity branchEntity = branchRepository.findById(signEntity.getBranch_id()).get();
            return "Sign to doctor " + doctorEntity.getPosition_name() + " " + doctorEntity.getName()
                    + " in branch " + branchEntity.getName() + " on the " + branchEntity.getAddress()
                    + " from client " + clientEntity.getName() + " is created.";
        } catch (RuntimeException e) {
            // TODO: 3/16/21 what if its other RuntimeException Child (any of its 832 children)???
            throw new CreateException("Can not create a sign");
        }
    }

    public List<DoctorAppointment> readAll() {
        List<DoctorAppointmentEntity> signs = doctorAppointmentRepository.findAll();
        return signs.stream().map(s -> objectMapper.convertValue(s, DoctorAppointment.class)).collect(Collectors.toList());
    }

    public String read(Long id) {
        DoctorAppointment sign = new DoctorAppointment();
        if (doctorAppointmentRepository.findById(id).isPresent()) {
            DoctorAppointmentEntity signEntity = doctorAppointmentRepository.findById(id).get();
            sign = objectMapper.convertValue(signEntity, DoctorAppointment.class);
        }
        DoctorEntity doctorEntity = doctorRepository.findById(sign.getDoctor_id()).get();
        ClientEntity clientEntity = clientRepository.findById(sign.getClient_id()).get();
        ClinicBranchEntity branchEntity = branchRepository.findById(sign.getBranch_id()).get();
        return "Sign to doctor " + doctorEntity.getPosition_name() + " " + doctorEntity.getName()
                + " to branch " + branchEntity.getName() + " on the " + branchEntity.getAddress()
                +" on " + sign.getVisit_date()
                + " from client " + clientEntity.getName();

    }

    public String update(Long id, DoctorAppointment sign) {

            try {
                if (doctorAppointmentRepository.findById(id).isPresent()) {
                    DoctorAppointmentEntity signEntity = objectMapper.convertValue(sign, DoctorAppointmentEntity.class);
                    doctorAppointmentRepository.save(signEntity);
                    DoctorEntity doctorEntity = doctorRepository.findById(sign.getDoctor_id()).get();
                    ClientEntity clientEntity = clientRepository.findById(sign.getClient_id()).get();
                    ClinicBranchEntity branchEntity = branchRepository.findById(signEntity.getBranch_id()).get();
                    return "Sign to doctor " + doctorEntity.getPosition_name() + " " + doctorEntity.getName()
                            + " to branch " + branchEntity.getName() + " on the " + branchEntity.getAddress()
                            + " on " + sign.getVisit_date()
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
            doctorAppointmentRepository.deleteById(id);
            return "Sign with id: " + id + " was deleted!";
        } catch (RuntimeException e) {
            // TODO: 3/16/21 what if its other RuntimeException Child (any of its 832 children)???
            throw new DeleteException("Sign is not found!");
        }
    }
}
