package clinic_registration.db.repository;

import clinic_registration.db.entity.Doctor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface DoctorRepository extends PagingAndSortingRepository<Doctor, Long> {
    List<Doctor> findAll();
    void deleteById(Long id);
}
