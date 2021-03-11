package clinic_registration.db.repository;

import clinic_registration.db.entity.ClientEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ClientRepository extends PagingAndSortingRepository<ClientEntity, Long> {

    List<ClientEntity> findAll();
    void deleteById(Long id);
}
