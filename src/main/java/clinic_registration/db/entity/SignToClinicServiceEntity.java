package clinic_registration.db.entity;

import clinic_registration.dto.ClinicServiceType;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(schema = "public", name = "clinic_service_appointment")
public class SignToClinicServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer client_id;
    private LocalDate visitDate;
    private ClinicServiceType serviceType;
    private Integer duration;

    public SignToClinicServiceEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClient_id() {
        return client_id;
    }

    public void setClient_id(Integer client_id) {
        this.client_id = client_id;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
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

    @Override
    public String toString() {
        return "SignToClinicServiceEntity{" +
                "id=" + id +
                ", client_id=" + client_id +
                ", visitDate=" + visitDate +
                ", serviceType=" + serviceType +
                ", duration=" + duration +
                '}';
    }
}
