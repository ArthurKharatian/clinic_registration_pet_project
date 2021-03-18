package clinic_registration.exceptions;

import org.springframework.http.HttpStatus;

public enum ErrorMessage {

    UNKNOWN(10001, "Ooops. Unknown exception!", HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_FOUND(10001, "Can't find!", HttpStatus.NOT_FOUND);


    private final int code;
    private final String message;
    private final HttpStatus status;

    ErrorMessage(int code, String message, HttpStatus status) {

        this.code = code;
        this.message = message;
        this.status = status;
    }
}
