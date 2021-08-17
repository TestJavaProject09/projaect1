package utility;

import data.Coordinates;
import data.FuelType;
import data.Vehicle;
import data.VehicleType;
import exceptions.IncorrectValueException;
import exceptions.NullFieldException;

import java.time.LocalDateTime;


/**
 * This class is for creating new instances of Vehicle class
 */
public class VehicleFactory {
    private Integer id;
    private Object loadObject;

    public VehicleFactory() {
    }

    public void setLoadObject(Object loadObject) {
        this.loadObject = loadObject;
    }

    public Object getLoadObject() {
        return loadObject;
    }

    public Integer getId() {
        return id;
    }

    /**
     * Set new start point for id counter
     *
     * @param id1 - indicated start point for id counter
     */
    public void setStartId(int id1) {
        id = id1;
    }

    /**
     * Creates new vehicle with new id and creationDate
     *
     * @param name              - vehicle's name
     * @param coordinates       - vehicle's coordinates
     * @param creationDate      - vehicle's creation date
     * @param enginePower       - vehicle's engine power
     * @param distanceTravelled - vehicle's distance travelled
     * @param type              - vehicle's vehicle type
     * @param fuelType          - vehicle's fuel type
     * @return vehicle instance
     * @throws NullFieldException      if field is null, when is shouldn't be null
     * @throws IncorrectValueException - if value of the field contains wrong data, which is not allowed in this field
     */
    public Vehicle createVehicle(String name, Coordinates coordinates, LocalDateTime creationDate
            , Integer enginePower, int distanceTravelled, VehicleType type, FuelType fuelType) throws NullFieldException, IncorrectValueException {
        return createVehicleWithIdAndCreationDate(++id, name, coordinates, LocalDateTime.now(), enginePower, distanceTravelled, type, fuelType);
    }

    /**
     * Create vehicle with given id and creationDate
     *
     * @param _id               vehicle's id
     * @param name              - vehicle's name
     * @param coordinates       - vehicle's coordinates
     * @param creationDate      - vehicle's creation date
     * @param enginePower       - vehicle's engine power
     * @param distanceTravelled - vehicle's distance travelled
     * @param type              - vehicle's vehicle type
     * @param fuelType          - vehicle's fuel type
     * @return vehicle instance
     * @throws NullFieldException      if field is null, when is shouldn't be null
     * @throws IncorrectValueException - if value of the field contains wrong data, which is not allowed in this field
     */
    public Vehicle createVehicleWithIdAndCreationDate(Integer _id, String name, Coordinates coordinates, LocalDateTime creationDate
            , Integer enginePower, int distanceTravelled, VehicleType type, FuelType fuelType) throws NullFieldException, IncorrectValueException {
        if (name == null || name.length() == 0) {
            throw new NullFieldException("Name");
        }
        if (coordinates == null) {
            throw new NullFieldException("Coordinates");
        }

        if (enginePower <= 0) {
            throw new IncorrectValueException("Salary", "This field should be more than 0.");
        }
        if (creationDate == null) {
            throw new NullFieldException("StartDate");
        }
        return new Vehicle(_id, name, coordinates, creationDate, enginePower, distanceTravelled, type, fuelType);
    }


}
