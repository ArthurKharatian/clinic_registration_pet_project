package clinic_registration.services;

import clinic_registration.db.entity.AdminEntity;
import clinic_registration.db.repository.AdminRepository;
import clinic_registration.dto.Admin;
import clinic_registration.exceptions.ClinicServiceException;
import clinic_registration.exceptions.ErrorMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final ObjectMapper objectMapper;
    private final SessionFactory sessionFactory;

    public Admin create(Admin admin) {
        if (admin == null) {
            throw new ClinicServiceException("admin is null", ErrorMessage.BAD_REQUEST);
        }
        AdminEntity adminEntity = objectMapper.convertValue(admin, AdminEntity.class);
        AdminEntity save = adminRepository.save(adminEntity);
        return objectMapper.convertValue(save, Admin.class);
    }

    public List<Admin> readAll() {
        List<AdminEntity> admins = adminRepository.findAll();
        return admins.stream().map(admin -> objectMapper.convertValue(admin, Admin.class)).collect(Collectors.toList());
    }

    public Admin read(Long id) {
        AdminEntity adminEntity = adminRepository.findById(id).orElseThrow(() ->
                new ClinicServiceException(String.format("Admin with id %d is not found", id), ErrorMessage.NOT_FOUND));
        return objectMapper.convertValue(adminEntity, Admin.class);
    }

    public void update(Admin admin) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        if (!adminRepository.existsById(admin.getId())) {
            throw new ClinicServiceException(String.format("Admin with id %d is not found", admin.getId()),
                    ErrorMessage.NOT_FOUND);
        }
        AdminEntity save = adminRepository.save(objectMapper.convertValue(admin, AdminEntity.class));
        session.update(save);
        session.flush();
        transaction.commit();

        session.close();
    }

    public void delete(Long id) {
        if (!adminRepository.existsById(id)) {
            throw new ClinicServiceException(String.format("Admin with id %d is not found", id), ErrorMessage.NOT_FOUND);
        }
        adminRepository.deleteById(id);
    }
}
