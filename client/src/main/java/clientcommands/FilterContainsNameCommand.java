package clientcommands;

import exceptions.IncorrectArgumentException;
import utility.Console;
import utility.Invoker;
import utility.Receiver;

import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public class FilterContainsNameCommand extends AbstractCommand {
    private final Receiver receiver;

    public FilterContainsNameCommand(DatagramChannel datagramChannel, SocketAddress socketAddress, Console console, Invoker invoker) {
        super("Show elements with value of field \"StartDate\", which is bigger than indicated one");
        this.receiver = new Receiver(datagramChannel, socketAddress, console, invoker);
    }

    @Override
    public void execute(String arg) throws IncorrectArgumentException {
        if (arg.length() == 0) {
            throw new IncorrectArgumentException("Command needs argument");
        } else {
            receiver.filterContainsName(arg);
        }
    }
}
