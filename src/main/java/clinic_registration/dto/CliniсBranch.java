package clinic_registration.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CliniсBranch {
    private Long id;
    private String name;
    private String address;
    private String open_time;
    private String close_time;
}
