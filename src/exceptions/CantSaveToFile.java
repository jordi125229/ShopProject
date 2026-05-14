package exceptions;

public class CantSaveToFile extends RuntimeException {
    public CantSaveToFile(String message) {
        super(message);
    }
}
