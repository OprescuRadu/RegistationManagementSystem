package throwableObjects;

public class AlreadyOnList extends RuntimeException {
    public AlreadyOnList() {
        super();
    }
    @Override
    public String getMessage() {
        return "Error.Guest is already registered.";
    }
}
