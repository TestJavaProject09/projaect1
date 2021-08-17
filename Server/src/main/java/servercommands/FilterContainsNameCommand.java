package servercommands;

import utility.Receiver;

public class FilterContainsNameCommand extends AbstractCommand{
    private final Receiver receiver;
    public FilterContainsNameCommand(Receiver receiver){
        this.receiver = receiver;
    }
    @Override
    public void execute(String arg){
        receiver.filterContainsName(arg);
    }
}
