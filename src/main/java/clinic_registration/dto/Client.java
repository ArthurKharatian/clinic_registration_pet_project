package clinic_registration.dto;

import java.time.LocalDate;

public class Client {
    private Integer id;
    private String name;
    private ClientGender clientGender;
    private LocalDate birthdate;
    private int phoneNumber;
    private String email;


    public Client() {
    }

    public Client(Integer id, String name, ClientGender clientGender, LocalDate birthdate, int phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.clientGender = clientGender;
        this.birthdate = birthdate;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Client(String name, ClientGender clientGender, LocalDate birthdate, int phoneNumber, String email) {
        this.name = name;
        this.clientGender = clientGender;
        this.birthdate = birthdate;
        this.phoneNumber = phoneNumber;
        this.email = email;
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
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthdate=" + birthdate +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                ", clientGender=" + clientGender +
                '}';
    }
}
