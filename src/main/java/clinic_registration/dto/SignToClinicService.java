package clinic_registration.dto;

import java.time.LocalDate;

public class SignToClinicService {
    private Integer id;
    private ClinicServiceType serviceType;
    private Integer duration;
    private LocalDate visitDate;
    private Integer client_id;



    public SignToClinicService() {
    }

    public SignToClinicService(ClinicServiceType serviceType, Integer duration, LocalDate visitDate) {
        this.serviceType = serviceType;
        this.duration = duration;
        this.visitDate = visitDate;
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

    public Integer getClient_id() {
        return client_id;
    }

    public void setClient_id(Integer client_id) {
        this.client_id = client_id;
    }
}
