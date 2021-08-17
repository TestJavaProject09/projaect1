package utility;

import data.FuelType;
import data.VehicleType;
import exceptions.IncorrectValueException;
import exceptions.NullFieldException;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class FieldCheckerForScript {

    public FieldCheckerForScript() {

    }

    public <T> T readAndCheckField(String field, String FieldName, String error, FieldCheckerHelp<T> rule) throws DateTimeParseException, NullFieldException, IncorrectValueException {
        T temp = null;
        temp = rule.check(field);
        return temp;
    }


    public String readAndCheckName(String name) throws NullFieldException, IncorrectValueException {
        FieldCheckerHelp<String> tempInterface = str -> {
            if (str == null || str.equals("")) {
                throw new NullFieldException("name");
            }
            return str;
        };
        return readAndCheckField(name, "name", "", tempInterface);
    }

    public Integer readAndCheckX(String coordinateX) throws NumberFormatException, IncorrectValueException, NullFieldException {
        FieldCheckerHelp<Integer> tempInterface = str -> {
            Integer result = null;
            if (str != null) {
                result = Integer.parseInt(str);
                if (result > 768) {
                    throw new IncorrectValueException("coordinate X", "(Reminder: Coordinate X can't be more than 768.)");
                }
            } else throw new NullFieldException("coordinate X");
            return result;
        };
        return readAndCheckField(coordinateX, "coordinate X", "(Reminder: Coordinate X can't be more than 768.)", tempInterface);
    }

    public Integer readAndCheckY(String coordinateY) throws NumberFormatException, IncorrectValueException, NullFieldException {
        FieldCheckerHelp<Integer> tempInterface = str -> {
            Integer result = null;
            if (str != null) {
                result = Integer.parseInt(str);
            } else throw new NullFieldException("coordinate Y");
            return result;
        };
        return readAndCheckField(coordinateY, "coordinate Y", "", tempInterface);
    }

    public Integer readAndCheckEnginePower(String enginePower) throws NumberFormatException, IncorrectValueException, NullFieldException {
        FieldCheckerHelp<Integer> tempInterface = str -> {
            Integer result;
            if (str != null) {
                result = Integer.parseInt(str);
                if (result <= 0) {
                    throw new IncorrectValueException("engine power", "Engine power should be more than 0.)");
                }
            } else {
                throw new NullFieldException("engine power");
            }
            return result;
        };
        return readAndCheckField(enginePower, "enginePower", "(Reminder: Engine Power should be more than 0.)", tempInterface);
    }

    public Integer readAndCheckDisTravelled(String DistanceTravelled) throws NumberFormatException, IncorrectValueException, NullFieldException {
        FieldCheckerHelp<Integer> tempInterface = str -> {
            Integer result = null;
            if (str != null) {
                result = Integer.parseInt(str);
                if (result <= 0) {
                    throw new IncorrectValueException("distance travelled", "(Distance travelled should be more than 0.)");
                }
            }
            return result;
        };
        return readAndCheckField(DistanceTravelled, "distance travelled", "(Distance travelled should be more than 0.)", tempInterface);
    }

    public VehicleType readAndCheckVType(String VType) throws IncorrectValueException, NullFieldException {
        FieldCheckerHelp<VehicleType> tempInterface = str -> {
            if (str == null || str.equals("")) {
                return null;
            } else {
                return VehicleType.valueOf(str.toUpperCase());
            }
        };
        return readAndCheckField(VType, "Vehicle Type", "", tempInterface);
    }

    public FuelType readAndCheckFType(String FType) throws IncorrectValueException, NullFieldException {
        FieldCheckerHelp<FuelType> tempInterface = str -> {
            if (str == null || str.equals("")) {
                return null;
            } else {
                return FuelType.valueOf(str.toUpperCase());
            }
        };
        return readAndCheckField(FType, "Fuel Type", "", tempInterface);
    }
}
