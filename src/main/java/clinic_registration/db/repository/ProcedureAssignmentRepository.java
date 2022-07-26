package clinic_registration.db.repository;

import clinic_registration.db.entity.ProcedureAssignment;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProcedureAssignmentRepository extends PagingAndSortingRepository<ProcedureAssignment, Long> {
    List<ProcedureAssignment> findAll();
    void deleteById(Long id);
}
