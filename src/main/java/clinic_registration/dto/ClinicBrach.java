package clinic_registration.dto;

import clinic_registration.db.entity.AdminEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ClinicBrach {
    private Long id;
    private AdminEntity admin;
    private String name;
    private String address;
    private String open_time;
    private String close_time;
}
