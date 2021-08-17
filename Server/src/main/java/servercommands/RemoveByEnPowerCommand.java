package servercommands;


import utility.Receiver;

/**
 * Command 'remove_by_EnPow'. Removes the element by its Engine Power.
 */
public class RemoveByEnPowerCommand extends AbstractCommand {
    private final Receiver receiver;

    public RemoveByEnPowerCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute(String arg) {
        receiver.removeByEnPower(arg);
    }
}
