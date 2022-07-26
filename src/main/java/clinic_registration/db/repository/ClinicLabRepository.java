package clinic_registration.db.repository;

import clinic_registration.db.entity.ClinicLab;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ClinicLabRepository extends PagingAndSortingRepository<ClinicLab, Long> {
    List<ClinicLab> findAll();
    void deleteById(Long aLong);
}
