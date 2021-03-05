package clinic_registration.dto;

import java.util.*;

public class DoctorStorage {
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

    public Map<String, String> getDoctors() {
        return doctors;
    }

    public List<String> getAllDoctorsNames(){
        return new ArrayList<>(doctors.keySet());
    }

    public String getDoctorType(String name){
        return doctors.get(name);
    }

    public String getDoctorByType(String type){
        String docName = "";
        for(Map.Entry<String, String> pair : doctors.entrySet()){
            if(pair.getValue().equals(type)){
                docName = pair.getKey();
            }
        }
        return docName;
    }
}
