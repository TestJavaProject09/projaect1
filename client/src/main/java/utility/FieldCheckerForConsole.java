package utility;

import data.FuelType;
import data.VehicleType;
import exceptions.IncorrectValueException;
import exceptions.NullFieldException;

import java.util.NoSuchElementException;

public class FieldCheckerForConsole {
    private final Console console;

    public FieldCheckerForConsole(Console console) {
        this.console = console;
    }


    public <T> T readAndCheckField(String FieldName, String error, FieldCheckerHelp<T> rule) {
        T temp = null;
        while (true) {
            System.out.println("Enter vehicle`s " + FieldName + ":");
            try {
                temp = rule.check(console.readln());
            } catch (NumberFormatException exception) {
                System.out.println("Input is incorrect. Please, try again." + error);
                continue;
            } catch (NoSuchElementException exception) {
                continue;
            } catch (IncorrectValueException | NullFieldException e) {
                System.out.println(e.getMessage());
            }
            return temp;
        }
    }


    public String readAndCheckName() {
        FieldCheckerHelp<String> tempInterface = str -> {
            if (str == null || str.equals("")) {
                throw new NullFieldException("name");
            }
            return str;
        };
        return readAndCheckField("name", "", tempInterface);
    }

    public Integer readAndCheckX() {
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
        return readAndCheckField("coordinate X", "(Reminder: Coordinate X can't be more than 768.)", tempInterface);
    }

    public Integer readAndCheckY() {
        FieldCheckerHelp<Integer> tempInterface = str -> {
            Integer result = null;
            if (str != null) {
                result = Integer.parseInt(str);
            } else throw new NullFieldException("coordinate Y");
            return result;
        };
        return readAndCheckField("coordinate Y", "", tempInterface);
    }

    public Integer readAndCheckEnginePower() {
        FieldCheckerHelp<Integer> tempInterface = str -> {
            Integer result;
            if (str != null) {
                result = Integer.parseInt(str);
                if (result <= 0) {
                    throw new IncorrectValueException("engine power", "(Engine power should be more than 0.)");
                }
            } else {
                throw new NullFieldException("Engine power");
            }
            return result;
        };
        return readAndCheckField("engine power", "(Engine power should be more than 0.)", tempInterface);
    }

    public Integer readAndCheckDisTravelled() {
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
        return readAndCheckField("distance travelled", "(Distance travelled should be more than 0.)", tempInterface);
    }

    public VehicleType readAndCheckVType() {
        for (VehicleType VType : VehicleType.values()) {
            System.out.println(VType.toString());
        }
        FieldCheckerHelp<VehicleType> tempInterface = str -> {
            if (str == null || str.equals("")) {
                return null;
            } else {
                return VehicleType.valueOf(str.toUpperCase());
            }
        };
        return readAndCheckField("Vehicle Type", "", tempInterface);
    }

    public FuelType readAndCheckFType() {
        for (FuelType FType : FuelType.values()) {
            System.out.println(FType.toString());
        }
        FieldCheckerHelp<FuelType> tempInterface = str -> {
            if (str == null || str.equals("")) {
                return null;
            } else {
                return FuelType.valueOf(str.toUpperCase());
            }
        };
        return readAndCheckField("Fuel Type", "", tempInterface);
    }
}