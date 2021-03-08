package clinic_registration.db.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(schema = "public", name = "doctor_appointment")
public class SignToDoctorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer client_id;
    private LocalDate visitDate;
    private String doctorName;
    private String doctorType;


    public SignToDoctorEntity() {
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

    public String getDoctorName() {
        return doctorName;
    }


    public String getDoctorType() {
        return doctorType;
    }

    public void setDoctorType(String doctorType) {
        this.doctorType = doctorType;
    }

    @Override
    public String toString() {
        return "SignToDoctorEntity{" +
                "id=" + id +
                ", client_id=" + client_id +
                ", visitDate=" + visitDate +
                ", doctorName='" + doctorName + '\'' +
                ", doctorType='" + doctorType + '\'' +
                '}';
    }
}
