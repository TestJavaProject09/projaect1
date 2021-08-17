package clientcommands;


import exceptions.IncorrectArgumentException;
import utility.Console;
import utility.Invoker;
import utility.Receiver;

import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

/**
 * Command 'remove_by_EnPow'. Removes the element by its Engine Power.
 */
public class RemoveByEnPower extends AbstractCommand {
    private final Receiver receiver;

    public RemoveByEnPower(DatagramChannel datagramChannel, SocketAddress socketAddress, Console console, Invoker invoker) {
        super("Remove element for Engine Power");
        this.receiver = new Receiver(datagramChannel, socketAddress, console, invoker);
    }

    /**
     * Executes the command.
     */
    @Override
    public void execute(String arg) throws IncorrectArgumentException {
        if (arg.length() == 0){
            throw new IncorrectArgumentException("Command needs argument");
        }else {
            try {
                long tempEnPower = Long.parseLong(arg);
                if (tempEnPower <=0){
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException exception) {
                throw new IncorrectArgumentException("Incorrect argument. Id can't be less than 0.");
            }
            receiver.removeByEnPower(arg);
        }

    }
}
