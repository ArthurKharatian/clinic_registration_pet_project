package clinic_registration.service.impl;

import clinic_registration.db.entity.ClinicBranch;
import clinic_registration.db.entity.Status;
import clinic_registration.db.repository.ClinicBranchRepository;
import clinic_registration.dto.ClinicBranchDto;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClinicBranchServiceImplTest {

    @InjectMocks
    private ClinicBranchServiceImpl branchService;

    @Mock
    private ClinicBranchRepository branchRepository;

    @Spy
    private ObjectMapper objectMapper;

    @Test
    public void create() {
        ClinicBranch branch = new ClinicBranch();
        branch.setId(1L);
        String status = String.valueOf(Status.CREATED);
        branch.setStatus(status);

        when(branchRepository.save(branch)).thenReturn(branch);

        ClinicBranchDto branchDto = new ClinicBranchDto();
        branchDto.setId(1L);

        ClinicBranchDto result = branchService.create(branchDto);
        verify(branchRepository, times(1)).save(branch);
        assertEquals(status, result.getStatus());
    }

    @Test(expected = ClinicServiceException.class)
    public void create_nullDto() {
        branchService.create(null);
    }

    @Test(expected = ClinicServiceException.class)
    public void create_dtoExists() {
        ClinicBranch branch = new ClinicBranch();
        branch.setId(1L);

        when(branchRepository.findById(anyLong())).thenReturn(Optional.of(branch));

        ClinicBranchDto branchDto = new ClinicBranchDto();
        branchDto.setId(1L);
        branchService.create(branchDto);
    }

    @Test
    public void read() {
        ClinicBranch branch = new ClinicBranch();
        branch.setId(1L);

        when(branchRepository.findById(1L)).thenReturn(Optional.of(branch));

        ClinicBranchDto result = branchService.read(1L);
        assertEquals(branch.getId(), result.getId());

    }

    @Test(expected = ClinicServiceException.class)
    public void read_notFound() {
        branchService.read(1L);
    }

    @Test
    public void update() {
        ClinicBranch branch = new ClinicBranch();
        branch.setId(1L);
        String status = String.valueOf(Status.UPDATED);
        branch.setStatus(status);


        when(branchRepository.findById(1L)).thenReturn(Optional.of(branch));
        when(branchRepository.save(branch)).thenReturn(branch);

        ClinicBranchDto branchDto = new ClinicBranchDto();
        branchDto.setId(1L);

        ClinicBranchDto result = branchService.update(branchDto);
        verify(branchRepository, times(1)).save(branch);
        assertEquals(status, result.getStatus());
    }

    @Test(expected = ClinicServiceException.class)
    public void update_idNull() {
        branchService.update(new ClinicBranchDto());
    }

    @Test
    public void delete() {
        ClinicBranch branch = new ClinicBranch();
        branch.setId(1L);
        String status = String.valueOf(Status.DELETED);
        branch.setStatus(status);

        when(branchRepository.findById(1L)).thenReturn(Optional.of(branch));
        when(branchRepository.save(branch)).thenReturn(branch);

        ClinicBranchDto result = branchService.delete(1L);
        verify(branchRepository, times(1)).save(branch);
        assertEquals(status, result.getStatus());
    }

    @Test
    public void readAll() {
        List<ClinicBranch> branches = Arrays.asList(new ClinicBranch(), new ClinicBranch());
        when(branchRepository.findAll()).thenReturn(branches);

        List<ClinicBranchDto> result = branchService.readAll();
        assertEquals(branches.size(), result.size());
        assertEquals(branches.get(0).getId(), result.get(0).getId());
    }

    @Test
    public void createClinicBranch() {
        ClinicBranch branch = new ClinicBranch();
        branch.setId(1L);
        String status = String.valueOf(Status.CREATED);
        branch.setStatus(status);

        when(branchRepository.save(branch)).thenReturn(branch);

        ClinicBranchDto branchDto = new ClinicBranchDto();
        branchDto.setId(1L);
        ResponseEntity<String> result = branchService.createBranch(branchDto);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertNotNull(result.getBody());
        assertTrue(result.getBody().contains(String.valueOf(Status.CREATED)));
    }

    @Test
    public void createClinicBranch_bodyNull() {
        ResponseEntity<String> result = branchService.createBranch(null);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void readClinicBranch() {
        ClinicBranch branch = new ClinicBranch();
        branch.setId(1L);

        when(branchRepository.findById(1L)).thenReturn(Optional.of(branch));

        ResponseEntity<String> result = branchService.readBranch(1L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
    }

    @Test
    public void readClinicBranch_bodyNull() {
        ResponseEntity<String> result = branchService.readBranch(null);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void updateClinicBranch() {
        ClinicBranch branch = new ClinicBranch();
        branch.setId(1L);
        String status = String.valueOf(Status.UPDATED);
        branch.setStatus(status);


        when(branchRepository.findById(1L)).thenReturn(Optional.of(branch));
        when(branchRepository.save(branch)).thenReturn(branch);

        ClinicBranchDto branchDto = new ClinicBranchDto();
        branchDto.setId(1L);

        ResponseEntity<String> result = branchService.updateBranch(branchDto);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertTrue(result.getBody().contains(String.valueOf(Status.UPDATED)));
    }

    @Test
    public void updateClinicBranch_bodyNull() {
        ResponseEntity<String> result = branchService.updateBranch(null);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void deleteClinicBranch() {
        ClinicBranch branch = new ClinicBranch();
        branch.setId(1L);
        String status = String.valueOf(Status.DELETED);
        branch.setStatus(status);

        when(branchRepository.findById(1L)).thenReturn(Optional.of(branch));
        when(branchRepository.save(branch)).thenReturn(branch);

        ResponseEntity<String> result = branchService.deleteBranch(1L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertTrue(result.getBody().contains(String.valueOf(Status.DELETED)));
    }

    @Test
    public void deleteClinicBranch_bodyNull() {
        ResponseEntity<String> result = branchService.deleteBranch(null);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void getAllClinicBranches() {
        List<ClinicBranch> branches = Arrays.asList(new ClinicBranch(), new ClinicBranch());
        when(branchRepository.findAll()).thenReturn(branches);

        ResponseEntity<List<ClinicBranchDto>> result = branchService.getAllBranches();
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

}