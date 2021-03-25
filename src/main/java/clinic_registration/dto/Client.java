package clinic_registration.dto;

import lombok.*;

import java.time.LocalDate;
@Data
@NoArgsConstructor
public class Client {
    private Long id;
    private String name;
    private ClientGender clientGender;
    private LocalDate birthdate;
    private int phoneNumber;
    private String email;
}
