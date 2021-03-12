package clinic_registration.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "admin")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class AdminEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String  email;
    private String staff_name;
    private Integer phone_number;
}
