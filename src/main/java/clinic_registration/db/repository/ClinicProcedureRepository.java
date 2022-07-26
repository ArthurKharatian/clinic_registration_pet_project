package clinic_registration.db.repository;

import clinic_registration.db.entity.ClinicProcedure;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ClinicProcedureRepository extends PagingAndSortingRepository<ClinicProcedure, Long> {
    List<ClinicProcedure> findAll();
    void deleteById(Long id);
}
