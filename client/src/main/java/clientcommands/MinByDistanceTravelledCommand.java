package clientcommands;

import exceptions.IncorrectArgumentException;
import utility.Console;
import utility.Invoker;
import utility.Receiver;

import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

/**
 * Command 'min_by_DistanceTravelled'. Prints element with low argument of distance travelled.
 */
public class MinByDistanceTravelledCommand extends AbstractCommand {
    private final Receiver receiver;

    public MinByDistanceTravelledCommand(DatagramChannel datagramChannel, SocketAddress socketAddress, Console console, Invoker invoker) {
        super("search lowest element of collection for distance travelled");
        this.receiver = new Receiver(datagramChannel, socketAddress, console, invoker);
    }

    /**
     * Executes the command.
     */
    @Override
    public void execute(String arg) throws IncorrectArgumentException {
        if (arg.length() > 0){
            throw new IncorrectArgumentException("Command doesn't need argument");
        }else {
            receiver.minByDisTravelled();
        }
    }
}