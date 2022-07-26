package clinic_registration.service.impl;

import clinic_registration.db.entity.Doctor;
import clinic_registration.db.entity.Status;
import clinic_registration.db.repository.DoctorRepository;
import clinic_registration.dto.DoctorDto;
import clinic_registration.exceptions.ClinicServiceException;
import clinic_registration.service.DoctorService;
import clinic_registration.utils.JsonConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final ObjectMapper objectMapper;
    private static final String EXC_MESSAGE = "Doctor with id %d is not found";

    @Override
    public DoctorDto create(DoctorDto doctorDto) {
        if (doctorDto == null) {
            throw new ClinicServiceException("Doctor is null");
        }

        Long id = doctorDto.getId();
        if (id != null && doctorRepository.findById(id).isPresent()) {
            throw new ClinicServiceException(String.format("Doctor with id %d already exists", id));
        }

        Doctor doctor = objectMapper.convertValue(doctorDto, Doctor.class);
        doctor.setStatus(String.valueOf(Status.CREATED));
        doctorRepository.save(doctor);
        return doctorDto;
    }

    @Override
    public DoctorDto read(Long id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() ->
                new ClinicServiceException(String.format(EXC_MESSAGE, id)));
        return objectMapper.convertValue(doctor, DoctorDto.class);
    }

    @Override
    public DoctorDto update(DoctorDto doctorDto) {
        Long doctorId = doctorDto.getId();
        if (doctorId == null || doctorRepository.findById(doctorId).isEmpty()) {
            throw new ClinicServiceException(String.format(EXC_MESSAGE, doctorId));
        }
        Doctor doctor = objectMapper.convertValue(doctorDto, Doctor.class);
        doctor.setStatus(String.valueOf(Status.UPDATED));
        doctorRepository.save(doctor);
        return doctorDto;
    }

    @Override
    public DoctorDto delete(Long id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() ->
                new ClinicServiceException(String.format(EXC_MESSAGE, id)));
        doctor.setStatus(String.valueOf(Status.DELETED));
        doctorRepository.save(doctor);
        return objectMapper.convertValue(doctor, DoctorDto.class);

    }

    @Override
    public List<DoctorDto> readAll() {
        List<Doctor> doctors = doctorRepository.findAll();
        return doctors.stream().map(assignment ->
                        objectMapper.convertValue(assignment, DoctorDto.class))
                .collect(Collectors.toList());

    }

    @Override
    public ResponseEntity<String> createDoctor(DoctorDto doctorDto) {
        if (doctorDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Doctor is not created! Body is null");
        }

        DoctorDto doctor = create(doctorDto);
        String dto = JsonConverter.getString(doctor, objectMapper);

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @Override
    public ResponseEntity<String> readDoctor(Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor is not found! Id is null");
        }

        DoctorDto doctor = read(id);
        String dto = JsonConverter.getString(doctor, objectMapper);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateDoctor(DoctorDto doctorDto) {
        if (doctorDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Doctor is not updated! Body is null");
        }

        DoctorDto doctor = update(doctorDto);
        String dto = JsonConverter.getString(doctor, objectMapper);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @Override
    public ResponseEntity<String> deleteDoctor(Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Doctor is not deleted! Id is null");
        }

        DoctorDto doctor = delete(id);
        String dto = JsonConverter.getString(doctor, objectMapper);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @Override
    public ResponseEntity<List<DoctorDto>> getAllDoctors() {
        return ResponseEntity.ok(readAll());
    }
}
