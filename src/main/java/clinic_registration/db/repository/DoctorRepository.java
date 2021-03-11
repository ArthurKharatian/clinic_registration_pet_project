package clinic_registration.db.repository;

import clinic_registration.db.entity.DoctorEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface DoctorRepository extends PagingAndSortingRepository<DoctorEntity, Long> {
    List<DoctorEntity> findAll();
    void deleteById(Long id);
}
