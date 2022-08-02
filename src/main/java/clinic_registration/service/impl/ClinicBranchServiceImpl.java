package clinic_registration.service.impl;

import clinic_registration.db.entity.ClinicBranch;
import clinic_registration.db.entity.Status;
import clinic_registration.db.repository.ClinicBranchRepository;
import clinic_registration.dto.ClinicBranchDto;
import clinic_registration.exceptions.ClinicServiceException;
import clinic_registration.service.ClinicBranchService;
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
public class ClinicBranchServiceImpl implements ClinicBranchService {

    private final ClinicBranchRepository branchRepository;
    private final ObjectMapper objectMapper;
    private static final String EXC_MESSAGE = "Branch with id %d is not found";

    @Override
    public ClinicBranchDto create(ClinicBranchDto clinicBranchDto) {
        if (clinicBranchDto == null) {
            throw new ClinicServiceException("Branch is null");
        }

        Long id = clinicBranchDto.getId();
        if (id != null && branchRepository.findById(id).isPresent()) {
            throw new ClinicServiceException(String.format("Branch with id %d already exists", id));
        }

        ClinicBranch branch = objectMapper.convertValue(clinicBranchDto, ClinicBranch.class);
        branch.setStatus(String.valueOf(Status.CREATED));
        ClinicBranch save = branchRepository.save(branch);
        return objectMapper.convertValue(save, ClinicBranchDto.class);
    }

    @Override
    public ClinicBranchDto read(Long id) {
        ClinicBranch branch = branchRepository.findById(id).orElseThrow(() ->
                new ClinicServiceException(String.format(EXC_MESSAGE, id)));
        return objectMapper.convertValue(branch, ClinicBranchDto.class);
    }

    @Override
    public ClinicBranchDto update(ClinicBranchDto clinicBranchDto) {
        Long id = clinicBranchDto.getId();
        if (id == null) {
            throw new ClinicServiceException("id is null");
        }

        read(id);

        ClinicBranch branch = objectMapper.convertValue(clinicBranchDto, ClinicBranch.class);
        branch.setStatus(String.valueOf(Status.UPDATED));
        ClinicBranch save = branchRepository.save(branch);
        return objectMapper.convertValue(save, ClinicBranchDto.class);
    }

    @Override
    public ClinicBranchDto delete(Long id) {
        ClinicBranch branch = objectMapper.convertValue(read(id), ClinicBranch.class);
        branch.setStatus(String.valueOf(Status.DELETED));
        branchRepository.save(branch);
        return objectMapper.convertValue(branch, ClinicBranchDto.class);
    }

    @Override
    public List<ClinicBranchDto> readAll() {
        return branchRepository.findAll().stream()
                .map(branch -> objectMapper.convertValue(branch, ClinicBranchDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<String> createBranch(ClinicBranchDto clinicBranchDto) {
        if (clinicBranchDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Branch is not created! Body is null");
        }

        ClinicBranchDto branch = create(clinicBranchDto);
        String dto = JsonConverter.getString(branch, objectMapper);

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @Override
    public ResponseEntity<String> readBranch(Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Branch is not found! Id is null");
        }

        ClinicBranchDto branch = read(id);
        String dto = JsonConverter.getString(branch, objectMapper);

        return new ResponseEntity<>(dto, HttpStatus.OK);    }

    @Override
    public ResponseEntity<String> updateBranch(ClinicBranchDto clinicBranchDto) {
        if (clinicBranchDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Branch is not updated! Body is null");
        }

        ClinicBranchDto branch = update(clinicBranchDto);
        String dto = JsonConverter.getString(branch, objectMapper);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @Override
    public ResponseEntity<String> deleteBranch(Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Branch assignment is not deleted! Id is null");
        }

        ClinicBranchDto branch = delete(id);
        String dto = JsonConverter.getString(branch, objectMapper);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @Override
    public ResponseEntity<List<ClinicBranchDto>> getAllBranches() {
        return ResponseEntity.ok(readAll());
    }
}
