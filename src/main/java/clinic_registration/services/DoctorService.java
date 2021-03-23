package clinic_registration.services;

import clinic_registration.db.entity.DoctorEntity;
import clinic_registration.db.repository.DoctorRepository;
import clinic_registration.dto.Doctor;
import clinic_registration.exceptions.ClinicServiceException;
import clinic_registration.exceptions.ErrorMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final ObjectMapper objectMapper;


    public DoctorService(DoctorRepository doctorRepository, ObjectMapper objectMapper) {
        this.doctorRepository = doctorRepository;
        this.objectMapper = objectMapper;
    }

    public void create(Doctor doctor) {
        if(doctor == null){throw new ClinicServiceException("doctor is null", ErrorMessage.BAD_REQUEST);}
        DoctorEntity doctorEntity = objectMapper.convertValue(doctor, DoctorEntity.class);
        doctorRepository.save(doctorEntity);
    }

    public List<Doctor> readAll() {
        List<DoctorEntity> doctors = doctorRepository.findAll();
        return doctors.stream().map(doc -> objectMapper.convertValue(doc, Doctor.class)).collect(Collectors.toList());
    }

    public Doctor read(Long id) {
        DoctorEntity doctorEntity = doctorRepository.findById(id).orElseThrow(()->
                new ClinicServiceException(String.format("Doctor with id %d is not found", id), ErrorMessage.NOT_FOUND));
        return objectMapper.convertValue(doctorEntity, Doctor.class);
    }

    public void update(Long id, Doctor doctor) {
        if (doctorRepository.existsById(id)) {
            doctor.setId(id);
            doctorRepository.save(objectMapper.convertValue(doctor, DoctorEntity.class));
        } else {
            throw new ClinicServiceException(String.format("Doctor with id %d is not found", id), ErrorMessage.NOT_FOUND);
        }

    }
    public void delete(Long id) {
        if(!doctorRepository.existsById(id)){throw new ClinicServiceException(String.format("Doctor with id %d is not found", id), ErrorMessage.NOT_FOUND);}
        doctorRepository.deleteById(id);
    }
}
