package data;

import java.time.LocalDateTime;

/**
 * info about Vehicle.
 */

public class Vehicle implements Comparable<Vehicle> {
    private final Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private final String name; //Поле не может быть null, Строка не может быть пустой
    private final Coordinates coordinates; //Поле не может быть null
    private final LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private final Integer enginePower; //Поле не может быть null, Значение поля должно быть больше 0
    private final int distanceTravelled; //Значение поля должно быть больше 0
    private final VehicleType type; //Поле не может быть null
    private final FuelType fuelType; //Поле может быть null

    public Vehicle(int id, String name, Coordinates coordinates, LocalDateTime creationDate,
                   int enginePower, int distanceTravelled, VehicleType type, FuelType fuelType) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.enginePower = enginePower;
        this.distanceTravelled = distanceTravelled;
        this.type = type;
        this.fuelType = fuelType;
    }

    /**
     * get id of Vehicle.
     *
     * @return id of vehicle.
     */
    public Integer getId() {
        return id;
    }

    /**
     * get name of Vehicle.
     *
     * @return name of vehicle.
     */
    public String getName() {
        return name;
    }

    /**
     * get coordinates of Vehicle.
     *
     * @return coordinates of vehicle.
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * get EnginePower of Vehicle.
     *
     * @return Engine Power of vehicle.
     */
    public Integer getEnginePower() {
        return enginePower;
    }

    /**
     * get Distance Travelled of Vehicle.
     *
     * @return Distance Travelled of vehicle.
     */
    public int getDistanceTravelled() {
        return distanceTravelled;
    }

    /**
     * get Type of Vehicle.
     *
     * @return type of vehicle.
     */
    public VehicleType getType() {
        return type;
    }

    /**
     * get Fuel type of Vehicle.
     *
     * @return Fuel Type of vehicle.
     */
    public FuelType getFuelType() {
        return fuelType;
    }

    /**
     * get Creation date of object.
     *
     * @return Creation date of object.
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    @Override
    public int compareTo(Vehicle vehicleObj) {
        return enginePower.compareTo(vehicleObj.getEnginePower());
    }

    @Override
    public String toString() {
        String info = "";
        info += "vehicle №" + id;
        info += " (добавлен " + creationDate.toLocalDate() + " " + creationDate.toLocalTime() + ")";
        info += "\n имя: " + name;
        info += "\n местоположение: " + coordinates;
        info += "\n мощьность двигателя: " + enginePower;
        info += "\n пройденное расстояние: " + distanceTravelled;
        info += "\n тип: " + type;
        info += "\n тип двигателя: " + fuelType;
        return info;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + coordinates.hashCode() + enginePower.hashCode() + distanceTravelled + type.hashCode() +
                fuelType.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Vehicle) {
            Vehicle vehicleObj = (Vehicle) obj;
            return name.equals(vehicleObj.getName()) && coordinates.equals(vehicleObj.getCoordinates()) &&
                    enginePower.equals(vehicleObj.getEnginePower()) &&
                    distanceTravelled == vehicleObj.getDistanceTravelled();
        }
        return false;
    }
}