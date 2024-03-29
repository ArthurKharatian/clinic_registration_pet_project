package clinic_registration.db.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "public", name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    @Column(name = "staff_name")
    private String staffName;

    @Column(name = "phone_number")
    private Integer phoneNumber;

    private String status;
}
