package clinic_registration.db.repository;

import clinic_registration.db.entity.ClinicBranch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicBranchRepository extends JpaRepository<ClinicBranch, Long> {

}
