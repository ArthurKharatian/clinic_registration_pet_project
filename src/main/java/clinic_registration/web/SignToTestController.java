package clinic_registration.web;

import clinic_registration.dto.SignToTest;
import clinic_registration.services.SignToTestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/signToTest")
public class SignToTestController {
    private final SignToTestService testService;

    public SignToTestController(SignToTestService testService) {
        this.testService = testService;
    }

    @PostMapping
    public String addClient(@RequestBody SignToTest test){
        return testService.create(test);
    }

    @PutMapping(value = "/{id}", headers = "Accept=application/json")
    public String update(@PathVariable Long id, @RequestBody SignToTest test){
        return testService.update(id, test);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        return testService.delete(id);
    }

    @GetMapping("/all")
    public List<SignToTest> readAll(){
        return testService.readAll();
    }

    @GetMapping("/{id}")
    public String read(@PathVariable("id") Long id){
        return testService.read(id);
    }
}
