package clinic_registration.web;


import clinic_registration.services.SignToClinicServService;
import org.springframework.stereotype.Controller;

@Controller
public class SingToClinicServiceController {
    private final SignToClinicServService clinicServService;

    public SingToClinicServiceController(SignToClinicServService clinicServService) {
        this.clinicServService = clinicServService;
    }
}
