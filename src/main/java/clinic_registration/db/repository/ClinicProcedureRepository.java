package clinic_registration.db.repository;

import clinic_registration.db.entity.ClinicProcedureEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ClinicProcedureRepository extends PagingAndSortingRepository<ClinicProcedureEntity, Long> {
    List<ClinicProcedureEntity> findAll();
    void deleteById(Long id);
}
