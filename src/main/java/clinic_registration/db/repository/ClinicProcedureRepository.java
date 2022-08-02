package clinic_registration.db.repository;

import clinic_registration.db.entity.ClinicProcedure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicProcedureRepository extends JpaRepository<ClinicProcedure, Long> {

}
