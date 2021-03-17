package clinic_registration.web;

import clinic_registration.dto.Admin;
import clinic_registration.dto.ClinicBrach;
import clinic_registration.dto.Doctor;
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
    public ResponseEntity<ClinicBrach> addClient(@RequestBody ClinicBrach brach){
        branchService.create(brach);
        return new ResponseEntity<>(brach, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", headers = "Accept=application/json")
    public ResponseEntity<ClinicBrach> update(@PathVariable Long id, @RequestBody ClinicBrach brach){
        branchService.update(id, brach);
        return new ResponseEntity<>(brach, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClinicBrach> delete(@PathVariable("id") Long id){
        branchService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<ClinicBrach> readAll(){
        return branchService.readAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicBrach> read(@PathVariable("id") Long id){
        ClinicBrach brach = branchService.read(id);
        return new ResponseEntity<>(brach, HttpStatus.OK);
    }
}

