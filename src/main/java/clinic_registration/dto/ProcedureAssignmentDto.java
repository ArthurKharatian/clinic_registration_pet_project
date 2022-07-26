package clinic_registration.dto;

import lombok.*;

import java.time.LocalDate;

@Data
public class ProcedureAssignmentDto {
    private Long id;
    private ClinicProcedureDto procedure;
    private ClientDto client;
    private ClinicBranchDto branch;
    private LocalDate visitDate;
}
