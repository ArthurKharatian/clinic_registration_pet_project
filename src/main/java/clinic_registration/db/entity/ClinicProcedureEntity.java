package clinic_registration.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "public", name = "clinic_procedure")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ClinicProcedureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer duration;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "procedure_id")
    private List<ProcedureAssignmentEntity> procedureEntities;
}
