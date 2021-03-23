package clinic_registration.web;

import java.time.LocalDateTime;

class ServiceMessageDto {

    int code;
    String message;
    LocalDateTime requestTime;
    LocalDateTime responseTime = LocalDateTime.now();
    Object dto;


    public ServiceMessageDto(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Object getDto() {
        return dto;
    }

    public void setDto(Object dto) {
        this.dto = dto;
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(LocalDateTime requestTime) {
        this.requestTime = requestTime;
    }

    public LocalDateTime getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(LocalDateTime responseTime) {
        this.responseTime = responseTime;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}