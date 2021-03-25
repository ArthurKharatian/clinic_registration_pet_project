package clinic_registration.services;

import clinic_registration.db.entity.DoctorAppointmentEntity;
import clinic_registration.db.repository.DoctorAppointmentRepository;
import clinic_registration.dto.DoctorAppointment;
import clinic_registration.exceptions.ClinicServiceException;
import clinic_registration.exceptions.ErrorMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorAppointmentService {
    private final DoctorAppointmentRepository appointmentRepository;
    private final ObjectMapper objectMapper;
    private static final String EXC_MESSAGE = "The appointment with id %d is not found";


    public DoctorAppointmentService(DoctorAppointmentRepository appointmentRepository, ObjectMapper objectMapper) {
        this.appointmentRepository = appointmentRepository;
        this.objectMapper = objectMapper;
    }

    public void create(DoctorAppointment appointment) {
        if(appointment == null){throw new ClinicServiceException("appointment is null", ErrorMessage.BAD_REQUEST);}
        DoctorAppointmentEntity appointmentEntity = objectMapper.convertValue(appointment, DoctorAppointmentEntity.class);
        appointmentRepository.save(appointmentEntity);
    }

    public List<DoctorAppointment> readAll() {
        List<DoctorAppointmentEntity> appointments = appointmentRepository.findAll();
        return appointments.stream().map(ap -> objectMapper.convertValue(ap, DoctorAppointment.class)).collect(Collectors.toList());
    }

    public DoctorAppointment read(Long id) {
        DoctorAppointmentEntity appointmentEntity = appointmentRepository.findById(id).orElseThrow(()->
                new ClinicServiceException(String.format(EXC_MESSAGE, id), ErrorMessage.NOT_FOUND));
        return objectMapper.convertValue(appointmentEntity, DoctorAppointment.class);
    }

    public void update(DoctorAppointment appointment) {
        if (!appointmentRepository.existsById(appointment.getId())) {
            throw new ClinicServiceException(String.format(EXC_MESSAGE, appointment.getId()), ErrorMessage.NOT_FOUND);
        }
        appointmentRepository.save(objectMapper.convertValue(appointment, DoctorAppointmentEntity.class));

    }
    public void delete(Long id) {
        if(!appointmentRepository.existsById(id)){throw new ClinicServiceException(String.format(EXC_MESSAGE, id), ErrorMessage.NOT_FOUND);}
        appointmentRepository.deleteById(id);
    }
}
