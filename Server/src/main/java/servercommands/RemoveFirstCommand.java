package servercommands;

import utility.Receiver;

public class RemoveFirstCommand extends AbstractCommand{
    private final Receiver receiver;
    public RemoveFirstCommand(Receiver receiver){
        this.receiver = receiver;
    }
    @Override
    public void execute(String arg){
        receiver.removeFirst();
    }
}
