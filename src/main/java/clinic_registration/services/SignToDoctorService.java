package clinic_registration.services;

import clinic_registration.db.entity.SignToDoctorEntity;
import clinic_registration.db.repository.SignToDoctorRepository;
import clinic_registration.dto.DoctorStorage;
import clinic_registration.dto.SignToDoctor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SignToDoctorService {

    private final SignToDoctorRepository doctorRepository;
    private final ObjectMapper objectMapper;
    private DoctorStorage doctorStorage;

    public SignToDoctorService(SignToDoctorRepository doctorRepository, ObjectMapper objectMapper) {
        this.doctorRepository = doctorRepository;
        this.objectMapper = objectMapper;
    }

    private SignToDoctorEntity getDocEntity(SignToDoctor signToDoctor) {
        SignToDoctorEntity sign = new SignToDoctorEntity();
        sign.setId(signToDoctor.getId());
        sign.setVisitDate(signToDoctor.getVisitDate());
        sign.setDoctorType(signToDoctor.getDoctorType());
        sign.setDoctorName(doctorStorage.getDoctorByType(sign.getDoctorType()));
        return sign;
    }

    public void create(SignToDoctor signToDoctor) {

        SignToDoctorEntity sign = getDocEntity(signToDoctor);
        LocalDate visitDate = sign.getVisitDate();
        String docName = sign.getDoctorName();

        List<SignToDoctorEntity> doctorRepositoryAll = doctorRepository.findAll();
        for (SignToDoctorEntity tempSign : doctorRepositoryAll) {
            if(tempSign.getVisitDate() != visitDate && !docName.equals(tempSign.getDoctorName())){
                doctorRepository.save(sign);
            }
        }



    }
}
