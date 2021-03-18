package clinic_registration.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(schema = "public", name = "doctor_appointment")
@Getter
@Setter
@NoArgsConstructor
@ToString

public class DoctorAppointmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long doctor_id;
    private Long client_id;
    private Long branch_id;
    private LocalDate visit_date;
}
