package clinic_registration.db.repository;

import clinic_registration.db.entity.SignToDoctorEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SignToDoctorRepository extends PagingAndSortingRepository<SignToDoctorEntity, Long> {
    List<SignToDoctorEntity> findAll();
    void deleteById(Long id);
}
