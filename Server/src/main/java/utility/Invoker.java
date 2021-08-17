package utility;

import exceptions.IncorrectArgumentException;
import exceptions.ValidationException;
import servercommands.*;

import java.util.HashMap;


/**
 * This class contains map with commands which can be execute
 */
public class Invoker {
    private final HashMap<String, servercommands.Command> command;
    private final Receiver receiver;
    private boolean isStopRequested = false;
    public Invoker(Receiver receiver) {
        command = new HashMap<>();
        this.receiver = receiver;
    }

    /**
     * Initialize commands map
     */
    public void initMap() {
        command.put("info", new InfoCommand(receiver));
        command.put("show", new ShowCommand(receiver));
        command.put("add", new AddCommand(receiver));
        command.put("update", new UpdateCommand(receiver));
        command.put("remove_by_id", new RemoveByIdCommand(receiver));
        command.put("clear", new ClearCommand(receiver));
        command.put("add_if_max", new AddIfMaxCommand(receiver));
        command.put("remove_greater", new RemoveGreaterCommand(receiver));
        command.put("remove_first", new RemoveFirstCommand(receiver));
        command.put("remove_by_engine_power", new RemoveByEnPowerCommand(receiver));
        command.put("Min_by_distance_travelled", new MinByDistanceTravelledCommand(receiver));
        command.put("filter_by_name", new FilterContainsNameCommand(receiver));
    }

    public void execute(String name, String arg) throws IncorrectArgumentException, ValidationException {
        if (command.containsKey(name)) {
            command.get(name).execute(arg);
        } else {
            System.out.println("Input is incorrect.");
        }
    }
    public boolean isStopRequested() {
        return isStopRequested;
    }

    }
