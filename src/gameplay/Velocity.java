// ID : 556693885
package gameplay;

import geometry.Point;

/**
 * In this class we define the GameInfos.Velocity Object.
 */
public class Velocity {
    //field
    private Point d;

    /**
     * Constructors.
     * <p>
     *
     * @param dx - x velocity advancement
     * @param dy - y velocity advancement
     */
    public Velocity(double dx, double dy) {
        this.d = new Point(dx, dy);

    }

    /**
     * fromAngleAndSpee -- creates a velocity from an angle and a speed.
     * <p>
     *
     * @param angle - the angle
     * @param speed - the speed
     * @return GameInfos.Velocity - a new velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radians = Math.toRadians(angle);
        double dx = speed * Math.sin(radians);
        double dy = -speed * Math.cos(radians);
        return new Velocity(dx, dy);
    }

    /**
     * applyToPoint -- applies the velocity to a point.
     * take a point with position (x,y) and return a new point.
     * <p>
     *
     * @param p -- the point to apply the velocity on
     * @return Geometry.Point -- the new advanced point
     */
    public Point applyToPoint(Point p) {
        Point now1 = new Point(p.getX() + d.getX(), p.getY() + d.getY());
        return now1;
    }

    /**
     * Accessors -- return the point of the velocity.
     * <p>
     *
     * @return Geometry.Point -- the point of the velocity
     */
    public Point getPoint() {
        return this.d;
    }

    /**
     * Accessors -- return the x of the point of the velocity.
     * <p>
     *
     * @return double -- the x
     */
    public double getX() {
        return this.getPoint().getX();
    }

    /**
     * Accessors -- return the y of the point of the velocity.
     * <p>
     *
     * @return double -- the y
     */
    public double getY() {
        return this.getPoint().getY();
    }
}