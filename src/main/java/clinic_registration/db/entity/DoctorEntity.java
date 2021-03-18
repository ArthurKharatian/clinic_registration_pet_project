package clinic_registration.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(schema = "public", name = "doctor")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class DoctorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String position_name;
    private String add_position_name;
    private String email;
    private Integer phone_number;
    private LocalDate birthdate;

}
