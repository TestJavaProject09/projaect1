package request;

import data.Vehicle;

import java.io.Serializable;
import java.util.Stack;

public class SerializationForClient implements Serializable {
    private boolean status;
    private String message;
    private Long count;
    private Stack<Vehicle> vehicles;

    public SerializationForClient(boolean st, String mes, Long co, Stack<Vehicle> v) {
        setStatus(st);
        setMessage(mes);
        setCount(co);
        setVehicles(v);
    }

    public boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Long getCount() {
        return count;
    }

    public Stack<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Stack<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
