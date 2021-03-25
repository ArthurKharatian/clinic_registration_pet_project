package clinic_registration.dto;

import lombok.*;

@Data
@NoArgsConstructor
public class ClinicLab {
    private Long id;
    private ClinicBranch branch;
    private String workerName;
    private String positionName;
    private String openTime;
    private String closeTime;
}
