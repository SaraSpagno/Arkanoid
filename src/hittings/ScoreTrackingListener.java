// ID: 556693885
package hittings;

import interfaces.HitListener;
import sprites.Ball;
import sprites.Block;

/**
 * In this class we define the scoreTrackingListener class.
 */
public class ScoreTrackingListener implements HitListener {
    // field
    private Counter currentScore;

    /**
     * Constructors.
     * <p>
     *
     * @param scoreCounter - the current score count
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }


    /**
     * hitEvent - operates in accordance after a hit has happened.
     * <p>
     *
     * @param beingHit - the object being hit
     * @param hitter   - the object that is hitting
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}
