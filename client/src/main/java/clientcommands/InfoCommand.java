package clientcommands;

import exceptions.IncorrectArgumentException;
import utility.Console;
import utility.Invoker;
import utility.Receiver;

import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public class InfoCommand extends AbstractCommand {
    private final Receiver receiver;

    public InfoCommand(DatagramChannel datagramChannel, SocketAddress socketAddress, Console console, Invoker invoker) {
        super("Show information about collection (type, initialization time and etc.)");
        this.receiver = new Receiver(datagramChannel, socketAddress, console, invoker);
    }

    @Override
    public void execute(String arg) throws IncorrectArgumentException {
        if (arg.length() > 0){
            throw new IncorrectArgumentException("Command doesn't need argument");
        }else {
            receiver.info();
        }
    }
}