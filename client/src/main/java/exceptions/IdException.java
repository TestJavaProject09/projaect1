package exceptions;
/**
 * Thrown by various methods if given id is incorrect.
 */

public class IdException extends Exception{
    /**
     * Constructs {@code IdException} with the given message string.
     * @param s the detailed message.
     */
    IdException(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}