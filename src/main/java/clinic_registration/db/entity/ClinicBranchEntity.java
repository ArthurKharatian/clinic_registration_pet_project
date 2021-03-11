package clinic_registration.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "public", name = "clinic_branch")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ClinicBranchEntity {
    @Id
    private Long id;
    private String name, address, open_time, close_time;
}
