package clientcommands;
import exceptions.IncorrectArgumentException;
import exceptions.ValidationException;

/**
 * Interface for commands
 */
public interface Command{

    String getDescription();

    void execute(String arg) throws IncorrectArgumentException, ValidationException;
}