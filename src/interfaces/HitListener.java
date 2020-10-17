// ID: 556693885
package interfaces;

import sprites.Ball;
import sprites.Block;

/**
 * In this interface we define the hitListener functions.
 */
public interface HitListener {

    /**
     * hitEvent - operates in accordance after a hit has happened.
     * <p>
     *
     * @param beingHit - the object being hit
     * @param hitter   - the object that is hitting
     */
    void hitEvent(Block beingHit, Ball hitter);
}