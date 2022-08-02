package clinic_registration.service.impl;

import clinic_registration.db.entity.ClinicLab;
import clinic_registration.db.entity.Status;
import clinic_registration.db.repository.ClinicLabRepository;
import clinic_registration.dto.ClinicLabDto;
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
public class ClinicLabServiceImplTest {

    @InjectMocks
    private ClinicLabServiceImpl labService;

    @Mock
    private ClinicLabRepository labRepository;

    @Spy
    private ObjectMapper objectMapper;

    @Test
    public void create() {
        ClinicLab lab = new ClinicLab();
        lab.setId(1L);
        String status = String.valueOf(Status.CREATED);
        lab.setStatus(status);

        when(labRepository.save(lab)).thenReturn(lab);

        ClinicLabDto labDto = new ClinicLabDto();
        labDto.setId(1L);

        ClinicLabDto result = labService.create(labDto);
        verify(labRepository, times(1)).save(lab);
        assertEquals(status, result.getStatus());
    }

    @Test(expected = ClinicServiceException.class)
    public void create_nullDto() {
        labService.create(null);
    }

    @Test(expected = ClinicServiceException.class)
    public void create_dtoExists() {
        ClinicLab lab = new ClinicLab();
        lab.setId(1L);

        when(labRepository.findById(anyLong())).thenReturn(Optional.of(lab));

        ClinicLabDto labDto = new ClinicLabDto();
        labDto.setId(1L);
        labService.create(labDto);
    }

    @Test
    public void read() {
        ClinicLab lab = new ClinicLab();
        lab.setId(1L);

        when(labRepository.findById(1L)).thenReturn(Optional.of(lab));

        ClinicLabDto result = labService.read(1L);
        assertEquals(lab.getId(), result.getId());

    }

    @Test(expected = ClinicServiceException.class)
    public void read_notFound() {
        labService.read(1L);
    }

    @Test
    public void update() {
        ClinicLab lab = new ClinicLab();
        lab.setId(1L);
        String status = String.valueOf(Status.UPDATED);
        lab.setStatus(status);


        when(labRepository.findById(1L)).thenReturn(Optional.of(lab));
        when(labRepository.save(lab)).thenReturn(lab);

        ClinicLabDto labDto = new ClinicLabDto();
        labDto.setId(1L);

        ClinicLabDto result = labService.update(labDto);
        verify(labRepository, times(1)).save(lab);
        assertEquals(status, result.getStatus());
    }

    @Test(expected = ClinicServiceException.class)
    public void update_idNull() {
        labService.update(new ClinicLabDto());
    }

    @Test
    public void delete() {
        ClinicLab lab = new ClinicLab();
        lab.setId(1L);
        String status = String.valueOf(Status.DELETED);
        lab.setStatus(status);

        when(labRepository.findById(1L)).thenReturn(Optional.of(lab));
        when(labRepository.save(lab)).thenReturn(lab);

        ClinicLabDto result = labService.delete(1L);
        verify(labRepository, times(1)).save(lab);
        assertEquals(status, result.getStatus());
    }

    @Test
    public void readAll() {
        List<ClinicLab> labs = Arrays.asList(new ClinicLab(), new ClinicLab());
        when(labRepository.findAll()).thenReturn(labs);

        List<ClinicLabDto> result = labService.readAll();
        assertEquals(labs.size(), result.size());
        assertEquals(labs.get(0).getId(), result.get(0).getId());
    }

    @Test
    public void createClinicLab() {
        ClinicLab lab = new ClinicLab();
        lab.setId(1L);
        String status = String.valueOf(Status.CREATED);
        lab.setStatus(status);

        when(labRepository.save(lab)).thenReturn(lab);

        ClinicLabDto labDto = new ClinicLabDto();
        labDto.setId(1L);
        ResponseEntity<String> result = labService.createLab(labDto);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertNotNull(result.getBody());
        assertTrue(result.getBody().contains(String.valueOf(Status.CREATED)));
    }

    @Test
    public void createClinicLab_bodyNull() {
        ResponseEntity<String> result = labService.createLab(null);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void readClinicLab() {
        ClinicLab lab = new ClinicLab();
        lab.setId(1L);

        when(labRepository.findById(1L)).thenReturn(Optional.of(lab));

        ResponseEntity<String> result = labService.readLab(1L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
    }

    @Test
    public void readClinicLab_bodyNull() {
        ResponseEntity<String> result = labService.readLab(null);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void updateClinicLab() {
        ClinicLab lab = new ClinicLab();
        lab.setId(1L);
        String status = String.valueOf(Status.UPDATED);
        lab.setStatus(status);


        when(labRepository.findById(1L)).thenReturn(Optional.of(lab));
        when(labRepository.save(lab)).thenReturn(lab);

        ClinicLabDto labDto = new ClinicLabDto();
        labDto.setId(1L);

        ResponseEntity<String> result = labService.updateLab(labDto);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertTrue(result.getBody().contains(String.valueOf(Status.UPDATED)));
    }

    @Test
    public void updateClinicLab_bodyNull() {
        ResponseEntity<String> result = labService.updateLab(null);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void deleteClinicLab() {
        ClinicLab lab = new ClinicLab();
        lab.setId(1L);
        String status = String.valueOf(Status.DELETED);
        lab.setStatus(status);

        when(labRepository.findById(1L)).thenReturn(Optional.of(lab));
        when(labRepository.save(lab)).thenReturn(lab);

        ResponseEntity<String> result = labService.deleteLab(1L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertTrue(result.getBody().contains(String.valueOf(Status.DELETED)));
    }

    @Test
    public void deleteClinicLab_bodyNull() {
        ResponseEntity<String> result = labService.deleteLab(null);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void getAllClinicLabs() {
        List<ClinicLab> labs = Arrays.asList(new ClinicLab(), new ClinicLab());
        when(labRepository.findAll()).thenReturn(labs);

        ResponseEntity<List<ClinicLabDto>> result = labService.getAllLabs();
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

}