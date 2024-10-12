package exceptions;

public class InvalidCommandException extends IllegalArgumentException {
    public InvalidCommandException(String message) {
        super(message);
    }
}
