package clinic_registration.dto;

import lombok.*;

@Data
@NoArgsConstructor
public class ClinicProcedure {
    private Long id;
    private String name;
    private Integer duration;
}
