package servercommands;

import utility.Receiver;

public class AddIfMaxCommand extends AbstractCommand{
    private final Receiver receiver;
    public AddIfMaxCommand(Receiver receiver){
        this.receiver = receiver;
    }
    @Override
    public void execute(String arg){
        receiver.addIfMax();
    }
}
