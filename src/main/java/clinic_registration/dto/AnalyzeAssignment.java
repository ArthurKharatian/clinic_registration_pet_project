package clinic_registration.dto;

import clinic_registration.db.entity.ClientEntity;
import clinic_registration.db.entity.ClinicLabEntity;
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
    private ClinicLabEntity lab;
    private ClientEntity client;
    private LocalDate visit_date;
}
