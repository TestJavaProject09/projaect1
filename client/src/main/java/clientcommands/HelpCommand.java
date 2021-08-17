package clientcommands;

import exceptions.IncorrectArgumentException;
import utility.Console;
import utility.Invoker;
import utility.Receiver;

import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.util.HashMap;

public class HelpCommand extends AbstractCommand {
    private final HashMap<String, Command> commands;
    private final Receiver receiver;

    public HelpCommand(HashMap<String, Command> commands, DatagramChannel datagramChannel, SocketAddress socketAddress, Console console, Invoker invoker) {
        super("Show allowed commands");
        this.commands = commands;
        this.receiver = new Receiver(datagramChannel, socketAddress, console, invoker);
    }

    @Override
    public void execute(String arg) throws IncorrectArgumentException {
        if (arg.length() > 0) {
            throw new IncorrectArgumentException("Command doesn't need argument");
        } else {
            receiver.help(commands);
        }
    }
}
