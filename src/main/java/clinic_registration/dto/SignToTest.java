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
public class SignToTest {
    private Long id;
    private String name;
    private Long lab_id;
    private Long client_id;
    private LocalDate visit_date;
}
