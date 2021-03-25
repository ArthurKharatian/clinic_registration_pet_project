package clinic_registration.db.entity;

import clinic_registration.dto.ClientGender;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@Table(schema = "public", name = "client")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDate birthdate;

    @Column(name = "phone_number")
    private int phoneNumber;
    private String email;

    @Column(name = "client_gender")
    private ClientGender clientGender;
}
