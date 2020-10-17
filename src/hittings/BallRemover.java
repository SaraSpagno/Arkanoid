// ID: 556693885
package hittings;

import animations.GameLevel;
import interfaces.HitListener;
import sprites.Ball;
import sprites.Block;


/**
 * In this class we define the BallRemover class.
 */
public class BallRemover implements HitListener {
    // fields
    private GameLevel gameLevel;
    private Counter remainingBalls;


    /**
     * Constructors.
     * <p>
     *  @param gameLevel          - the game in which we are playing
     * @param removedBalls - the balls we have removed
     */
    public BallRemover(GameLevel gameLevel, Counter removedBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = removedBalls;
    }


    /**
     * hitEvent - operates in accordance after a hit has happened.
     * <p>
     *
     * @param beingHit - the object being hit
     * @param hitter   - the object that is hitting
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(gameLevel);
        remainingBalls.decrease(1);
    }
}