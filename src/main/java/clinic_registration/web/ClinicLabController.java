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
    public ResponseEntity<ServiceMessageDto> addLab(@RequestBody ClinicLab lab){
        labService.create(lab);
        return new ResponseEntity<>(new ServiceMessageDto(777,
                "Laboratory is created!"), HttpStatus.CREATED);
    }

    @PutMapping(headers = "Accept=application/json")
    public ResponseEntity<ServiceMessageDto> update(@RequestBody ClinicLab lab){
        labService.update(lab);
        return new ResponseEntity<>(new ServiceMessageDto(555,
                String.format("Laboratory with id %d is updated!", lab.getId())), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ServiceMessageDto> delete(@PathVariable("id") Long id){
        labService.delete(id);
        return new ResponseEntity<>(new ServiceMessageDto(666,
                String.format("Laboratory with id %d is deleted!", id)), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClinicLab>> readAll(){
        return new ResponseEntity<>(labService.readAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicLab> read(@PathVariable("id") Long id){
        ClinicLab lab = labService.read(id);
        return new ResponseEntity<>(lab, HttpStatus.OK);
    }
}
