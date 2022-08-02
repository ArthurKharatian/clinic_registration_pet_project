package clinic_registration.service.impl;

import clinic_registration.db.entity.ClinicProcedure;
import clinic_registration.db.entity.Status;
import clinic_registration.db.repository.ClinicProcedureRepository;
import clinic_registration.dto.ClinicProcedureDto;
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
public class ClinicProcedureServiceImplTest {

    @InjectMocks
    private ClinicProcedureServiceImpl procedureService;

    @Mock
    private ClinicProcedureRepository procedureRepository;

    @Spy
    private ObjectMapper objectMapper;

    @Test
    public void create() {
        ClinicProcedure procedure = new ClinicProcedure();
        procedure.setId(1L);
        String status = String.valueOf(Status.CREATED);
        procedure.setStatus(status);

        when(procedureRepository.save(procedure)).thenReturn(procedure);

        ClinicProcedureDto procedureDto = new ClinicProcedureDto();
        procedureDto.setId(1L);

        ClinicProcedureDto result = procedureService.create(procedureDto);
        verify(procedureRepository, times(1)).save(procedure);
        assertEquals(status, result.getStatus());
    }

    @Test(expected = ClinicServiceException.class)
    public void create_nullDto() {
        procedureService.create(null);
    }

    @Test(expected = ClinicServiceException.class)
    public void create_dtoExists() {
        ClinicProcedure procedure = new ClinicProcedure();
        procedure.setId(1L);

        when(procedureRepository.findById(anyLong())).thenReturn(Optional.of(procedure));

        ClinicProcedureDto procedureDto = new ClinicProcedureDto();
        procedureDto.setId(1L);
        procedureService.create(procedureDto);
    }

    @Test
    public void read() {
        ClinicProcedure procedure = new ClinicProcedure();
        procedure.setId(1L);

        when(procedureRepository.findById(1L)).thenReturn(Optional.of(procedure));

        ClinicProcedureDto result = procedureService.read(1L);
        assertEquals(procedure.getId(), result.getId());

    }

    @Test(expected = ClinicServiceException.class)
    public void read_notFound() {
        procedureService.read(1L);
    }

    @Test
    public void update() {
        ClinicProcedure procedure = new ClinicProcedure();
        procedure.setId(1L);
        String status = String.valueOf(Status.UPDATED);
        procedure.setStatus(status);


        when(procedureRepository.findById(1L)).thenReturn(Optional.of(procedure));
        when(procedureRepository.save(procedure)).thenReturn(procedure);

        ClinicProcedureDto procedureDto = new ClinicProcedureDto();
        procedureDto.setId(1L);

        ClinicProcedureDto result = procedureService.update(procedureDto);
        verify(procedureRepository, times(1)).save(procedure);
        assertEquals(status, result.getStatus());
    }

    @Test(expected = ClinicServiceException.class)
    public void update_idNull() {
        procedureService.update(new ClinicProcedureDto());
    }

    @Test
    public void delete() {
        ClinicProcedure procedure = new ClinicProcedure();
        procedure.setId(1L);
        String status = String.valueOf(Status.DELETED);
        procedure.setStatus(status);

        when(procedureRepository.findById(1L)).thenReturn(Optional.of(procedure));
        when(procedureRepository.save(procedure)).thenReturn(procedure);

        ClinicProcedureDto result = procedureService.delete(1L);
        verify(procedureRepository, times(1)).save(procedure);
        assertEquals(status, result.getStatus());
    }

    @Test
    public void readAll() {
        List<ClinicProcedure> procedures = Arrays.asList(new ClinicProcedure(), new ClinicProcedure());
        when(procedureRepository.findAll()).thenReturn(procedures);

        List<ClinicProcedureDto> result = procedureService.readAll();
        assertEquals(procedures.size(), result.size());
        assertEquals(procedures.get(0).getId(), result.get(0).getId());
    }

    @Test
    public void createClinicProcedure() {
        ClinicProcedure procedure = new ClinicProcedure();
        procedure.setId(1L);
        String status = String.valueOf(Status.CREATED);
        procedure.setStatus(status);

        when(procedureRepository.save(procedure)).thenReturn(procedure);

        ClinicProcedureDto procedureDto = new ClinicProcedureDto();
        procedureDto.setId(1L);
        ResponseEntity<String> result = procedureService.createProcedure(procedureDto);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertNotNull(result.getBody());
        assertTrue(result.getBody().contains(String.valueOf(Status.CREATED)));
    }

    @Test
    public void createClinicProcedure_bodyNull() {
        ResponseEntity<String> result = procedureService.createProcedure(null);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void readClinicProcedure() {
        ClinicProcedure procedure = new ClinicProcedure();
        procedure.setId(1L);

        when(procedureRepository.findById(1L)).thenReturn(Optional.of(procedure));

        ResponseEntity<String> result = procedureService.readProcedure(1L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
    }

    @Test
    public void readClinicProcedure_bodyNull() {
        ResponseEntity<String> result = procedureService.readProcedure(null);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void updateClinicProcedure() {
        ClinicProcedure procedure = new ClinicProcedure();
        procedure.setId(1L);
        String status = String.valueOf(Status.UPDATED);
        procedure.setStatus(status);


        when(procedureRepository.findById(1L)).thenReturn(Optional.of(procedure));
        when(procedureRepository.save(procedure)).thenReturn(procedure);

        ClinicProcedureDto procedureDto = new ClinicProcedureDto();
        procedureDto.setId(1L);

        ResponseEntity<String> result = procedureService.updateProcedure(procedureDto);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertTrue(result.getBody().contains(String.valueOf(Status.UPDATED)));
    }

    @Test
    public void updateClinicProcedure_bodyNull() {
        ResponseEntity<String> result = procedureService.updateProcedure(null);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void deleteClinicProcedure() {
        ClinicProcedure procedure = new ClinicProcedure();
        procedure.setId(1L);
        String status = String.valueOf(Status.DELETED);
        procedure.setStatus(status);

        when(procedureRepository.findById(1L)).thenReturn(Optional.of(procedure));
        when(procedureRepository.save(procedure)).thenReturn(procedure);

        ResponseEntity<String> result = procedureService.deleteProcedure(1L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertTrue(result.getBody().contains(String.valueOf(Status.DELETED)));
    }

    @Test
    public void deleteClinicProcedure_bodyNull() {
        ResponseEntity<String> result = procedureService.deleteProcedure(null);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void getAllClinicProcedures() {
        List<ClinicProcedure> procedures = Arrays.asList(new ClinicProcedure(), new ClinicProcedure());
        when(procedureRepository.findAll()).thenReturn(procedures);

        ResponseEntity<List<ClinicProcedureDto>> result = procedureService.getAllProcedures();
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

}