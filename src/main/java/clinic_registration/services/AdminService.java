package clinic_registration.services;

import clinic_registration.db.entity.AdminEntity;
import clinic_registration.db.repository.AdminRepository;
import clinic_registration.dto.Admin;
import clinic_registration.exceptions.CreateException;
import clinic_registration.exceptions.DeleteException;
import clinic_registration.exceptions.UpdateException;
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

    public String create(Admin admin) {
        AdminEntity adminEntity;
        try {
            adminEntity = objectMapper.convertValue(admin, AdminEntity.class);
            adminRepository.save(adminEntity);
        } catch (RuntimeException e) {
            throw new CreateException("Can not create an admin");
        }
        return adminEntity.toString() + "is created";
    }

    public List<Admin> readAll() {
        List<AdminEntity> admins = adminRepository.findAll();
        return admins.stream().map(admin -> objectMapper.convertValue(admin, Admin.class)).collect(Collectors.toList());
    }

    public Admin read(Long id) {
        Admin admin = null;
        if (adminRepository.findById(id).isPresent()) {
            AdminEntity adminEntity = adminRepository.findById(id).get();
            admin = objectMapper.convertValue(adminEntity, Admin.class);
        }
        return admin;
    }

    public String update(Long id, Admin admin) {
        if (adminRepository.findById(id).isPresent()) {
            try {
                AdminEntity adminEntity = objectMapper.convertValue(admin, AdminEntity.class);
                adminRepository.save(adminEntity);
                return admin.toString() + " is updated!";
            } catch (RuntimeException e) {
                throw new UpdateException("Admin is not found!");
            }
        }
        return "Admin " + admin.toString() + " is not found!";
    }

    public String delete(Long id) {
        try {
            adminRepository.deleteById(id);
            return "Admin with id: " + id + " was deleted!";
        }catch (RuntimeException e) {
            throw new DeleteException("Admin is not found!");
        }
    }
}
