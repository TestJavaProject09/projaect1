package servercommands;

import utility.Console;
import utility.Receiver;

/**
 * Command 'min_by_DistanceTravelled'. Prints element with low argument of distance travelled.
 */
public class MinByDistanceTravelledCommand extends AbstractCommand {
    private final Receiver receiver;
    public MinByDistanceTravelledCommand(Receiver receiver){
        this.receiver = receiver;
    }
    @Override
    public void execute(String arg){
        receiver.minByDistanceTravelled();
    }
}
