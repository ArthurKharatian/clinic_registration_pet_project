package clinic_registration.services;

import clinic_registration.db.entity.DoctorEntity;
import clinic_registration.db.repository.DoctorRepository;
import clinic_registration.dto.Doctor;
import clinic_registration.exceptions.CreateException;
import clinic_registration.exceptions.DeleteException;
import clinic_registration.exceptions.UpdateException;
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

    public String create(Doctor doctor) {
        DoctorEntity doctorEntity;
        try {
            doctorEntity = objectMapper.convertValue(doctor, DoctorEntity.class);
            doctorRepository.save(doctorEntity);
        } catch (RuntimeException e) {
            throw new CreateException("Can not create an doctor");
        }
        return doctorEntity.toString() + "is created";
    }

    public List<Doctor> readAll() {
        List<DoctorEntity> doctors = doctorRepository.findAll();
        return doctors.stream().map(doc -> objectMapper.convertValue(doc, Doctor.class)).collect(Collectors.toList());
    }

    public Doctor read(Long id) {
        Doctor doctor = null;
        if (doctorRepository.findById(id).isPresent()) {
            DoctorEntity doctorEntity = doctorRepository.findById(id).get();
            doctor = objectMapper.convertValue(doctorEntity, Doctor.class);
        }
        return doctor;
    }

    public String update(Long id, Doctor doctor) {
        if (doctorRepository.findById(id).isPresent()) {
            try {
                DoctorEntity doctorEntity = objectMapper.convertValue(doctor, DoctorEntity.class);
                doctorRepository.save(doctorEntity);
                return doctor.toString() + " is updated!";
            } catch (RuntimeException e) {
                throw new UpdateException("Doctor is not found!");
            }
        }
        return "Doctor " + doctor.toString() + " is not found!";
    }

    public String delete(Long id) {
        try {
            doctorRepository.deleteById(id);
            return "Doctor with id: " + id + " was deleted!";
        } catch (RuntimeException e) {
            throw new DeleteException("Doctor is not found!");
        }
    }
}
