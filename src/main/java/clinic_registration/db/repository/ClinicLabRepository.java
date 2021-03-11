package clinic_registration.db.repository;

import clinic_registration.db.entity.ClinicLabEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ClinicLabRepository extends PagingAndSortingRepository<ClinicLabEntity, Long> {
    List<ClinicLabEntity> findAll();
    void deleteById(Long aLong);
}
