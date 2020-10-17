// ID: 556693885
package collections;

import hittings.CollisionInfo;
import geometry.Line;
import geometry.Point;
import interfaces.Collidable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * In this class we define the Collections.GameEnvironment object.
 */
public class GameEnvironment {
    // field
    private List<Collidable> collidables = new ArrayList<>();

    /**
     * addCollidable -- adds the collidable to the list.
     * <p>
     *
     * @param c - the collidable to add.
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * getClosestCollision -- gets the closest collision of a Geometry.Line.
     * <p>
     *
     * @param trajectory - the trajectory line.
     * @return CollisionInfo - the collisionInfo created
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        // creating two new dictionaries
        Map<Point, Collidable> info = new HashMap<>();
        Map<Double, Point> distances = new HashMap<>();

        for (Collidable c : collidables) {
            // getting the closest point to the start of the line for each collidable
            Point closestToStartOfCollidable = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (closestToStartOfCollidable != null) {
                // adding the line, point and distance to the dictionaries
                info.put(closestToStartOfCollidable, c);
                distances.put(closestToStartOfCollidable.distance(trajectory.start()), closestToStartOfCollidable);
            }
        }

        if (!(info.isEmpty())) {
            // getting the point with the minimum distance
            Point closestToStart = distances.get(Collections.min(distances.keySet()));
            // returning a new collision info
            return new CollisionInfo(closestToStart, info.get(closestToStart));
        }
        return null;
    }


    /**
     * removeCollidable -- removes the collidable to the list.
     * <p>
     *
     * @param c - the collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }

}