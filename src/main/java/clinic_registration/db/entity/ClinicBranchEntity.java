package clinic_registration.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "public", name = "clinic_branch")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ClinicBranchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    private String open_time;
    private String close_time;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    private List<SignToDoctorEntity> signToDoctorEntities;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    private List<SignToProcedureEntity> procedureEntities;
}
