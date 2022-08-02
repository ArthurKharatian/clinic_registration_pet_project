package clinic_registration.db.repository;

import clinic_registration.db.entity.ProcedureAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcedureAssignmentRepository extends JpaRepository<ProcedureAssignment, Long> {

}
