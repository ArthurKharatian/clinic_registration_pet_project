package clinic_registration.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ClinicLab {
    private Long id;
    private String worker_name;
    private String position_name;
    private String open_time;
    private String close_time;

}
