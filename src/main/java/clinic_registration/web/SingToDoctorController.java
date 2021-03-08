package clinic_registration.web;

import clinic_registration.dto.Client;
import clinic_registration.dto.SignToDoctor;
import clinic_registration.services.SignToDoctorService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signToDoc")
public class SingToDoctorController {
    private final SignToDoctorService doctorService;

    public SingToDoctorController(SignToDoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public String addSign(@RequestBody SignToDoctor signToDoctor,Client client){
        return doctorService.create(signToDoctor, client);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Integer id, SignToDoctor signToDoctor){
        return doctorService.update(id, signToDoctor);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id){
        return doctorService.delete(id);
    }
}
