package exceptions;

public class NoAvailableId extends RuntimeException {
    public NoAvailableId(String message) {
        super(message);
    }
}
