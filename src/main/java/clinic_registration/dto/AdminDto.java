package clinic_registration.dto;

import lombok.*;

@Data
public class AdminDto {

    private Long id;
    private String name;
    private String  email;
    private String staffName;
    private Integer phoneNumber;
    private String status;

}
