// ID : 556693885
package interfaces;

import geometry.Point;
import geometry.Rectangle;
import gameplay.Velocity;
import sprites.Ball;

/**
 * In this interface we define the interfaces.Collidable functions.
 */
public interface Collidable {

    /**
     * getCollisionRectangle -- gets the rec of the collision object.
     * <p>
     *
     * @return Shapes.Rectangle - the rectangle.
     */
    Rectangle getCollisionRectangle();

    /**
     * hit -- notify that the collidable has been hit.
     * changing the velocity of the hitting object.
     * <p>
     *
     * @param collisionPoint  - the collision point.
     * @param currentVelocity - the current velocity.
     * @param hitter          - the balls that does the hit.
     * @return GameInfos.Velocity - the new velocity.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * equals -- checks if two collidables are equals.
     * <p>
     *
     * @param other - the other collidable
     * @return Boolean - yes if they are equal, false otherwise
     */
    Boolean equals(Collidable other);

}