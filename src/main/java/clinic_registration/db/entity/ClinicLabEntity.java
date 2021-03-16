package clinic_registration.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "public", name = "clinic_lab")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ClinicLabEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long branch_id;
    private String worker_name;
    private String position_name;
    private String open_time;
    private String close_time;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "lab_id")
    private List<AnalyzeAssignmentEntity> testEntities;
}
