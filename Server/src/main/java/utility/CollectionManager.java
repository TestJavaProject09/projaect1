package utility;

import data.Vehicle;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;

/**
 * This class is used to do all operations with collection
 */

public class CollectionManager {
    private Stack<Vehicle> vehicleCollection = new Stack<>();
    private boolean ExeDone;
    private final ZonedDateTime InitTime = ZonedDateTime.now();

    public CollectionManager() {
    }

    /**
     * Adds new vehicle to the collection
     *
     * @param vehicle vehicle instance to be add
     */
    public void add(Vehicle vehicle) {
        ExeDone = true;
        vehicleCollection.add(vehicle);
    }


    public void addIfMax(Vehicle vehicle) {
        try {
            ExeDone = true;
            Vehicle max;
            max = getFirst();
            for (Vehicle vehicle1 : vehicleCollection) {
                if (max.compareTo(vehicle1) > 0) {
                    max = vehicle1;
                }
            }
            if (vehicle.compareTo(max) > 0) {
                vehicleCollection.add(vehicle);
            }
        } catch (NullPointerException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public Vehicle getFirst() {
        if (vehicleCollection.isEmpty()) return null;
        return vehicleCollection.firstElement();
    }

    /**
     * @return Vehicle, who has min distance travelled.
     */
    public Vehicle minByDistanceTravelled() {
        return Collections.min(vehicleCollection, Comparator.comparingInt(Vehicle::getDistanceTravelled));
    }

    public boolean removeFromCollection(Vehicle vehicle) {
        vehicleCollection.remove(vehicle);
        return true;
    }

    /**
     * @param name filtered vehicle.
     * @return compare name with string.
     */

    public String vehicleFilteredInfo(String name) {
        StringBuilder info = new StringBuilder();
        for (Vehicle vehicle : vehicleCollection) {
            if (vehicle.getName().contains(name)) {
                info.append(vehicle).append("\n\n");
            }
        }
        return info.toString().trim();
    }

    public Vehicle getByEnginePower(Integer enginePower) {
        for (Vehicle vehicle : vehicleCollection) {
            if (vehicle.getEnginePower().equals(enginePower)) return vehicle;
        }
        return null;
    }

    public void removeById(Integer id) {
        vehicleCollection.removeIf(vehicle -> vehicle.getId() == id);
    }

    public boolean removeGreater(Vehicle vehicle) {
        boolean temp = false;
        for (Vehicle vehicle1 : vehicleCollection) {
            if (vehicle.compareTo(vehicle1) > 0) {
                vehicleCollection.remove(vehicle1);
                temp = true;
            }
        }
        return temp;
    }

    public Stack<Vehicle> show() {
        return vehicleCollection.stream().sorted(Comparator.comparing(Vehicle::getName)).collect(Stack::new, Stack::add, Stack::addAll);
    }

    public void update(Integer id, Vehicle vehicle) {
        vehicleCollection.forEach(vehicle1 -> {
            if (vehicle1.getId() == id) {
                vehicle1.setName(vehicle.getName());
                vehicle1.setCoordinates(vehicle.getCoordinates());
                vehicle1.setEnginePower(vehicle.getEnginePower());
                vehicle1.setDistanceTravelled(vehicle.getDistanceTravelled());
                vehicle1.setType(vehicle.getFuelType());
                vehicle1.setFuelType(vehicle.getType());
            }
        });
    }


    /**
     * Remove all elements from collection
     */
    public void clear() {
        ExeDone = true;
        vehicleCollection.clear();
    }

    /**
     * @return true if collection have unsaved changes
     */
    public boolean exeDone() {
        return ExeDone;
    }

    /**
     * @return string array with information about collection
     */
    public String getInfo() {
        String Type = "Type: Collection of vehicle's type objects\n";
        String Init = "Initialization time: " + InitTime.toString() + "\n";
        String Size = "Number of elements: " + vehicleCollection.size() + "\n";
        String State;
        if (exeDone()) {
            State = "Collection has been modified.";
        } else {
            State = "Collection hasn't been modified yet.";
        }
        return Type + Init + Size + State;
    }

    /**
     * @return copy collection with vehicles
     */
    public Stack<Vehicle> getCollection() {
        return vehicleCollection;
    }

    public void setCollection(Stack<Vehicle> collection) {
        this.vehicleCollection = collection;
    }

    /**
     * Load collection from indicated file
     *
     * @param collectionFromFile external collection of vehicle instances
     */
    public void load(Collection<Vehicle> collectionFromFile) {
        vehicleCollection.addAll(collectionFromFile);
    }

    public Integer getLastId() {
        Integer lastId = 0;
        for (Vehicle v : vehicleCollection) {
            if (v.getId() > lastId) {
                lastId = v.getId();
            }
        }
        return lastId;
    }

}