// ID: 556693885
package hittings;

import geometry.Point;
import interfaces.Collidable;

/**
 * In this class we define the collisionInfo object.
 */
public class CollisionInfo {
    // fields
    private Point collisionPoint;
    private Collidable collidableObject;

    /**
     * Constructors.
     * <p>
     *
     * @param collisionPoint   - the point of collision.
     * @param collidableObject - the object of the collision.
     */
    public CollisionInfo(Point collisionPoint, Collidable collidableObject) {
        this.collisionPoint = collisionPoint;
        this.collidableObject = collidableObject;
    }

    /**
     * equals -- checks if two Hittings.CollisionInfo are equal.
     * <p>
     *
     * @param other - the other collisionInfo.
     * @return Boolean - true if equal false otherwise.
     */
    public Boolean equals(CollisionInfo other) {
        return this.collisionObject().equals(other.collidableObject)
                && this.collisionPoint.equals(other.collisionPoint);
    }

    /**
     * Accessors -- return the point of collision.
     * <p>
     *
     * @return Interfaces.Collidable - the collision point.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Accessors -- the collision object.
     * <p>
     *
     * @return Interfaces.Collidable - the collision object.
     */
    public Collidable collisionObject() {
        return this.collidableObject;
    }

}