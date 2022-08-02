package clinic_registration.service.impl;

import clinic_registration.db.entity.Admin;
import clinic_registration.db.entity.Status;
import clinic_registration.db.repository.AdminRepository;
import clinic_registration.dto.AdminDto;
import clinic_registration.exceptions.ClinicServiceException;
import clinic_registration.service.AdminService;
import clinic_registration.utils.JsonConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final ObjectMapper objectMapper;
    private static final String EXC_MESSAGE = "Admin with id %d is not found";

    @Override
    public AdminDto create(AdminDto adminDto) {
        if (adminDto == null) {
            throw new ClinicServiceException("Admin is null");
        }

        Long id = adminDto.getId();
        if (id != null && adminRepository.findById(id).isPresent()) {
            throw new ClinicServiceException(String.format("Admin with id %d already exists", id));
        }

        Admin admin = objectMapper.convertValue(adminDto, Admin.class);
        admin.setStatus(String.valueOf(Status.CREATED));
        Admin save = adminRepository.save(admin);
        return objectMapper.convertValue(save, AdminDto.class);
    }

    @Override
    public AdminDto read(Long id) {
        Admin admin = adminRepository.findById(id).orElseThrow(() ->
                new ClinicServiceException(String.format(EXC_MESSAGE, id)));
        return objectMapper.convertValue(admin, AdminDto.class);
    }

    @Override
    public AdminDto update(AdminDto adminDto) {
        Long id = adminDto.getId();
        if (id == null) {
            throw new ClinicServiceException("id is null");
        }

        read(id);

        Admin admin = objectMapper.convertValue(adminDto, Admin.class);
        admin.setStatus(String.valueOf(Status.UPDATED));
        Admin save = adminRepository.save(admin);
        return objectMapper.convertValue(save, AdminDto.class);
    }

    @Override
    public AdminDto delete(Long id) {
        Admin admin = objectMapper.convertValue(read(id), Admin.class);
        admin.setStatus(String.valueOf(Status.DELETED));
        Admin save = adminRepository.save(admin);
        return objectMapper.convertValue(save, AdminDto.class);
    }

    @Override
    public List<AdminDto> readAll() {
        return adminRepository.findAll().stream()
                .map(admin -> objectMapper.convertValue(admin, AdminDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<String> createAdmin(AdminDto adminDto) {
        if (adminDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Admin is not created! Body is null");
        }

        AdminDto admin = create(adminDto);
        String dto = JsonConverter.getString(admin, objectMapper);

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @Override
    public ResponseEntity<String> readAdmin(Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin is not found! Id is null");
        }

        AdminDto admin = read(id);
        String dto = JsonConverter.getString(admin, objectMapper);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateAdmin(AdminDto adminDto) {
        if (adminDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Admin is not updated! Body is null");
        }

        AdminDto admin = update(adminDto);
        String dto = JsonConverter.getString(admin, objectMapper);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @Override
    public ResponseEntity<String> deleteAdmin(Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Admin assignment is not deleted! Id is null");
        }

        AdminDto admin = delete(id);
        String dto = JsonConverter.getString(admin, objectMapper);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @Override
    public ResponseEntity<List<AdminDto>> getAllAdmins() {
        return ResponseEntity.ok(readAll());
    }
}
