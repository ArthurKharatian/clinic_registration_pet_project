package clinic_registration.services;

import clinic_registration.db.entity.SignToDoctorEntity;
import clinic_registration.db.repository.SignToDoctorRepository;
import clinic_registration.dto.Client;
import clinic_registration.dto.SignToDoctor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SignToDoctorService {

    private final SignToDoctorRepository doctorRepository;
    private final ObjectMapper objectMapper;


    public SignToDoctorService(SignToDoctorRepository doctorRepository, ObjectMapper objectMapper) {
        this.doctorRepository = doctorRepository;
        this.objectMapper = objectMapper;
    }

    public String create(SignToDoctor signToDoctor, Client client) {

        SignToDoctorEntity sign = objectMapper.convertValue(signToDoctor, SignToDoctorEntity.class);

        List<SignToDoctorEntity> doctorRepositoryAll = doctorRepository.findAll();
        for (SignToDoctorEntity tempSign : doctorRepositoryAll) {
            if(!sign.equals(tempSign)){
                sign.setClient_id(client.getId());
                doctorRepository.save(sign);
               return client.getName() + " is signed to " + sign.getDoctorType() + " " +
                      sign.getDoctorName() + ", on " + sign.getVisitDate() + ".";
            }
        }
        return "The sign to " + sign.getDoctorType() + " " + sign.getDoctorName() +
                " on " + sign.getVisitDate() + " is already exist. Please choose another date.";
    }

    public String update(Integer id, SignToDoctor signToDoctor) {
        if(doctorRepository.findById(id).isPresent()){
            SignToDoctorEntity doctorEntity = objectMapper.convertValue(signToDoctor, SignToDoctorEntity.class);;
            doctorRepository.save(doctorEntity);
            return doctorEntity.toString() + " is updated!";

        }
        return "The sign with id: " + id + " is not found!";
    }

    public String delete(Integer id) {
        if(doctorRepository.findById(id).isPresent()) {
            doctorRepository.findAll().removeIf(sign -> sign.getId().equals(id));
            return "The sign with id: " + id + " was deleted!";
        }
        return "The sign with id: " + id + " is not found!";
    }
}
