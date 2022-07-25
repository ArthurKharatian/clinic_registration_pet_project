package clinic_registration.web;

import clinic_registration.dto.ClinicBranch;
import clinic_registration.dto.ServiceMessageDto;
import clinic_registration.services.ClinicBranchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clinic")
public class ClinicBranchController {
    private final ClinicBranchService branchService;

    public ClinicBranchController(ClinicBranchService branchService) {
        this.branchService = branchService;
    }

    @PostMapping
    public ResponseEntity<ServiceMessageDto> addBranch(@RequestBody ClinicBranch branch){
        branchService.create(branch);
        return new ResponseEntity<>(new ServiceMessageDto(777,
                "Branch is created!"), HttpStatus.CREATED);
    }

    @PutMapping(headers = "Accept=application/json")
    public ResponseEntity<ServiceMessageDto> update(@RequestBody ClinicBranch branch){
        branchService.update(branch);
        return new ResponseEntity<>(new ServiceMessageDto(555,
                String.format("Branch with id %d is updated!", branch.getId())), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ServiceMessageDto> delete(@PathVariable("id") Long id){
        branchService.delete(id);
        return new ResponseEntity<>(new ServiceMessageDto(666,
                String.format("Branch with id %d is deleted!", id)), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClinicBranch>> readAll(){
        return new ResponseEntity<>(branchService.readAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicBranch> read(@PathVariable("id") Long id){
        ClinicBranch branch = branchService.read(id);
        return new ResponseEntity<>(branch, HttpStatus.OK);
    }
}

