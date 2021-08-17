package request;

import data.Vehicle;

import java.io.Serializable;

public class Serialization implements Serializable {
    private String command;
    private String arg;
    private Vehicle vehicle;
    private String dataLine;
    private Vehicle dataVehicle;

    public Serialization(String command, String arg, Vehicle vehicle) {
        this.command = command;
        this.arg = arg;
        this.vehicle = vehicle;
    }

    public Serialization(String line) {
        dataLine = line;
    }

    public Serialization(Vehicle vehicle) {
        dataVehicle = vehicle;
    }

    public String getDataLine() {
        return dataLine;
    }

    public Vehicle getDataVehicle() {
        return dataVehicle;
    }

    public String getCommand() {
        return command;
    }

    public String getArg() {
        return arg;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
