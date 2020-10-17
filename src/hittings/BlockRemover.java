// ID: 556693885
package hittings;

import animations.GameLevel;
import interfaces.HitListener;
import sprites.Ball;
import sprites.Block;

/**
 * In this class we define the BlockRemover class.
 */
public class BlockRemover implements HitListener {
    // fields
    private GameLevel gameLevel;
    private Counter remainingBlocks;


    /**
     * Constructors.
     * <p>
     *  @param gameLevel          - the game in which we are playing
     * @param removedBlocks - the blocks we have removed
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }


    /**
     * hitEvent - operates in accordance after a hit has happened.
     * <p>
     *
     * @param beingHit - the object being hit
     * @param hitter   - the object that is hitting
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(gameLevel);
        beingHit.removeHitListener(this);
        remainingBlocks.decrease(1);

    }
}