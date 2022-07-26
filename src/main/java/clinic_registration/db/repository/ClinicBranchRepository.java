package clinic_registration.db.repository;

import clinic_registration.db.entity.ClinicBranch;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ClinicBranchRepository extends PagingAndSortingRepository<ClinicBranch, Long> {
    List<ClinicBranch> findAll();
    void deleteById(Long id);
}
