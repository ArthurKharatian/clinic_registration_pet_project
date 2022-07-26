package clinic_registration.service;

import clinic_registration.dto.ClinicBranchDto;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ClinicBranchService {

    ClinicBranchDto create(ClinicBranchDto clinicBranchDto);

    ClinicBranchDto read(Long id);

    @Transactional
    ClinicBranchDto update(ClinicBranchDto clinicBranchDto);

    @Transactional
    ClinicBranchDto delete(Long id);

    List<ClinicBranchDto> readAll();

    ResponseEntity<String> createBranch(@RequestBody ClinicBranchDto clinicBranchDto);

    ResponseEntity<String> readBranch(@PathVariable("id") Long id);

    @Transactional
    ResponseEntity<String> updateBranch(@RequestBody ClinicBranchDto clinicBranchDto);

    @Transactional
    ResponseEntity<String> deleteBranch(@PathVariable("id") Long id);

    ResponseEntity<List<ClinicBranchDto>> getAllBranches();

}
