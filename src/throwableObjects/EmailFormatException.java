package throwableObjects;

import java.util.InputMismatchException;

public class EmailFormatException extends InputMismatchException {
    public EmailFormatException() {
        super();
    }

    public EmailFormatException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Error, email must contain '@'! ";
    }
}
