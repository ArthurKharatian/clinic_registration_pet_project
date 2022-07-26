package clinic_registration.db.repository;

import clinic_registration.db.entity.Admin;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AdminRepository extends PagingAndSortingRepository<Admin, Long> {
    List<Admin> findAll();
    void deleteById(Long id);
   List<Admin> findAllByName(String name);
}
