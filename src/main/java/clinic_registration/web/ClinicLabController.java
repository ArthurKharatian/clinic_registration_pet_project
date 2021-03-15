package clinic_registration.web;

import clinic_registration.dto.ClinicLab;
import clinic_registration.services.ClinicLabService;
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
    public String addClient(@RequestBody ClinicLab lab){
        return labService.create(lab);
    }

    @PutMapping(value = "/{id}", headers = "Accept=application/json")
    public String update(@PathVariable Long id, @RequestBody ClinicLab lab){
        return labService.update(id, lab);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        return labService.delete(id);
    }

    @GetMapping("/all")
    public List<ClinicLab> readAll(){
        return labService.readAll();
    }

    @GetMapping("/{id}")
    public ClinicLab read(@PathVariable("id") Long id){
        return labService.read(id);
    }
}
