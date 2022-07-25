package clinic_registration.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ServiceMessageDto {

    int code;
    String message;
    LocalDateTime requestTime;
    LocalDateTime responseTime = LocalDateTime.now();
    Object dto;


    public ServiceMessageDto(int code, String message) {
        this.code = code;
        this.message = message;
    }
}