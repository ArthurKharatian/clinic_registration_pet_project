package clinic_registration.web;

import clinic_registration.dto.ClinicBrach;
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
    public ResponseEntity<ServiceMessageDto> addBranch(@RequestBody ClinicBrach brach){
        branchService.create(brach);
        return new ResponseEntity<>(new ServiceMessageDto(777,
                "Brach is created!"), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", headers = "Accept=application/json")
    public ResponseEntity<ServiceMessageDto> update(@PathVariable Long id, @RequestBody ClinicBrach brach){
        branchService.update(id, brach);
        return new ResponseEntity<>(new ServiceMessageDto(555,
                String.format("Brach with id %d is updated!", id)), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ServiceMessageDto> delete(@PathVariable("id") Long id){
        branchService.delete(id);
        return new ResponseEntity<>(new ServiceMessageDto(666,
                String.format("Brach with id %d is deleted!", id)), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClinicBrach>> readAll(){
        return new ResponseEntity<>(branchService.readAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicBrach> read(@PathVariable("id") Long id){
        ClinicBrach brach = branchService.read(id);
        return new ResponseEntity<>(brach, HttpStatus.OK);
    }
}

