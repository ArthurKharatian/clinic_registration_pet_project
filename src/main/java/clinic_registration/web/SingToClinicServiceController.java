package clinic_registration.web;

import clinic_registration.services.ClientService;
import clinic_registration.services.SignToClinicServService;
import org.springframework.stereotype.Controller;

@Controller
public class SingToClinicServiceController {
    private final ClientService clientService;
    private final SignToClinicServService clinicServService;

    public SingToClinicServiceController(ClientService clientService, SignToClinicServService clinicServService) {
        this.clientService = clientService;
        this.clinicServService = clinicServService;
    }
}
