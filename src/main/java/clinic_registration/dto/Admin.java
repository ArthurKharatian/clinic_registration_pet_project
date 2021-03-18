package clinic_registration.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Admin {
    private Long id;
    private String name;
    private String  email;
    private String staff_name;
    private Integer phone_number;
}
