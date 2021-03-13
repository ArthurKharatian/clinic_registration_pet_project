package clinic_registration.services;

import clinic_registration.db.entity.SignToDoctorEntity;
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
    private final ObjectMapper objectMapper;

    public SignToDoctorService(SignToDoctorRepository signToDoctorRepository, ObjectMapper objectMapper) {
        this.signToDoctorRepository = signToDoctorRepository;
        this.objectMapper = objectMapper;
    }

    public String create(SignToDoctor sign) {
        SignToDoctorEntity signEntity;
        try {
            signEntity = objectMapper.convertValue(sign, SignToDoctorEntity.class);
            signToDoctorRepository.save(signEntity);
        } catch (RuntimeException e) {
            throw new CreateException("Can not create a sign");
        }
        return signEntity.toString() + "is created";
    }

    public List<SignToDoctor> readAll() {
        List<SignToDoctorEntity> signs = signToDoctorRepository.findAll();
        return signs.stream().map(s -> objectMapper.convertValue(s, SignToDoctor.class)).collect(Collectors.toList());
    }

    public SignToDoctor read(Long id) {
        SignToDoctor sign = null;
        if (signToDoctorRepository.findById(id).isPresent()) {
            SignToDoctorEntity signEntity = signToDoctorRepository.findById(id).get();
            sign = objectMapper.convertValue(signEntity, SignToDoctor.class);
        }
        return sign;
    }

    public String update(Long id, SignToDoctor sign) {
        if (signToDoctorRepository.findById(id).isPresent()) {
            try {
                SignToDoctorEntity signEntity = objectMapper.convertValue(sign, SignToDoctorEntity.class);
                signToDoctorRepository.save(signEntity);
                return sign.toString() + " is updated!";
            } catch (RuntimeException e) {
                throw new UpdateException("Sign is not found!");
            }
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
