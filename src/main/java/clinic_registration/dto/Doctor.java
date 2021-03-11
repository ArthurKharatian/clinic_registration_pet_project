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
public class Doctor {
    private Long id;
    private String name, position_name, add_position_name, email;
    private Integer phone_number;
    private LocalDate birthdate;
}
