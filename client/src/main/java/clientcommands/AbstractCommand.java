package clientcommands;

import exceptions.IncorrectArgumentException;
import exceptions.ValidationException;

/**
 * Abstract command class contains name and description
 */
public class AbstractCommand implements Command{
    private final String description;

    public AbstractCommand(String description) {
        this.description = description;
    }

    /**
     * @return Description of the command
     */
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void execute(String arg) throws IncorrectArgumentException, ValidationException {

    }
}