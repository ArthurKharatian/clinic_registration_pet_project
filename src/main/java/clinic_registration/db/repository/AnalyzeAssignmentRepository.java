package clinic_registration.db.repository;

import clinic_registration.db.entity.AnalyzeAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalyzeAssignmentRepository extends JpaRepository<AnalyzeAssignment, Long> {

}
