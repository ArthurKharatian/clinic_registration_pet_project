package clinic_registration.db.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(schema = "public", name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(name = "position_name")
    private String positionName;

    @Column(name = "add_position_name")
    private String addPositionName;
    private String email;

    @Column(name = "phone_number")
    private Integer phoneNumber;
    private LocalDate birthdate;

    private String status;
}
