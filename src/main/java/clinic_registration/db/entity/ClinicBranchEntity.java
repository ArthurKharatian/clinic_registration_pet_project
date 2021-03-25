package clinic_registration.db.entity;

import lombok.*;

import javax.persistence.*;
@Data
@Entity
@NoArgsConstructor
@Table(schema = "public", name = "clinic_branch")
public class ClinicBranchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;

    @Column(name = "open_time")
    private String openTime;

    @Column(name = "close_time")
    private String closeTime;

    @OneToOne(fetch = FetchType.EAGER)
    private AdminEntity admin;

}
