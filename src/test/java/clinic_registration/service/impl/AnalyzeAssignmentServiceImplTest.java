package clinic_registration.service.impl;

import clinic_registration.db.entity.AnalyzeAssignment;
import clinic_registration.db.entity.Status;
import clinic_registration.db.repository.AnalyzeAssignmentRepository;
import clinic_registration.dto.AnalyzeAssignmentDto;
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
public class AnalyzeAssignmentServiceImplTest {

    @InjectMocks
    private AnalyzeAssignmentServiceImpl analyzeService;

    @Mock
    private AnalyzeAssignmentRepository analyzeRepository;

    @Spy
    private ObjectMapper objectMapper;

    @Test
    public void create() {
        AnalyzeAssignment analyze = new AnalyzeAssignment();
        analyze.setId(1L);
        String status = String.valueOf(Status.CREATED);
        analyze.setStatus(status);

        when(analyzeRepository.save(analyze)).thenReturn(analyze);

        AnalyzeAssignmentDto analyzeDto = new AnalyzeAssignmentDto();
        analyzeDto.setId(1L);

        AnalyzeAssignmentDto result = analyzeService.create(analyzeDto);
        verify(analyzeRepository, times(1)).save(analyze);
        assertEquals(status, result.getStatus());
    }

    @Test(expected = ClinicServiceException.class)
    public void create_nullDto() {
        analyzeService.create(null);
    }

    @Test(expected = ClinicServiceException.class)
    public void create_dtoExists() {
        AnalyzeAssignment analyze = new AnalyzeAssignment();
        analyze.setId(1L);

        when(analyzeRepository.findById(anyLong())).thenReturn(Optional.of(analyze));

        AnalyzeAssignmentDto analyzeDto = new AnalyzeAssignmentDto();
        analyzeDto.setId(1L);
        analyzeService.create(analyzeDto);
    }

    @Test
    public void read() {
        AnalyzeAssignment analyze = new AnalyzeAssignment();
        analyze.setId(1L);

        when(analyzeRepository.findById(1L)).thenReturn(Optional.of(analyze));

        AnalyzeAssignmentDto result = analyzeService.read(1L);
        assertEquals(analyze.getId(), result.getId());

    }

    @Test(expected = ClinicServiceException.class)
    public void read_notFound() {
        analyzeService.read(1L);
    }

    @Test
    public void update() {
        AnalyzeAssignment analyze = new AnalyzeAssignment();
        analyze.setId(1L);
        String status = String.valueOf(Status.UPDATED);
        analyze.setStatus(status);


        when(analyzeRepository.findById(1L)).thenReturn(Optional.of(analyze));
        when(analyzeRepository.save(analyze)).thenReturn(analyze);

        AnalyzeAssignmentDto analyzeDto = new AnalyzeAssignmentDto();
        analyzeDto.setId(1L);

        AnalyzeAssignmentDto result = analyzeService.update(analyzeDto);
        verify(analyzeRepository, times(1)).save(analyze);
        assertEquals(status, result.getStatus());
    }

    @Test(expected = ClinicServiceException.class)
    public void update_idNull() {
        analyzeService.update(new AnalyzeAssignmentDto());
    }

    @Test
    public void delete() {
        AnalyzeAssignment analyze = new AnalyzeAssignment();
        analyze.setId(1L);
        String status = String.valueOf(Status.DELETED);
        analyze.setStatus(status);

        when(analyzeRepository.findById(1L)).thenReturn(Optional.of(analyze));
        when(analyzeRepository.save(analyze)).thenReturn(analyze);

        AnalyzeAssignmentDto result = analyzeService.delete(1L);
        verify(analyzeRepository, times(1)).save(analyze);
        assertEquals(status, result.getStatus());
    }

    @Test
    public void readAll() {
        List<AnalyzeAssignment> analyzes = Arrays.asList(new AnalyzeAssignment(), new AnalyzeAssignment());
        when(analyzeRepository.findAll()).thenReturn(analyzes);

        List<AnalyzeAssignmentDto> result = analyzeService.readAll();
        assertEquals(analyzes.size(), result.size());
        assertEquals(analyzes.get(0).getId(), result.get(0).getId());
    }

    @Test
    public void createAnalyzeAssignment() {
        AnalyzeAssignment analyze = new AnalyzeAssignment();
        analyze.setId(1L);
        String status = String.valueOf(Status.CREATED);
        analyze.setStatus(status);

        when(analyzeRepository.save(analyze)).thenReturn(analyze);

        AnalyzeAssignmentDto analyzeDto = new AnalyzeAssignmentDto();
        analyzeDto.setId(1L);
        ResponseEntity<String> result = analyzeService.createAnalyze(analyzeDto);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertNotNull(result.getBody());
        assertTrue(result.getBody().contains(String.valueOf(Status.CREATED)));
    }

    @Test
    public void createAnalyzeAssignment_bodyNull() {
        ResponseEntity<String> result = analyzeService.createAnalyze(null);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void readAnalyzeAssignment() {
        AnalyzeAssignment analyze = new AnalyzeAssignment();
        analyze.setId(1L);

        when(analyzeRepository.findById(1L)).thenReturn(Optional.of(analyze));

        ResponseEntity<String> result = analyzeService.readAnalyze(1L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
    }

    @Test
    public void readAnalyzeAssignment_bodyNull() {
        ResponseEntity<String> result = analyzeService.readAnalyze(null);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void updateAnalyzeAssignment() {
        AnalyzeAssignment analyze = new AnalyzeAssignment();
        analyze.setId(1L);
        String status = String.valueOf(Status.UPDATED);
        analyze.setStatus(status);


        when(analyzeRepository.findById(1L)).thenReturn(Optional.of(analyze));
        when(analyzeRepository.save(analyze)).thenReturn(analyze);

        AnalyzeAssignmentDto analyzeDto = new AnalyzeAssignmentDto();
        analyzeDto.setId(1L);

        ResponseEntity<String> result = analyzeService.updateAnalyze(analyzeDto);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertTrue(result.getBody().contains(String.valueOf(Status.UPDATED)));
    }

    @Test
    public void updateAnalyzeAssignment_bodyNull() {
        ResponseEntity<String> result = analyzeService.updateAnalyze(null);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void deleteAnalyzeAssignment() {
        AnalyzeAssignment analyze = new AnalyzeAssignment();
        analyze.setId(1L);
        String status = String.valueOf(Status.DELETED);
        analyze.setStatus(status);

        when(analyzeRepository.findById(1L)).thenReturn(Optional.of(analyze));
        when(analyzeRepository.save(analyze)).thenReturn(analyze);

        ResponseEntity<String> result = analyzeService.deleteAnalyze(1L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertTrue(result.getBody().contains(String.valueOf(Status.DELETED)));
    }

    @Test
    public void deleteAnalyzeAssignment_bodyNull() {
        ResponseEntity<String> result = analyzeService.deleteAnalyze(null);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void getAllAnalyzeAssignments() {
        List<AnalyzeAssignment> analyzes = Arrays.asList(new AnalyzeAssignment(), new AnalyzeAssignment());
        when(analyzeRepository.findAll()).thenReturn(analyzes);

        ResponseEntity<List<AnalyzeAssignmentDto>> result = analyzeService.getAllAnalyzes();
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

}