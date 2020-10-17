// ID: 556693885
package interfaces;

import gameplay.Velocity;
import geometry.Rectangle;
import sprites.Block;

import java.util.List;

/**
 * In this Interface we define the LevelInformation functions.
 */
public interface LevelInformation {


    /**
     * numberOfballs -- return the number of balls in the game.
     * <p>
     *
     * @return int - the number of balls.
     */
    int numberOfBalls();


    /**
     * initialBallVelocities -- return the initialBallVelocities in the game.
     * <p>
     *
     * @return List<Velocity>  - the initialBallVelocities.
     */
    List<Velocity> initialBallVelocities();


    /**
     * paddleVelocity -- returns the velocity of the paddle.
     * <p>
     *
     * @return Velocity - the velocity of the paddle.
     */
    Velocity paddleVelocity();

    /**
     * paddleWidth -- return the width of the paddle.
     * <p>
     *
     * @return int - the width of the paddle.
     */
    int paddleWidth();


    /**
     * levelName -- return the name in the game.
     * <p>
     *
     * @return String - the name of the level.
     */
    String levelName();


    /**
     * getBackground -- return the background in the game.
     * <p>
     *
     * @return Sprite - the background of game.
     */
    Sprite getBackground();


    /**
     * blocks -- return the List<Block> of the game.
     * <p>
     *
     * @return List<Block>  - the List<Block>.
     */
    List<Block> blocks();


    /**
     * numberOfBlocks -- return the number of block in the game.
     * <p>
     *
     * @return int - the number of blocks.
     */
    int numberOfBlocksToRemove();

    /**
     * borders -- return the List<Block> borders of the game.
     * <p>
     *
     * @return List<Block>  - the List<Block> borders.
     */
    List<Block> borders();

    /**
     * deathRegion -- return the deathRegion  of the game.
     * <p>
     *
     * @return block  - the deathRegion of the game.
     */
    Block deathRegion();

    /**
     * paddle -- return the paddle of the game.
     * <p>
     *
     * @return rectangle  - the paddle of the game.
     */
    Rectangle paddle();

}