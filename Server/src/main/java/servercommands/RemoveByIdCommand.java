package servercommands;

import utility.Receiver;

public class RemoveByIdCommand extends AbstractCommand {
    private final Receiver receiver;

    public RemoveByIdCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute(String arg) {
        receiver.removeById(arg);
    }
}
