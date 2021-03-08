package clinic_registration.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SignToDoctor {

   private Integer id;
   private LocalDate visitDate;
   private String doctorType;
   private String doctorName;
   private Integer client_id;


   private final Map<String, String> doctors = new HashMap<>();
   {
      doctors.put("Volkov Aleksandr Borisovich", "THERAPIST");
      doctors.put("Nekrasov Ivan Andreevich", "NEUROLOGIST");
      doctors.put("Ovsepyan Gamlet Robertovich", "OPHTHALMOLOGIST");
      doctors.put("Matveichuk Ignat Leonidovich", "SURGEON");
      doctors.put("Bunina Inna Aleksandrovna", "ORTHOPEDIST");
      doctors.put("Shats Petr Abramovich", "ENT");
      doctors.put("Orlova Tamara Ivanovna", "GASTROENTEROLOGIST");
      doctors.put("Gabrielyan Marina Rubenovna", "DENTIST");
   }

   public String getDocNameByType(String type){
      String docName = "The doctor is not found";
      for(Map.Entry<String, String> pair : doctors.entrySet()){
         if(pair.getValue().equals(type)){
            docName = pair.getKey();
         }
      }
      return docName;
   }

   public SignToDoctor() {
   }

   public SignToDoctor(LocalDate visitDate, String doctorType) {
      this.visitDate = visitDate;
      this.doctorType = doctorType;
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

   public Integer getClient_id() {
      return client_id;
   }

   public void setClient_id(Integer client_id) {
      this.client_id = client_id;
   }

   public String getDoctorName() {
      return getDocNameByType(this.doctorType);
   } // <- getting from the HashMap

   public void setDoctorName(String doctorName) {
      this.doctorName = doctorName;
   }
}
