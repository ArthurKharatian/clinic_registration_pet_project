package clinic_registration.db.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(schema = "public", name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDate birthdate;

    @Column(name = "phone_number")
    private int phoneNumber;
    private String email;

    private String gender;

    private String status;
}
