package servercommands;

import utility.Receiver;

public class InfoCommand extends AbstractCommand {
    private final Receiver receiver;

    public InfoCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute(String arg) {
        receiver.info();
    }
}
