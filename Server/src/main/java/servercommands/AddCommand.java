package servercommands;

import utility.Receiver;

public class AddCommand extends AbstractCommand{
    private final Receiver receiver;
    public AddCommand(Receiver receiver){
        this.receiver = receiver;
    }
    @Override
    public void execute(String arg){
        receiver.add();
    }

}
