package clinic_registration.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "public", name = "admin")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class AdminEntity {
    @Id
    private Long id;
    private String name, email, staff_name;
    private Integer phone_number;
}
