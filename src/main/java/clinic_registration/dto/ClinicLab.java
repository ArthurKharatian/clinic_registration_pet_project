package clinic_registration.dto;

import clinic_registration.db.entity.ClinicBranchEntity;
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
    private ClinicBranchEntity branch;
    private String worker_name;
    private String position_name;
    private String open_time;
    private String close_time;

}
