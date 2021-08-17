package clientcommands;

import exceptions.IncorrectArgumentException;
import utility.Invoker;

public class ExitCommand extends AbstractCommand {
    private static final long serialVersionUID = 32L;
    transient final Invoker invoker;

    public ExitCommand(Invoker invoker) {
        super("End programme without saving to the file");
        this.invoker = invoker;
    }

    @Override
    public void execute(String arg) throws IncorrectArgumentException {
        if (arg.length() > 0) {
            throw new IncorrectArgumentException("Command doesn't need argument");
        } else {
            invoker.requestExit(this);
        }
    }
}



