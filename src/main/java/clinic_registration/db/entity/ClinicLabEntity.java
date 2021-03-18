package clinic_registration.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

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
    private String worker_name;
    private String position_name;
    private String open_time;
    private String close_time;

    @OneToOne(fetch = FetchType.EAGER)
    private ClinicBranchEntity branch;
}
