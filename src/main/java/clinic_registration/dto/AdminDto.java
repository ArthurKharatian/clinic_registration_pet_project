package clinic_registration.dto;

import lombok.*;

@Data
@NoArgsConstructor
public class AdminDto {
    private Long id;
    private String name;
    private String  email;
    private String staffName;
    private Integer phoneNumber;
}
