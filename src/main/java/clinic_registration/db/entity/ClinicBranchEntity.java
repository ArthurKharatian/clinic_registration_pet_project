package clinic_registration.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

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
}
