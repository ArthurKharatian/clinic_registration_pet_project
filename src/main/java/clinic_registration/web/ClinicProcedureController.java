package clinic_registration.web;

import clinic_registration.dto.ClinicProcedureDto;
import clinic_registration.service.ClinicProcedureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/procedure")
public class ClinicProcedureController {

    private final ClinicProcedureService procedureService;

    @PostMapping
    public ResponseEntity<String> createDoctor(@RequestBody ClinicProcedureDto clinicProcedureDto) {
        return procedureService.createProcedure(clinicProcedureDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> read(@PathVariable("id") Long id) {
        return procedureService.readProcedure(id);
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody ClinicProcedureDto clinicProcedureDto) {
        return procedureService.updateProcedure(clinicProcedureDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        return procedureService.deleteProcedure(id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClinicProcedureDto>> getAllProcedures() {
        return procedureService.getAllProcedures();
    }

}

