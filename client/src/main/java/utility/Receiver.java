package utility;

import clientcommands.Command;
import data.Vehicle;
import exceptions.*;
import request.AnswerReader;
import request.RequestSender;
import request.SerializationFromClient;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Receiver {
    private final VehicleFactory vehicleFactory = new VehicleFactory(1);
    private final DatagramChannel datagramChannel;
    private final SocketAddress socketAddress;
    private final RequestSender requestSender;
    private HashMap<String, Command> commands;
    private final Invoker invoker;
    private final AnswerReader answerReader;

    public Receiver(DatagramChannel datagramChannel, SocketAddress socketAddress, Console console, Invoker invoker) {
        this.datagramChannel = datagramChannel;
        this.socketAddress = socketAddress;
        requestSender = new RequestSender(datagramChannel, socketAddress);
        vehicleFactory.setConsole(console);
        answerReader = console.getAnswerReader();
        this.invoker = invoker;
    }


    public void help(HashMap<String, Command> commands) {
        this.commands = commands;
        commands.forEach((key, value) -> System.out.println(key + " - " + value.getDescription()));
    }

    public void add() {
        try {
            requestSender.sendRequest(new SerializationFromClient("add", null, vehicleFactory.getVehicleFromConsole()));
        } catch (NullFieldException | IncorrectValueException | IncorrectArgumentException e) {
            System.out.println("Vehicle can't be created. Please, try again.");
        }
    }

    public void addIfMax() {
        try {
            requestSender.sendRequest(new SerializationFromClient("add_if_max", null, vehicleFactory.getVehicleFromConsole()));
        } catch (IncorrectValueException | NullFieldException | IncorrectArgumentException e) {
            System.out.println("Vehicle can't be created. Please, try again.");
        }
    }

    public void clear() {
        requestSender.sendRequest(new SerializationFromClient("clear", null, null));
    }

    public void minByDisTravelled() {
        requestSender.sendRequest(new SerializationFromClient("min_by_dis_travelled", null, null));
    }

    public void filterContainsName(String arg) {
        requestSender.sendRequest(new SerializationFromClient("filter_name", arg, null));
    }

    public void groupCountingByPosition() {
        requestSender.sendRequest(new SerializationFromClient("group_counting_by_position", null, null));
    }

    public void info() {
        requestSender.sendRequest(new SerializationFromClient("info", null, null));
    }

    public void removeById(String arg) {
        requestSender.sendRequest(new SerializationFromClient("remove_by_id", arg, null));
    }

    public void removeByEnPower(String arg) {
        requestSender.sendRequest(new SerializationFromClient("remove_by_engine_power", arg, null));
    }

    public void removeGreater() {
        try {
            requestSender.sendRequest(new SerializationFromClient("remove_greater", null, vehicleFactory.getVehicleFromConsole()));
        } catch (IncorrectValueException | NullFieldException | IncorrectArgumentException e) {
            System.out.println("Vehicle can't be created. Please, try again.");
        }
    }

    public void removeFirst() {
        try {
            requestSender.sendRequest(new SerializationFromClient("remove_first", null, vehicleFactory.getVehicleFromConsole()));
        } catch (IncorrectValueException | NullFieldException | IncorrectArgumentException e) {
            System.out.println("Vehicle can't be created. Please, try again.");
        }
    }

    public void show() {
        requestSender.sendRequest(new SerializationFromClient("show", null, null));
    }

    public void update(String arg) throws ValidationException, IncorrectArgumentException {
        requestSender.sendRequest(new SerializationFromClient("validate_id", arg, null));
        try {
            if (answerReader.readValidation()) {
                try {
                    requestSender.sendRequest(new SerializationFromClient("update", arg, vehicleFactory.getVehicleFromConsole()));
                } catch (IncorrectValueException | NullFieldException e) {
                    System.out.println("Vehicle can't be created. Please, try again.");
                }
            } else throw new ValidationException();
        } catch (ServerIsNotAvailableException e) {
            System.out.println(e.getMessage());
        }

    }

    public void executeScript(String path) {
        String line;
        String command = "";
        String arg;
        Pattern commandNamePattern = Pattern.compile("^\\w+");
        Pattern argPattern = Pattern.compile("\\b(.*\\s*)*");
        String[] parameters = new String[9];
        Vehicle tempVehicle;
        answerReader.setAnswerAccepted(true);
//        System.out.println(invoker.getFilePaths().toString());
        invoker.addPath(path);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            while (true) {
                if (answerReader.isAnswerAccepted()) {
                    answerReader.setAnswerAccepted(false);
                    line = bufferedReader.readLine();
                    if (line == null || line.equals("")) {
                        System.out.println("Command \"execute_script\" has finished.");
                        invoker.deletePath(path);
                        if (!invoker.getReaders().isEmpty()) {
                            invoker.getBufferedReader();
                        }
                        break;
                    } else {
                        Matcher matcher = commandNamePattern.matcher(line);
                        if (matcher.find()) {
                            command = matcher.group();
                        } else {
                            System.out.println("Input is not a command.");
                            answerReader.setAnswerAccepted(true);
                            continue;
                        }
                        line = line.substring(command.length());
                        matcher = argPattern.matcher(line);
                        if (matcher.find()) {
                            arg = line.trim();
                        } else {
                            arg = "";
                        }
                        if (command.equals("execute_script")) {
                            invoker.addBufferedReader(bufferedReader);
                        }
                        if (invoker.getFilePaths().contains(arg)) {
                            System.out.println("Recursion has occurred. Please, correct your script.");
                            invoker.deletePath(path);
                            answerReader.setAnswerAccepted(true);
                            continue;
                        }
                        if (command.equals("add") || command.equals("update") || command.equals("remove_greater") || command.equals("add_if_max")) {
                            for (int i = 0; i < 9; i++) {
                                line = bufferedReader.readLine();
                                parameters[i] = line;
                            }
                            tempVehicle = vehicleFactory.getVehicleFromScript(parameters);
                            if (tempVehicle == null) {
                                System.out.println("Vehicle haven't passed validation. Command " + command + " won't be execute.");
                                answerReader.setAnswerAccepted(true);
                                continue;
                            }
                            requestSender.sendRequest(new SerializationFromClient(command, arg, tempVehicle));
                        } else {
                            try {
                                invoker.exe(command, arg);
                            } catch (UnknownCommandException | IncorrectArgumentException | ValidationException exception) {
                                System.out.print(exception.getMessage() + "\n");
                                answerReader.setAnswerAccepted(true);
                                continue;
                            }
                            if (command.equals("execute_script")) {
                                bufferedReader = invoker.getBufferedReader();
                                answerReader.setAnswerAccepted(true);
                            }
                        }
                        if (!command.equals("execute_script") && !command.equals("help")) {
                            try {
                                answerReader.readAnswer();
                            } catch (ServerIsNotAvailableException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }
                } else continue;
            }
            bufferedReader.close();
            invoker.getFilePaths().clear();
        } catch (IOException | IncorrectArgumentException e) {
            System.out.println("File can't be ran by client. (Check file's rights.)");
        }
    }
}