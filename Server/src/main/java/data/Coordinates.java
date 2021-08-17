package data;

/**
 * X-Y coordinates.
 */

public class Coordinates {
    private final float x;
    private final float y;


    public Coordinates(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return X-coordinate.
     */
    public float getX() {
        return x;
    }

    /**
     * @return Y-coordinate.
     */
    public float getY() {
        return y;
    }

    @Override
    public String toString() {
        return "X:" + x + " Y:" + y;
    }

    @Override
    public int hashCode() {
        return (int) y + (int) x;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Coordinates) {
            Coordinates coordinatesObj = (Coordinates) obj;
            return (x == coordinatesObj.getX()) && (y == coordinatesObj.getY());
        }
        return false;
    }
}
