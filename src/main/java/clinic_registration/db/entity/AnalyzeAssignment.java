package clinic_registration.db.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(schema = "public", name = "take_test_appointment")
public class AnalyzeAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(name = "visit_date")
    private LocalDate visitDate;

    @OneToOne(fetch = FetchType.EAGER)
    private ClinicLab lab;

    @OneToOne(fetch = FetchType.EAGER)
    private Client client;

        private String status;
}
