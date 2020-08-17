package exception;


public class DriverExecutingOrderException extends RuntimeException {
    public DriverExecutingOrderException(String message) {
        super(message);
    }
}
