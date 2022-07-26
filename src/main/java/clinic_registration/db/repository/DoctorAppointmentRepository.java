package clinic_registration.db.repository;

import clinic_registration.db.entity.DoctorAppointment;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface DoctorAppointmentRepository extends PagingAndSortingRepository<DoctorAppointment, Long> {
    List<DoctorAppointment> findAll();
    void deleteById(Long id);
}
