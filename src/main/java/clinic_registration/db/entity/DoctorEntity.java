package clinic_registration.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(schema = "public", name = "doctor")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class DoctorEntity {
    @Id
    private Long id;
    private String name, position_name, add_position_name, email;
    private Integer phone_number;
    private LocalDate birthdate;
}
