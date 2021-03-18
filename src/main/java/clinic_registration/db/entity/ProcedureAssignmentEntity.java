package clinic_registration.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(schema = "public", name = "procedure_appointment")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProcedureAssignmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate visit_date;

    @OneToOne(fetch = FetchType.EAGER)
    private ClinicProcedureEntity procedure;
    @OneToOne(fetch = FetchType.EAGER)
    private ClientEntity client;
    @OneToOne(fetch = FetchType.EAGER)
    private ClinicBranchEntity branch;
}
