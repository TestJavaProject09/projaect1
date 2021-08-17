package clientcommands;

import exceptions.IncorrectArgumentException;
import utility.Console;
import utility.Invoker;
import utility.Receiver;

import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public class RemoveGreaterCommand extends AbstractCommand {
    private final Receiver receiver;

    public RemoveGreaterCommand(DatagramChannel datagramChannel, SocketAddress socketAddress, Console console, Invoker invoker) {
        super("Delete all elements from collection which are bigger than indicated one");
        this.receiver = new Receiver(datagramChannel, socketAddress, console, invoker);
    }

    @Override
    public void execute(String arg) throws IncorrectArgumentException {
        if (arg.length() > 0) {
            throw new IncorrectArgumentException("Command doesn't need argument");
        } else {
            receiver.removeGreater();
        }
    }
}
