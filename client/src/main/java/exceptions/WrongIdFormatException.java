package exceptions;
/**
 * Thrown by various methods if given id has incorrect format.
 */
public class WrongIdFormatException extends IdException{

    public WrongIdFormatException(String s) {
        super(s);
    }

}