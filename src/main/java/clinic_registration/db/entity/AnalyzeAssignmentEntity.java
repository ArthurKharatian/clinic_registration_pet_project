package clinic_registration.db.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@Table(schema = "public", name = "take_test_appointment")
public class AnalyzeAssignmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(name = "visit_date")
    private LocalDate visitDate;

    @OneToOne(fetch = FetchType.EAGER)
    private ClinicLabEntity lab;

    @OneToOne(fetch = FetchType.EAGER)
    private ClientEntity client;
}
