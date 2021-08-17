package clientcommands;

import exceptions.IncorrectArgumentException;
import utility.Console;
import utility.Invoker;
import utility.Receiver;

import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public class RemoveByIdCommand extends AbstractCommand {
    private final Receiver receiver;

    public RemoveByIdCommand(DatagramChannel datagramChannel, SocketAddress socketAddress, Console console, Invoker invoker) {
        super("Delete element with indicated id");
        this.receiver = new Receiver(datagramChannel, socketAddress, console, invoker);
    }

    @Override
    public void execute(String arg) throws IncorrectArgumentException{
        if (arg.length() == 0){
            throw new IncorrectArgumentException("Command needs argument");
        }else {
            try {
                Long tempId = Long.parseLong(arg);
                if (tempId <=0){
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException exception) {
                throw new IncorrectArgumentException("Incorrect argument. Id can't be less than 0.");
            }
            receiver.removeById(arg);
        }
    }
}