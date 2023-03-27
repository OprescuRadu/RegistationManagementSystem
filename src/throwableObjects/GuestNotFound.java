package throwableObjects;

public class GuestNotFound extends RuntimeException {
    public GuestNotFound() {
    }

    public GuestNotFound(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Error! Guest not found!";
    }
}

