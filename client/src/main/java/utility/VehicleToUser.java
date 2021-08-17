package utility;

import data.Vehicle;

/**
 * This class is used for describing the Vehicle class instance
 */
public class VehicleToUser {
    /**
     * Describe vehicle in console
     *
     * @param vehicle - vehicle class instance to be described
     */
    public void vehicleToConsole(Vehicle vehicle) {
        System.out.println();
        System.out.println("Vehicle");
        System.out.println("Id: " + vehicle.getId());
        System.out.println("Name: " + vehicle.getName());
        System.out.println("Coordinates: X-" + vehicle.getCoordinates().getX() + " Y-" + vehicle.getCoordinates().getY());
        System.out.println("Creation Date: " + vehicle.getCreationDate());
        System.out.println("Engine power: " + vehicle.getEnginePower());
        System.out.println("DistanceTravelled: " + vehicle.getDistanceTravelled());
        System.out.println("VehicleType: " + vehicle.getType());
        System.out.println("FuelType: " + vehicle.getFuelType());
    }
}
