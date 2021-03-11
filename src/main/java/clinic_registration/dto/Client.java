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
public class Client {
    private Long id;
    private String name;
    private ClientGender client_gender;
    private LocalDate birthdate;
    private int phone_number;
    private String email;
}
