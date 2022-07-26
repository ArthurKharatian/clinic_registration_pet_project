package clinic_registration.db.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "public", name = "clinic_procedure")
public class ClinicProcedure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer duration;

    private String status;
}
