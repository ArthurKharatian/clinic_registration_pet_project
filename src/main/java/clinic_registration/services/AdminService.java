package clinic_registration.services;

import clinic_registration.db.entity.AdminEntity;
import clinic_registration.db.repository.AdminRepository;
import clinic_registration.dto.Admin;
import clinic_registration.exceptions.ClinicServiceException;
import clinic_registration.exceptions.ErrorMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {
    private final AdminRepository adminRepository;
    private final ObjectMapper objectMapper;


    public AdminService(AdminRepository adminRepository, ObjectMapper objectMapper) {
        this.adminRepository = adminRepository;
        this.objectMapper = objectMapper;
    }

    public void create(Admin admin) {
        if(admin == null){throw new ClinicServiceException("admin is null", ErrorMessage.UNKNOWN);}
        AdminEntity adminEntity = objectMapper.convertValue(admin, AdminEntity.class);
        adminRepository.save(adminEntity);
    }

    public List<Admin> readAll() {
        List<AdminEntity> admins = adminRepository.findAll();
        return admins.stream().map(admin -> objectMapper.convertValue(admin, Admin.class)).collect(Collectors.toList());
    }

    public Admin read(Long id) {
      AdminEntity adminEntity = adminRepository.findById(id).orElseThrow(()->
               new ClinicServiceException(String.format("Admin with id %d is not found", id), ErrorMessage.NOT_FOUND));
      return objectMapper.convertValue(adminEntity, Admin.class);
    }

    public void update(Long id, Admin admin) {
        if (adminRepository.existsById(id)) {
            admin.setId(id);
            adminRepository.save(objectMapper.convertValue(admin, AdminEntity.class));
        } else {
            throw new ClinicServiceException(String.format("Admin with id %d is not found", id), ErrorMessage.NOT_FOUND);
        }

    }
    public void delete(Long id) {
            if(!adminRepository.existsById(id)){throw new ClinicServiceException(String.format("Admin with id %d is not found", id), ErrorMessage.NOT_FOUND);}
            adminRepository.deleteById(id);
    }
}
