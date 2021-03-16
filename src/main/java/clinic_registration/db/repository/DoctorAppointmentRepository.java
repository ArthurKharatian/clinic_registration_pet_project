package clinic_registration.db.repository;

import clinic_registration.db.entity.DoctorAppointmentEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface DoctorAppointmentRepository extends PagingAndSortingRepository<DoctorAppointmentEntity, Long> {
    List<DoctorAppointmentEntity> findAll();
    void deleteById(Long id);
}
