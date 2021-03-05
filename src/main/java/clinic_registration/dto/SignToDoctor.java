package clinic_registration.dto;

import java.time.LocalDate;


public class SignToDoctor {

   private Integer id;
   private LocalDate visitDate;
   private String doctorType;

   public SignToDoctor() {
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public LocalDate getVisitDate() {
      return visitDate;
   }

   public void setVisitDate(LocalDate visitDate) {
      this.visitDate = visitDate;
   }

   public String getDoctorType() {
      return doctorType;
   }

   public void setDoctorType(String doctorType) {
      this.doctorType = doctorType;
   }
}
