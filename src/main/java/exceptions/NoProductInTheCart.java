package exceptions;

public class NoProductInTheCart extends RuntimeException {
    public NoProductInTheCart(String message) {
        super(message);
    }
}
