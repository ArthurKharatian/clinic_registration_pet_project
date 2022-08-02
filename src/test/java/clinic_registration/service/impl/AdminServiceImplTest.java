package clinic_registration.service.impl;

import clinic_registration.db.entity.Admin;
import clinic_registration.db.entity.Status;
import clinic_registration.db.repository.AdminRepository;
import clinic_registration.dto.AdminDto;
import clinic_registration.exceptions.ClinicServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AdminServiceImplTest {

    @InjectMocks
    private AdminServiceImpl adminService;

    @Mock
    private AdminRepository adminRepository;

    @Spy
    private ObjectMapper objectMapper;

    @Test
    public void create() {
        Admin admin = new Admin();
        admin.setId(1L);
        String status = String.valueOf(Status.CREATED);
        admin.setStatus(status);

        when(adminRepository.save(admin)).thenReturn(admin);

        AdminDto adminDto = new AdminDto();
        adminDto.setId(1L);

        AdminDto result = adminService.create(adminDto);
        verify(adminRepository, times(1)).save(admin);
        assertEquals(status, result.getStatus());
    }

    @Test(expected = ClinicServiceException.class)
    public void create_nullDto() {
        adminService.create(null);
    }

    @Test(expected = ClinicServiceException.class)
    public void create_dtoExists() {
        Admin admin = new Admin();
        admin.setId(1L);

        when(adminRepository.findById(anyLong())).thenReturn(Optional.of(admin));

        AdminDto adminDto = new AdminDto();
        adminDto.setId(1L);
        adminService.create(adminDto);
    }

    @Test
    public void read() {
        Admin admin = new Admin();
        admin.setId(1L);

        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));

        AdminDto result = adminService.read(1L);
        assertEquals(admin.getId(), result.getId());

    }

    @Test(expected = ClinicServiceException.class)
    public void read_notFound() {
        adminService.read(1L);
    }

    @Test
    public void update() {
        Admin admin = new Admin();
        admin.setId(1L);
        String status = String.valueOf(Status.UPDATED);
        admin.setStatus(status);


        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));
        when(adminRepository.save(admin)).thenReturn(admin);

        AdminDto adminDto = new AdminDto();
        adminDto.setId(1L);

        AdminDto result = adminService.update(adminDto);
        verify(adminRepository, times(1)).save(admin);
        assertEquals(status, result.getStatus());
    }

    @Test(expected = ClinicServiceException.class)
    public void update_idNull() {
        adminService.update(new AdminDto());
    }

    @Test
    public void delete() {
        Admin admin = new Admin();
        admin.setId(1L);
        String status = String.valueOf(Status.DELETED);
        admin.setStatus(status);

        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));
        when(adminRepository.save(admin)).thenReturn(admin);

        AdminDto result = adminService.delete(1L);
        verify(adminRepository, times(1)).save(admin);
        assertEquals(status, result.getStatus());
    }

    @Test
    public void readAll() {
        List<Admin> admins = Arrays.asList(new Admin(), new Admin());
        when(adminRepository.findAll()).thenReturn(admins);

        List<AdminDto> result = adminService.readAll();
        assertEquals(admins.size(), result.size());
        assertEquals(admins.get(0).getId(), result.get(0).getId());
    }

    @Test
    public void createAdmin() {
        Admin admin = new Admin();
        admin.setId(1L);
        String status = String.valueOf(Status.CREATED);
        admin.setStatus(status);

        when(adminRepository.save(admin)).thenReturn(admin);

        AdminDto adminDto = new AdminDto();
        adminDto.setId(1L);
        ResponseEntity<String> result = adminService.createAdmin(adminDto);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertNotNull(result.getBody());
        assertTrue(result.getBody().contains(String.valueOf(Status.CREATED)));
    }

    @Test
    public void createAdmin_bodyNull() {
        ResponseEntity<String> result = adminService.createAdmin(null);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void readAdmin() {
        Admin admin = new Admin();
        admin.setId(1L);

        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));

        ResponseEntity<String> result = adminService.readAdmin(1L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
    }

    @Test
    public void readAdmin_bodyNull() {
        ResponseEntity<String> result = adminService.readAdmin(null);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void updateAdmin() {
        Admin admin = new Admin();
        admin.setId(1L);
        String status = String.valueOf(Status.UPDATED);
        admin.setStatus(status);


        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));
        when(adminRepository.save(admin)).thenReturn(admin);

        AdminDto adminDto = new AdminDto();
        adminDto.setId(1L);

        ResponseEntity<String> result = adminService.updateAdmin(adminDto);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertTrue(result.getBody().contains(String.valueOf(Status.UPDATED)));
    }

    @Test
    public void updateAdmin_bodyNull() {
        ResponseEntity<String> result = adminService.updateAdmin(null);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void deleteAdmin() {
        Admin admin = new Admin();
        admin.setId(1L);
        String status = String.valueOf(Status.DELETED);
        admin.setStatus(status);

        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));
        when(adminRepository.save(admin)).thenReturn(admin);

        ResponseEntity<String> result = adminService.deleteAdmin(1L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertTrue(result.getBody().contains(String.valueOf(Status.DELETED)));
    }

    @Test
    public void deleteAdmin_bodyNull() {
        ResponseEntity<String> result = adminService.deleteAdmin(null);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void getAllAdmins() {
        List<Admin> admins = Arrays.asList(new Admin(), new Admin());
        when(adminRepository.findAll()).thenReturn(admins);

        ResponseEntity<List<AdminDto>> result = adminService.getAllAdmins();
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}