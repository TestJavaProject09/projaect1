package servercommands;

import utility.Receiver;

public class RemoveGreaterCommand extends AbstractCommand{
    private final Receiver receiver;
    public RemoveGreaterCommand(Receiver receiver){
        this.receiver = receiver;
    }
    @Override
    public void execute(String arg){
        receiver.removeGreater();
    }
}
