package clinic_registration.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(schema = "public", name = "take_test_appointment")
@Getter
@Setter
@NoArgsConstructor
@ToString

public class AnalyzeAssignmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long lab_id;
    private Long client_id;
    private LocalDate visit_date;
}
