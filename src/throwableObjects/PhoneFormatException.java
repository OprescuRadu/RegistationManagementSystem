package throwableObjects;

import java.util.InputMismatchException;


public class PhoneFormatException extends InputMismatchException {
    public PhoneFormatException() {
        super();
    }

    public PhoneFormatException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Error, phone number must contain 10 digits!";
    }
}

