package clinic_registration.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "public", name = "admin")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class AdminEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String  email;
    private String staff_name;
    private Integer phone_number;

}
