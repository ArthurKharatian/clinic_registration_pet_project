package clinic_registration.db.repository;

import clinic_registration.db.entity.AdminEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AdminRepository extends PagingAndSortingRepository<AdminEntity, Long> {
    List<AdminEntity> findAll();
    void deleteById(Long id);
   List<AdminEntity> findAllByName(String name);
}
