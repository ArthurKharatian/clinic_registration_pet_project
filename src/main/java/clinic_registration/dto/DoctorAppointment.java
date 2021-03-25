package clinic_registration.dto;

import lombok.*;

import java.time.LocalDate;
@Data
@NoArgsConstructor
public class DoctorAppointment {
    private Long id;
    private Doctor doctor;
    private Client client;
    private ClinicBranch branch;
    private LocalDate visitDate;

}
