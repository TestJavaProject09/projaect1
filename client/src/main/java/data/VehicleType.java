package data;

/**
 * Enumeration with Vehicle category constants.
 */
public enum VehicleType {
    HELICOPTER,
    DRONE,
    CHOPPER,
    SPACESHIP;

    /**
     * Generates a beautiful list of enum string values.
     *
     * @return String with all enum values splitted by comma.
     */
    public static String nameList() {
        StringBuilder nameList = new StringBuilder();
        for (VehicleType type : values()) {
            nameList.append(type.name()).append(", ");
        }
        return nameList.substring(0, nameList.length() - 2);
    }
}
