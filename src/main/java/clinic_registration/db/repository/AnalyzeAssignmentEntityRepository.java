package clinic_registration.db.repository;

import clinic_registration.db.entity.AnalyzeAssignment;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AnalyzeAssignmentEntityRepository extends PagingAndSortingRepository<AnalyzeAssignment, Long> {
    List<AnalyzeAssignment> findAll();
    void deleteById(Long id);
}
