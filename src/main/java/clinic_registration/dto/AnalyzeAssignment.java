package clinic_registration.dto;

import lombok.*;

import java.time.LocalDate;
@Data
@NoArgsConstructor
public class AnalyzeAssignment {
    private Long id;
    private String name;
    private ClinicLab lab;
    private Client client;
    private LocalDate visitDate;
}
