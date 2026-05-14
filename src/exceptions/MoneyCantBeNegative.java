package exceptions;

public class MoneyCantBeNegative extends RuntimeException {
    public MoneyCantBeNegative(String message) {
        super(message);
    }
}
