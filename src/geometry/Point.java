// ID: 556693885
package geometry;

/**
 * In this class we define the Geometry.Point object.
 */
public class Point {
    // fields
    private double x;
    private double y;

    /**
     * Constructors.
     * <p>
     *
     * @param x - x of the point
     * @param y - y of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * distance -- return the distance of this point to the other point.
     * <p>
     *
     * @param other - the point to get the distance from
     * @return double - the distance
     */
    public double distance(Point other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * equals -- checks if two points are the same.
     * <p>
     *
     * @param other - the point to check if they're the same
     * @return boolean - if are the same
     */
    public boolean equals(Point other) {
        return this.x == other.x && this.y == other.y;
    }

    /**
     * Accessors -- return x and y values of the point.
     * <p>
     *
     * @return double - the x value
     */
    public double getX() {
        return this.x;
    }

    /**
     * Accessors -- return y and y values of the point.
     * <p>
     *
     * @return double - the y value
     */
    public double getY() {
        return this.y;
    }
}