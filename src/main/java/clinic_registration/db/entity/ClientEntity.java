package clinic_registration.db.entity;

import clinic_registration.dto.ClientGender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(schema = "public", name = "client")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDate birthdate;
    private int phone_number;
    private String email;
    private ClientGender client_gender;
}
