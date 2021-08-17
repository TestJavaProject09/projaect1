package servercommands;

import utility.Receiver;

public class UpdateCommand extends AbstractCommand{
    private final Receiver receiver;
    public UpdateCommand(Receiver receiver){
        this.receiver = receiver;
    }
    @Override
    public void execute(String arg){
        receiver.update(arg);
    }
}
