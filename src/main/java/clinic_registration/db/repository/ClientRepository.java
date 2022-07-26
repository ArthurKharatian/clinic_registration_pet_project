package clinic_registration.db.repository;

import clinic_registration.db.entity.Client;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {

    List<Client> findAll();
    void deleteById(Long id);
}
