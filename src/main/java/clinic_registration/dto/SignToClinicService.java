package clinic_registration.dto;

import java.time.LocalDate;

public class SignToClinicService {
    private Integer id;
    private ClinicServiceType serviceType;
    private Integer duration;
    private LocalDate visitDate;

    public SignToClinicService() {
    }

    public SignToClinicService(Integer id, ClinicServiceType serviceType) {
        this.id = id;
        this.serviceType = serviceType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ClinicServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ClinicServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }
}
