package clinic_registration.dto;

import lombok.*;

import java.time.LocalDate;
@Data
public class DoctorAppointmentDto {
    private Long id;
    private DoctorDto doctor;
    private ClientDto client;
    private ClinicBranchDto branch;
    private LocalDate visitDate;

}
