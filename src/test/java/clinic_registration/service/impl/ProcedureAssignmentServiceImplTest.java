package clinic_registration.service.impl;

import clinic_registration.db.entity.ProcedureAssignment;
import clinic_registration.db.entity.Status;
import clinic_registration.db.repository.ProcedureAssignmentRepository;
import clinic_registration.dto.ProcedureAssignmentDto;
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
public class ProcedureAssignmentServiceImplTest {

    @InjectMocks
    private ProcedureAssignmentServiceImpl assignmentService;

    @Mock
    private ProcedureAssignmentRepository assignmentRepository;

    @Spy
    private ObjectMapper objectMapper;

    @Test
    public void create() {
        ProcedureAssignment assignment = new ProcedureAssignment();
        assignment.setId(1L);
        String status = String.valueOf(Status.CREATED);
        assignment.setStatus(status);

        when(assignmentRepository.save(assignment)).thenReturn(assignment);

        ProcedureAssignmentDto assignmentDto = new ProcedureAssignmentDto();
        assignmentDto.setId(1L);

        ProcedureAssignmentDto result = assignmentService.create(assignmentDto);
        verify(assignmentRepository, times(1)).save(assignment);
        assertEquals(status, result.getStatus());
    }

    @Test(expected = ClinicServiceException.class)
    public void create_nullDto() {
        assignmentService.create(null);
    }

    @Test(expected = ClinicServiceException.class)
    public void create_dtoExists() {
        ProcedureAssignment assignment = new ProcedureAssignment();
        assignment.setId(1L);

        when(assignmentRepository.findById(anyLong())).thenReturn(Optional.of(assignment));

        ProcedureAssignmentDto assignmentDto = new ProcedureAssignmentDto();
        assignmentDto.setId(1L);
        assignmentService.create(assignmentDto);
    }

    @Test
    public void read() {
        ProcedureAssignment assignment = new ProcedureAssignment();
        assignment.setId(1L);

        when(assignmentRepository.findById(1L)).thenReturn(Optional.of(assignment));

        ProcedureAssignmentDto result = assignmentService.read(1L);
        assertEquals(assignment.getId(), result.getId());

    }

    @Test(expected = ClinicServiceException.class)
    public void read_notFound() {
        assignmentService.read(1L);
    }

    @Test
    public void update() {
        ProcedureAssignment assignment = new ProcedureAssignment();
        assignment.setId(1L);
        String status = String.valueOf(Status.UPDATED);
        assignment.setStatus(status);


        when(assignmentRepository.findById(1L)).thenReturn(Optional.of(assignment));
        when(assignmentRepository.save(assignment)).thenReturn(assignment);

        ProcedureAssignmentDto assignmentDto = new ProcedureAssignmentDto();
        assignmentDto.setId(1L);

        ProcedureAssignmentDto result = assignmentService.update(assignmentDto);
        verify(assignmentRepository, times(1)).save(assignment);
        assertEquals(status, result.getStatus());
    }

    @Test(expected = ClinicServiceException.class)
    public void update_idNull() {
        assignmentService.update(new ProcedureAssignmentDto());
    }

    @Test
    public void delete() {
        ProcedureAssignment assignment = new ProcedureAssignment();
        assignment.setId(1L);
        String status = String.valueOf(Status.DELETED);
        assignment.setStatus(status);

        when(assignmentRepository.findById(1L)).thenReturn(Optional.of(assignment));
        when(assignmentRepository.save(assignment)).thenReturn(assignment);

        ProcedureAssignmentDto result = assignmentService.delete(1L);
        verify(assignmentRepository, times(1)).save(assignment);
        assertEquals(status, result.getStatus());
    }

    @Test
    public void readAll() {
        List<ProcedureAssignment> assignments = Arrays.asList(new ProcedureAssignment(), new ProcedureAssignment());
        when(assignmentRepository.findAll()).thenReturn(assignments);

        List<ProcedureAssignmentDto> result = assignmentService.readAll();
        assertEquals(assignments.size(), result.size());
        assertEquals(assignments.get(0).getId(), result.get(0).getId());
    }

    @Test
    public void createProcedureAssignment() {
        ProcedureAssignment assignment = new ProcedureAssignment();
        assignment.setId(1L);
        String status = String.valueOf(Status.CREATED);
        assignment.setStatus(status);

        when(assignmentRepository.save(assignment)).thenReturn(assignment);

        ProcedureAssignmentDto assignmentDto = new ProcedureAssignmentDto();
        assignmentDto.setId(1L);
        ResponseEntity<String> result = assignmentService.createAssignment(assignmentDto);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertNotNull(result.getBody());
        assertTrue(result.getBody().contains(String.valueOf(Status.CREATED)));
    }

    @Test
    public void createProcedureAssignment_bodyNull() {
        ResponseEntity<String> result = assignmentService.createAssignment(null);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void readProcedureAssignment() {
        ProcedureAssignment assignment = new ProcedureAssignment();
        assignment.setId(1L);

        when(assignmentRepository.findById(1L)).thenReturn(Optional.of(assignment));

        ResponseEntity<String> result = assignmentService.readAssignment(1L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
    }

    @Test
    public void readProcedureAssignment_bodyNull() {
        ResponseEntity<String> result = assignmentService.readAssignment(null);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void updateProcedureAssignment() {
        ProcedureAssignment assignment = new ProcedureAssignment();
        assignment.setId(1L);
        String status = String.valueOf(Status.UPDATED);
        assignment.setStatus(status);


        when(assignmentRepository.findById(1L)).thenReturn(Optional.of(assignment));
        when(assignmentRepository.save(assignment)).thenReturn(assignment);

        ProcedureAssignmentDto assignmentDto = new ProcedureAssignmentDto();
        assignmentDto.setId(1L);

        ResponseEntity<String> result = assignmentService.updateAssignment(assignmentDto);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertTrue(result.getBody().contains(String.valueOf(Status.UPDATED)));
    }

    @Test
    public void updateProcedureAssignment_bodyNull() {
        ResponseEntity<String> result = assignmentService.updateAssignment(null);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void deleteProcedureAssignment() {
        ProcedureAssignment assignment = new ProcedureAssignment();
        assignment.setId(1L);
        String status = String.valueOf(Status.DELETED);
        assignment.setStatus(status);

        when(assignmentRepository.findById(1L)).thenReturn(Optional.of(assignment));
        when(assignmentRepository.save(assignment)).thenReturn(assignment);

        ResponseEntity<String> result = assignmentService.deleteAssignment(1L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertTrue(result.getBody().contains(String.valueOf(Status.DELETED)));
    }

    @Test
    public void deleteProcedureAssignment_bodyNull() {
        ResponseEntity<String> result = assignmentService.deleteAssignment(null);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void getAllProcedureAssignments() {
        List<ProcedureAssignment> assignments = Arrays.asList(new ProcedureAssignment(), new ProcedureAssignment());
        when(assignmentRepository.findAll()).thenReturn(assignments);

        ResponseEntity<List<ProcedureAssignmentDto>> result = assignmentService.getAllAssignments();
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

}