package clinic_registration.web;


import clinic_registration.dto.Client;
import clinic_registration.dto.SignToClinicService;
import clinic_registration.services.SignToClinicServService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signToService")
public class SingToClinicServiceController {
    private final SignToClinicServService clinicServService;

    public SingToClinicServiceController(SignToClinicServService clinicServService) {
        this.clinicServService = clinicServService;
    }

    @PostMapping
    public String addSign(@RequestBody SignToClinicService clinicService, Client client){
        return clinicServService.create(clinicService, client);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Integer id, SignToClinicService clinicService){
        return clinicServService.update(id, clinicService);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id){
        return clinicServService.delete(id);
    }
}
