package clinic_registration.dto;

import clinic_registration.db.entity.ClientEntity;
import clinic_registration.db.entity.ClinicBranchEntity;
import clinic_registration.db.entity.DoctorEntity;
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
    private DoctorEntity doctor;
    private ClientEntity client;
    private ClinicBranchEntity branch;
    private LocalDate visit_date;

}
