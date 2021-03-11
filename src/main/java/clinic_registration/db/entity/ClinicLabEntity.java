package clinic_registration.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "public", name = "clinic_lab")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ClinicLabEntity {
    @Id
    private Long id;
    private String worker_name, position_name, open_time, close_time;
}
