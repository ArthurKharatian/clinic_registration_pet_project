package clinic_registration.web;

import clinic_registration.dto.ClinicLab;
import clinic_registration.services.ClinicLabService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lab")
public class ClinicLabController {
    private final ClinicLabService labService;

    public ClinicLabController(ClinicLabService labService) {
        this.labService = labService;
    }

    @PostMapping
    public ResponseEntity<ClinicLab> addLab(@RequestBody ClinicLab lab){
        labService.create(lab);
        return new ResponseEntity<>(lab, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", headers = "Accept=application/json")
    public ResponseEntity<ClinicLab> update(@PathVariable Long id, @RequestBody ClinicLab lab){
        labService.update(id, lab);
        return new ResponseEntity<>(lab, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClinicLab> delete(@PathVariable("id") Long id){
        labService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<ClinicLab> readAll(){
        return labService.readAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicLab> read(@PathVariable("id") Long id){
        ClinicLab lab = labService.read(id);
        return new ResponseEntity<>(lab, HttpStatus.OK);
    }
}
