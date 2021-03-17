package clinic_registration.exceptions;


public class ClinicServiceException extends RuntimeException{

    private final ErrorMessage errorMessage;

    public ClinicServiceException(String message) {
        super(message);
        errorMessage = ErrorMessage.UNKNOWN;
    }

    public ClinicServiceException(String message, ErrorMessage errorMessage) {
        super(message);
        this.errorMessage = errorMessage;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }
}
