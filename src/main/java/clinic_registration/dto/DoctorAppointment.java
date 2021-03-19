package clinic_registration.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class DoctorAppointment {
    private Long id;
    private Doctor doctor;
    private Client client;
    private ClinicBrach branch;
    private LocalDate visit_date;

}
