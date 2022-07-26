package clinic_registration.service.impl;

import clinic_registration.db.entity.ClinicLab;
import clinic_registration.db.entity.Status;
import clinic_registration.db.repository.ClinicLabRepository;
import clinic_registration.dto.ClinicLabDto;
import clinic_registration.exceptions.ClinicServiceException;
import clinic_registration.service.ClinicLabService;
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
public class ClinicLabServiceImpl implements ClinicLabService {

    private final ClinicLabRepository labRepository;
    private final ObjectMapper objectMapper;
    private static final String EXC_MESSAGE = "Laboratory with id %d is not found";


    @Override
    public ClinicLabDto create(ClinicLabDto clinicLabDto) {
        if (clinicLabDto == null) {
            throw new ClinicServiceException("Laboratory is null");
        }

        Long id = clinicLabDto.getId();
        if (id != null && labRepository.findById(id).isPresent()) {
            throw new ClinicServiceException(String.format("Branch with id %d already exists", id));
        }

        ClinicLab lab = objectMapper.convertValue(clinicLabDto, ClinicLab.class);
        lab.setStatus(String.valueOf(Status.CREATED));
        labRepository.save(lab);
        return clinicLabDto;
    }

    @Override
    public ClinicLabDto read(Long id) {
        ClinicLab lab = labRepository.findById(id).orElseThrow(() ->
                new ClinicServiceException(String.format(EXC_MESSAGE, id)));
        return objectMapper.convertValue(lab, ClinicLabDto.class);

    }

    @Override
    public ClinicLabDto update(ClinicLabDto clinicLabDto) {
        Long doctorId = clinicLabDto.getId();
        if (doctorId == null || labRepository.findById(doctorId).isEmpty()) {
            throw new ClinicServiceException(String.format(EXC_MESSAGE, doctorId));
        }
        ClinicLab lab = objectMapper.convertValue(clinicLabDto, ClinicLab.class);
        lab.setStatus(String.valueOf(Status.UPDATED));
        labRepository.save(lab);
        return clinicLabDto;
    }

    @Override
    public ClinicLabDto delete(Long id) {
        ClinicLab lab = labRepository.findById(id).orElseThrow(() ->
                new ClinicServiceException(String.format(EXC_MESSAGE, id)));
        lab.setStatus(String.valueOf(Status.DELETED));
        labRepository.save(lab);
        return objectMapper.convertValue(lab, ClinicLabDto.class);
    }

    @Override
    public List<ClinicLabDto> readAll() {
        List<ClinicLab> labs = labRepository.findAll();
        return labs.stream().map(lab ->
                        objectMapper.convertValue(lab, ClinicLabDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<String> createLab(ClinicLabDto clinicLabDto) {
        if (clinicLabDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Laboratory is not created! Body is null");
        }

        ClinicLabDto lab = create(clinicLabDto);
        String dto = JsonConverter.getString(lab, objectMapper);

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @Override
    public ResponseEntity<String> readLab(Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Laboratory is not found! Id is null");
        }

        ClinicLabDto lab = read(id);
        String dto = JsonConverter.getString(lab, objectMapper);

        return new ResponseEntity<>(dto, HttpStatus.OK);    }

    @Override
    public ResponseEntity<String> updateLab(ClinicLabDto clinicLabDto) {
        if (clinicLabDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Laboratory is not updated! Body is null");
        }

        ClinicLabDto lab = update(clinicLabDto);
        String dto = JsonConverter.getString(lab, objectMapper);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @Override
    public ResponseEntity<String> deleteLab(Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Procedure assignment is not deleted! Id is null");
        }

        ClinicLabDto lab = delete(id);
        String dto = JsonConverter.getString(lab, objectMapper);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @Override
    public ResponseEntity<List<ClinicLabDto>> getAllLabs() {
        return ResponseEntity.ok(readAll());
    }
}
