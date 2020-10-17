// ID: 556693885
package geometry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * In this class we define the Geometry.Rectangle object.
 */
public class Rectangle {

    // fields
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Constructors.
     * <p>
     *
     * @param upperLeft - the upperLeft point of the rec.
     * @param width     - the width of the rec.
     * @param height    - the height of the rec.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * intersectionPoint -- returns a list of
     * intersection points.
     * <p>
     *
     * @param line - the line to of the intersection.
     * @return java.util.List<Geometry.Point> - the list of found points.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        // setting an array with the 4 perimeter lines
        Line[] perimeter = new Line[4];
        perimeter[0] = new Line(upperLeft, new Point(upperLeft.getX() + width, upperLeft.getY()));

        perimeter[1] = new Line(upperLeft, new Point(upperLeft.getX(), upperLeft.getY() + height));

        perimeter[2] = new Line(upperLeft.getX() + width,
                upperLeft.getY(), upperLeft.getX() + width, upperLeft.getY() + height);

        perimeter[3] = new Line(upperLeft.getX(), upperLeft.getY() + height,
                upperLeft.getX() + width, upperLeft.getY() + height);

        // creating the list of points
        List<Point> points = new ArrayList<>();
        for (Line l : perimeter) {
            if (l.isIntersecting(line)) {
                points.add(l.intersectionWith(line));
            }
        }
        return points;

    }

    /**
     * Accessors -- return the width of the rec.
     * <p>
     *
     * @return double - the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Accessors -- return the height of the rec.
     * <p>
     *
     * @return double - the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Accessors -- return the upperLeft point of the rec.
     * <p>
     *
     * @return Geometry.Point - the upperLeft Geometry.Point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * insideRectangle -- checks if a point is inside the rectangle.
     * <p>
     *
     * @param other - the point to check
     * @return Map<Integer, Geometry.Point>, the point a new point just outside the rectangle,
     * Integer represents in which half of the rectangle is the ball. -1 = left, 1 = right.
     */
    public Map<Integer, Point> insideRectangle(Point other) {
        Map<Integer, Point> result = new HashMap<>();
        // check the x
        if (other.getX() > this.upperLeft.getX() && other.getX() < this.upperLeft.getX()
                + this.width && other.getY() > this.upperLeft.getY()) {
            // the point is inside
            // if it's in the first half --- left = -1
            double halfX = (2 * this.upperLeft.getX() + this.width) / 2;
            if (other.getX() < halfX) {
                result.put(-1, new Point(this.upperLeft.getX(), this.upperLeft.getY() - 1));
                return result;

            }
            // if it's in the second half
            result.put(1, new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() - 1));
            return result;
        }
        return null;
    }
}