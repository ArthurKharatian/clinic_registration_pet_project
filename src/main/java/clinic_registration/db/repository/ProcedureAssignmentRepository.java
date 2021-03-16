package clinic_registration.db.repository;

import clinic_registration.db.entity.ProcedureAssignmentEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProcedureAssignmentRepository extends PagingAndSortingRepository<ProcedureAssignmentEntity, Long> {
    List<ProcedureAssignmentEntity> findAll();
    void deleteById(Long id);
}
