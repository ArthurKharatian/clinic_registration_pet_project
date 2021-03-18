package clinic_registration.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(schema = "public", name = "take_test_appointment")
@Getter
@Setter
@NoArgsConstructor
@ToString

public class AnalyzeAssignmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate visit_date;

    @OneToOne(fetch = FetchType.EAGER)
    private ClinicLabEntity lab;
    @OneToOne(fetch = FetchType.EAGER)
    private ClientEntity client;
}
