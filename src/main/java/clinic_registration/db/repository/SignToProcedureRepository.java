package clinic_registration.db.repository;

import clinic_registration.db.entity.SignToProcedureEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SignToProcedureRepository extends PagingAndSortingRepository<SignToProcedureEntity, Long> {
    List<SignToProcedureEntity> findAll();
    void deleteById(Long id);
}
