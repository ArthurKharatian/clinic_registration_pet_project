package clinic_registration.db.repository;

import clinic_registration.db.entity.AnalyzeAssignmentEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AnalyzeAssignmentEntityRepository extends PagingAndSortingRepository<AnalyzeAssignmentEntity, Long> {
    List<AnalyzeAssignmentEntity> findAll();
    void deleteById(Long id);
}
