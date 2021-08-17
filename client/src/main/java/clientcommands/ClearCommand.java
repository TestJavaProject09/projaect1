package clientcommands;

import exceptions.IncorrectArgumentException;
import utility.Console;
import utility.Invoker;
import utility.Receiver;

import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public class ClearCommand extends AbstractCommand {
    private final Receiver receiver;

    public ClearCommand(DatagramChannel datagramChannel, SocketAddress socketAddress, Console console, Invoker invoker) {
        super( "Clear collection");
        this.receiver = new Receiver(datagramChannel, socketAddress, console, invoker);
    }

    @Override
    public void execute(String arg) throws IncorrectArgumentException {
        if (arg.length() > 0) {
            throw new IncorrectArgumentException("Command doesn't need argument");
        } else {
            receiver.clear();
        }
    }
}
