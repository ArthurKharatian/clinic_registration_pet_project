package clinic_registration.db.repository;

import clinic_registration.db.entity.ClinicBranchEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ClinicBranchRepository extends PagingAndSortingRepository<ClinicBranchEntity, Long> {
    List<ClinicBranchEntity> findAll();
    void deleteById(Long id);
}
