package clinic_registration.db.repository;

import clinic_registration.db.entity.SignToTestEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SignToTestRepository extends PagingAndSortingRepository<SignToTestEntity, Long> {
    List<SignToTestEntity> findAll();
    void deleteById(Long id);
}
