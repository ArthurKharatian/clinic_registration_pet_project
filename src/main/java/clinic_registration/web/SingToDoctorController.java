package clinic_registration.web;

import clinic_registration.services.ClientService;
import clinic_registration.services.SignToDoctorService;
import org.springframework.stereotype.Controller;

@Controller
public class SingToDoctorController {
    private final ClientService clientService;
    private final SignToDoctorService doctorService;

    public SingToDoctorController(ClientService clientService, SignToDoctorService doctorService) {
        this.clientService = clientService;
        this.doctorService = doctorService;
    }

}
