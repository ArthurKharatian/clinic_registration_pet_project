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
public class SignToProcedure {
    private Long id;
    private Long procedure_id;
    private Long client_id;
    private Long branch_id;
    private LocalDate visit_date;
}
