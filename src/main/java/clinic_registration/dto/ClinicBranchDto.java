package clinic_registration.dto;

import lombok.*;

@Data
public class ClinicBranchDto {

    private Long id;
    private AdminDto admin;
    private String name;
    private String address;
    private String openTime;
    private String closeTime;
    private String status;

}
