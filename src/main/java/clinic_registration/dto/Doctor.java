package clinic_registration.dto;

import lombok.*;

import java.time.LocalDate;
@Data
@NoArgsConstructor
public class Doctor {
    private Long id;
    private String name;
    private String positionName;
    private String addPositionName;
    private String email;
    private Integer phoneNumber;
    private LocalDate birthdate;
}
