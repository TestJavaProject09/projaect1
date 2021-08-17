package exceptions;

public class UnknownCommandException extends Throwable {
    public UnknownCommandException(String s) {
        super(s);
    }
}
