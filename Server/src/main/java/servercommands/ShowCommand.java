package servercommands;

import utility.Receiver;

public class ShowCommand extends AbstractCommand{
    private final Receiver receiver;
    public ShowCommand(Receiver receiver){
        this.receiver = receiver;
    }
    @Override
    public void execute(String arg){
        receiver.show();
    }
}
