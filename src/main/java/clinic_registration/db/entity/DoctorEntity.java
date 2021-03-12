package clinic_registration.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(schema = "public", name = "doctor")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class DoctorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String position_name;
    private String add_position_name;
    private String email;
    private Integer phone_number;
    private LocalDate birthdate;
}
