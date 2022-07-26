package clinic_registration.db.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "public", name = "clinic_lab")
public class ClinicLab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "worker_name")
    private String workerName;

    @Column(name = "position_name")
    private String positionName;

    @Column(name = "open_time")
    private String openTime;

    @Column(name = "close_time")
    private String closeTime;

    @OneToOne(fetch = FetchType.EAGER)
    private ClinicBranch branch;

    private String status;
}
