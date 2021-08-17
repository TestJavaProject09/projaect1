package servercommands;

import utility.Receiver;

public class ClearCommand extends AbstractCommand {
    private final Receiver receiver;
    public ClearCommand(Receiver receiver){
        this.receiver = receiver;
    }
    @Override
    public void execute(String arg){
        receiver.clear();
    }
}
