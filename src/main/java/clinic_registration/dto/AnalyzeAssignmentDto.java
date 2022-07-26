package clinic_registration.dto;

import lombok.*;

import java.time.LocalDate;
@Data
public class AnalyzeAssignmentDto {
    private Long id;
    private String name;
    private ClinicLabDto lab;
    private ClientDto client;
    private LocalDate visitDate;
}
