package clinic_registration.services;

import clinic_registration.db.entity.SignToClinicServiceEntity;
import clinic_registration.db.repository.SignToClinicServiceRepository;
import clinic_registration.dto.Client;
import clinic_registration.dto.SignToClinicService;
import clinic_registration.exceptions.CreateException;
import clinic_registration.exceptions.DeleteException;
import clinic_registration.exceptions.UpdateException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SignToClinicServService {
    private final SignToClinicServiceRepository clinicServiceRepository;
    private final ObjectMapper objectMapper;

    public SignToClinicServService(SignToClinicServiceRepository clinicServiceRepository, ObjectMapper objectMapper) {
        this.clinicServiceRepository = clinicServiceRepository;
        this.objectMapper = objectMapper;
    }

    public String create(SignToClinicService clinicService, Client client) {
        SignToClinicServiceEntity sign;
        try {
            sign = objectMapper.convertValue(clinicService, SignToClinicServiceEntity.class);

            List<SignToClinicServiceEntity> serviceRepoAll = clinicServiceRepository.findAll();
            for (SignToClinicServiceEntity tempSign : serviceRepoAll) {
                if (!sign.equals(tempSign)) {
                    sign.setClient_id(client.getId());
                    clinicServiceRepository.save(sign);
                    return client.getName() + " is signed to " + sign.getServiceType() +
                            " , on " + sign.getVisitDate() + ".";
                }
            }
        } catch (CreateException e) {
            throw new CreateException(e.getMessage());
        }
        return "The sign to " + sign.getServiceType() + " on " +
                sign.getVisitDate() + " is already exist. Please choose another date.";
    }

    public String update(Integer id, SignToClinicService clinicService) {
        if (clinicServiceRepository.findById(id).isPresent()) {
            try {
                SignToClinicServiceEntity sign = objectMapper.convertValue(clinicService, SignToClinicServiceEntity.class);
                clinicServiceRepository.save(sign);
                return sign.toString() + " is updated!";
            } catch (UpdateException e) {
                throw new UpdateException(e.getMessage());
            }

        }
        return "The sign with id: " + id + " is not found!";
    }

    public String delete(Integer id) {
        if (clinicServiceRepository.findById(id).isPresent()) {
            try {
                clinicServiceRepository.findAll().removeIf(sign -> sign.getId().equals(id));
                return "The sign with id: " + id + " was deleted!";
            } catch (DeleteException e) {
                throw new DeleteException(e.getMessage());
            }
        }
        return "The sign with id: " + id + " is not found!";
    }
}
