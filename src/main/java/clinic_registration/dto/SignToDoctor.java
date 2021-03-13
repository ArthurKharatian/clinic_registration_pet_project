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
public class SignToDoctor {
    private Long id;
    private Long doctor_id;
    private Long client_id;
    private Long branch_id;
    private LocalDate visit_date;
}
