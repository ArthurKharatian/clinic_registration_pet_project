package clinic_registration.service.impl;

import clinic_registration.db.entity.DoctorAppointment;
import clinic_registration.db.entity.Status;
import clinic_registration.db.repository.DoctorAppointmentRepository;
import clinic_registration.dto.DoctorAppointmentDto;
import clinic_registration.exceptions.ClinicServiceException;
import clinic_registration.service.DoctorAppointmentService;
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
public class DoctorAppointmentServiceImpl implements DoctorAppointmentService {

    private final DoctorAppointmentRepository appointmentRepository;
    private final ObjectMapper objectMapper;
    private static final String EXC_MESSAGE = "Appointment with id %d is not found";


    @Override
    public DoctorAppointmentDto create(DoctorAppointmentDto appointmentDto) {
        if (appointmentDto == null) {
            throw new ClinicServiceException("Appointment is null");
        }

        Long id = appointmentDto.getId();
        if (id != null && appointmentRepository.findById(id).isPresent()) {
            throw new ClinicServiceException(String.format("Appointment with id %d already exists", id));
        }

        DoctorAppointment appointment = objectMapper.convertValue(appointmentDto, DoctorAppointment.class);
        appointment.setStatus(String.valueOf(Status.CREATED));
        appointmentRepository.save(appointment);
        return appointmentDto;
    }

    @Override
    public DoctorAppointmentDto read(Long id) {
        DoctorAppointment appointment = appointmentRepository.findById(id).orElseThrow(() ->
                new ClinicServiceException(String.format(EXC_MESSAGE, id)));
        return objectMapper.convertValue(appointment, DoctorAppointmentDto.class);
    }

    @Override
    public DoctorAppointmentDto update(DoctorAppointmentDto appointmentDto) {
        Long doctorId = appointmentDto.getId();
        if (doctorId == null || appointmentRepository.findById(doctorId).isEmpty()) {
            throw new ClinicServiceException(String.format(EXC_MESSAGE, doctorId));
        }
        DoctorAppointment appointment = objectMapper.convertValue(appointmentDto, DoctorAppointment.class);
        appointment.setStatus(String.valueOf(Status.UPDATED));
        appointmentRepository.save(appointment);
        return appointmentDto;
    }

    @Override
    public DoctorAppointmentDto delete(Long id) {
        DoctorAppointment appointment = appointmentRepository.findById(id).orElseThrow(() ->
                new ClinicServiceException(String.format(EXC_MESSAGE, id)));
        appointment.setStatus(String.valueOf(Status.DELETED));
        appointmentRepository.save(appointment);
        return objectMapper.convertValue(appointment, DoctorAppointmentDto.class);
    }

    @Override
    public List<DoctorAppointmentDto> readAll() {
        List<DoctorAppointment> appointments = appointmentRepository.findAll();
        return appointments.stream().map(appointment ->
                        objectMapper.convertValue(appointment, DoctorAppointmentDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<String> createAppointment(DoctorAppointmentDto appointmentDto) {
        if (appointmentDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Appointment is not created! Body is null");
        }

        DoctorAppointmentDto appointment = create(appointmentDto);
        String dto = JsonConverter.getString(appointment, objectMapper);

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @Override
    public ResponseEntity<String> readAppointment(Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Appointment is not found! Id is null");
        }

        DoctorAppointmentDto appointment = read(id);
        String dto = JsonConverter.getString(appointment, objectMapper);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateAppointment(DoctorAppointmentDto appointmentDto) {
        if (appointmentDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Appointment is not updated! Body is null");
        }

        DoctorAppointmentDto appointment = update(appointmentDto);
        String dto = JsonConverter.getString(appointment, objectMapper);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @Override
    public ResponseEntity<String> deleteAppointment(Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Appointment is not deleted! Id is null");
        }

        DoctorAppointmentDto appointment = delete(id);
        String dto = JsonConverter.getString(appointment, objectMapper);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @Override
    public ResponseEntity<List<DoctorAppointmentDto>> getAllAppointments() {
        return ResponseEntity.ok(readAll());
    }
}
