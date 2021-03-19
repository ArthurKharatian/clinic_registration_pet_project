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
public class AnalyzeAssignment {
    private Long id;
    private String name;
    private ClinicLab lab;
    private Client client;
    private LocalDate visit_date;
}
