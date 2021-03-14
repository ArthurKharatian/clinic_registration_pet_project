package clinic_registration.web;

import clinic_registration.dto.SignToDoctor;
import clinic_registration.services.SignToDoctorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/signToDoc")
public class SignToDoctorController {
    private final SignToDoctorService signService;

    public SignToDoctorController(SignToDoctorService signService) {
        this.signService = signService;
    }
    @PostMapping
    public String addClient(@RequestBody SignToDoctor sign){
        return signService.create(sign);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, SignToDoctor sign){
        return signService.update(id, sign);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        return signService.delete(id);
    }

    @GetMapping("/all")
    public List<SignToDoctor> readAll(){
        return signService.readAll();
    }

    @GetMapping("/{id}")
    public String read(@PathVariable("id") Long id){
        return signService.read(id);
    }
}
