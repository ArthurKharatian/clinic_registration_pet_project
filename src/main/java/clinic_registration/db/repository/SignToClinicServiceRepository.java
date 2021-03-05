package clinic_registration.db.repository;

import clinic_registration.db.entity.SignToClinicServiceEntity;
import clinic_registration.db.entity.SignToDoctorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SignToClinicServiceRepository extends PagingAndSortingRepository<SignToClinicServiceEntity, Integer> {
    List<SignToClinicServiceEntity> findAll();

    @Query("select s from SignToClinicServiceEntity s where s.id = :id")
    List<SignToClinicServiceEntity> findSignToClinicServiceEntityByQuery(Integer id);

    void deleteById(Integer id);
}
