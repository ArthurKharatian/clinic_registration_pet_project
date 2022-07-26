package clinic_registration.db.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(schema = "public", name = "procedure_appointment")
public class ProcedureAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "visit_date")
    private LocalDate visitDate;

    @OneToOne(fetch = FetchType.EAGER)
    private ClinicProcedure procedure;

    @OneToOne(fetch = FetchType.EAGER)
    private Client client;

    @OneToOne(fetch = FetchType.EAGER)
    private ClinicBranch branch;

    private String status;
}
