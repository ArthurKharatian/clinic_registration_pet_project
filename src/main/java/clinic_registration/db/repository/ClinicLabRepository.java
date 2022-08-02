package clinic_registration.db.repository;

import clinic_registration.db.entity.ClinicLab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicLabRepository extends JpaRepository<ClinicLab, Long> {

}
