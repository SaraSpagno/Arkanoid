// ID: 556693885
package geometry;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In this class we define the Geometry.Line object.
 */
public class Line {
    // fields
    private Point start;
    private Point end;

    /**
     * Constructors.
     * <p>
     *
     * @param start - the start of the point
     * @param end   - the end of the point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructors.
     * <p>
     *
     * @param x1 - the x of the start point
     * @param y1 - the y of the start point
     * @param x2 - the x of the end point
     * @param y2 - the y of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * lenght -- return the lenght of the line.
     * <p>
     *
     * @return double -- the lenght of the line
     */
    public double length() {
        return this.start.distance(end);
    }

    /**
     * middle -- return the middle point of a line.
     * <p>
     *
     * @return Geometry.Point -- the middle point
     */
    public Point middle() {
        double mx = (start.getX() + end.getX()) / 2;
        double my = (start.getY() + end.getY()) / 2;
        return new Point(mx, my);
    }

    /**
     * Accessors -- return the start point of a line.
     * <p>
     *
     * @return Geometry.Point -- the start point
     */
    public Point start() {
        return this.start;
    }

    /**
     * Accessors -- return the end point of a line.
     * <p>
     *
     * @return Geometry.Point -- the end point
     */
    public Point end() {
        return this.end;
    }

    /**
     * isIntersecting -- checks if two lines are intersecting.
     * <p>
     *
     * @param other - the other line
     * @return boolean -- true if the lines are intersecting, false otherwwise
     */
    public boolean isIntersecting(Line other) {
        // using the next function to simplify - intersectionWith
        return intersectionWith(other) != null;
    }

    /**
     * intersectionWith -- checks if two lines are intersecting and returns the point of intersection.
     * <p>
     *
     * @param other - the other line
     * @return Geometry.Point --  the point of intersection
     */
    public Point intersectionWith(Line other) {

        // Geometry.Line AB represented as a1x + b1y = c1
        double a1 = end.getY() - start.getY();
        double b1 = start.getX() - end.getX();
        double c1 = a1 * (start.getX()) + b1 * (start.getY());

        // Geometry.Line CD represented as a2x + b2y = c2
        double a2 = other.end.getY() - other.start.getY();
        double b2 = other.start.getX() - other.end.getX();
        double c2 = a2 * (other.start.getX()) + b2 * (other.start.getY());

        // determinant
        double determinant = a1 * b2 - a2 * b1;

        //lines are not parallel
        if (determinant != 0) {
            // getting point of intersection of the area
            double x = (b2 * c1 - b1 * c2) / determinant;
            double y = (a1 * c2 - a2 * c1) / determinant;

            // checking if the lays is on both lines
            // using function "onSegment" implemented by me following
            if (onSegment(start.getX(), end.getX(), start.getY(), end.getY(), x, y)) {
                if (onSegment(other.start.getX(), other.end.getX(), other.start.getY(), other.end.getY(), x, y)) {
                    // if its on both, returning the point
                    return new Point(x, y);
                }
            }
        }
        return null;
    }

    /**
     * onSegment -- checks if a point is on a segment.
     * <p>
     *
     * @param x1 - x of the start of the line
     * @param x2 - x of the end of the second line
     * @param y1 - y of the start of the first line
     * @param y2 - y of the end of the line
     * @param x  - x of the point to check
     * @param y  - y of the point to check
     * @return Boolean --  the point of intersection
     */
    boolean onSegment(double x1, double x2, double y1, double y2, double x, double y) {
        // check if x is between max and min of x1,x2 (same for y)
        double nanoDouble = 0.001;
        return (Math.min(x1, x2) - x <= nanoDouble) && (x - Math.max(x1, x2)) <= nanoDouble
                && (Math.min(y1, y2) - y <= nanoDouble) && (y - Math.max(y1, y2) <= nanoDouble);
    }

    /**
     * equals -- checks if two lines are equal.
     * <p>
     *
     * @param other - the line to check if its equal to.
     * @return Boolean --  true if the lines are equal
     */
    public boolean equals(Line other) {
        return this.start == other.start && this.end == other.end;
    }

    /**
     * closestIntersectionToStartOfLine -- among all the
     * intersections Points between a specific rectangle.
     * <p>
     *
     * @param rect - the rectangle to check.
     * @return Geometry.Point --  the point found.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        // creating a list of the intersection points between line and rec
        List<Point> points = rect.intersectionPoints(new Line(this.start, this.end));
        if (points.isEmpty()) {
            return null;
        }
        // creating a dictionary mapping points to their distance
        Map<Double, Point> distances = new HashMap<>();
        for (Point point : points) {
            distances.put(this.start.distance(point), point);
        }
        // returning the point with the smallest distance
        return distances.get(Collections.min(distances.keySet()));
    }

}
