package clinic_registration.db.entity;

import clinic_registration.dto.ClientGender;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(schema = "public", name = "client")

public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private LocalDate birthdate;
    private int phoneNumber;
    private String email;
    private ClientGender clientGender;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private List<SignToDoctorEntity> signToDoctorEntities;


    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private List<SignToClinicServiceEntity> serviceEntities;

    public ClientEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ClientGender getClientGender() {
        return clientGender;
    }

    public void setClientGender(ClientGender clientGender) {
        this.clientGender = clientGender;
    }

    @Override
    public String toString() {
        return "ClientEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthdate=" + birthdate +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                ", clientGender=" + clientGender +
                '}';
    }
}
