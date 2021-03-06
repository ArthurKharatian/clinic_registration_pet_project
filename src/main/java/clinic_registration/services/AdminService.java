package clinic_registration.services;

import clinic_registration.db.entity.AdminEntity;
import clinic_registration.db.repository.AdminRepository;
import clinic_registration.dto.Admin;
import clinic_registration.exceptions.ClinicServiceException;
import clinic_registration.exceptions.ErrorMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final ObjectMapper objectMapper;
    private static final String EXC_MESSAGE = "Admin with id %d is not found";

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
                new ClinicServiceException(String.format(EXC_MESSAGE, id), ErrorMessage.NOT_FOUND));
        return objectMapper.convertValue(adminEntity, Admin.class);
    }


    @Transactional
    public void update(Admin admin) {

        if (!adminRepository.existsById(admin.getId())) {
            throw new ClinicServiceException(String.format(EXC_MESSAGE, admin.getId()),
                    ErrorMessage.NOT_FOUND);
        }
        adminRepository.save(objectMapper.convertValue(admin, AdminEntity.class));
    }
    @Transactional
    public void delete(Long id) {
        if (!adminRepository.existsById(id)) {
            throw new ClinicServiceException(String.format(EXC_MESSAGE, id), ErrorMessage.NOT_FOUND);
        }
        adminRepository.deleteById(id);
    }
}
