package clinic_registration.db.repository;

import clinic_registration.db.entity.ClientEntity;
import clinic_registration.db.entity.SignToDoctorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SignToDoctorRepository extends PagingAndSortingRepository<SignToDoctorEntity, Integer> {
    List<SignToDoctorEntity> findAll();

    @Query("select s from SignToDoctorEntity s where s.id = :id")
    List<SignToDoctorEntity> findSignToDoctorEntityByQuery(Integer id);

    void deleteById(Integer id);


}
