package clinic_registration.dto;

import lombok.*;

import java.time.LocalDate;
@Data
public class ClientDto {

    private Long id;
    private String name;
    private String gender;
    private LocalDate birthdate;
    private Integer phoneNumber;
    private String email;
    private String status;
}
