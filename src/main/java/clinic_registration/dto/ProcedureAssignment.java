package clinic_registration.dto;

import lombok.*;

import java.time.LocalDate;
@Data
@NoArgsConstructor
public class ProcedureAssignment {
    private Long id;
    private ClinicProcedure procedure;
    private Client client;
    private ClinicBranch branch;
    private LocalDate visitDate;
}
