package data;

/**
 * Enumeration with vehicle's fuel type category constants.
 */

public enum FuelType {
    GASOLINE,
    ALCOHOL,
    NUCLEAR,
    PLASMA,
    NULL;


    /**
     * Generates a beautiful list of enum string values.
     *
     * @return String with all enum values splitted by comma.
     */
    public static String nameList() {
        StringBuilder nameList = new StringBuilder();
        for (FuelType fuelType : values()) {
            nameList.append(fuelType.name()).append(", ");
        }
        return nameList.substring(0, nameList.length() - 2);
    }
}
