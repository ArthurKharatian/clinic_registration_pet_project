package clinic_registration.dto;

import lombok.*;

@Data
@NoArgsConstructor
public class ClinicBranch {
    private Long id;
    private Admin admin;
    private String name;
    private String address;
    private String openTime;
    private String closeTime;
}
