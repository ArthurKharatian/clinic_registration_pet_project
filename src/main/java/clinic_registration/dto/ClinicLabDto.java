package clinic_registration.dto;

import lombok.*;

@Data
public class ClinicLabDto {
    private Long id;
    private ClinicBranchDto branch;
    private String workerName;
    private String positionName;
    private String openTime;
    private String closeTime;
}
