//ID: 5566993885
package levels;

import gameplay.Velocity;
import geometry.Point;
import geometry.Rectangle;
import interfaces.LevelInformation;
import interfaces.Sprite;
import sprites.Block;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * In this Interface we define the level object.
 */
public class StandardLevel implements LevelInformation {

    private int frameWidth = 800;
    private int frameHeight = 600;
    private String levelName;
    private Velocity paddleVelocity;
    private int paddleWidth;
    private Sprite background;
    private List<Velocity> initialBallVelocities;
    private List<Block> blocks;
    private int blocksToRemove;
    private int paddleHeight = 14;


    /**
     * Constructor.
     * <p>
     *
     * @param levelName             - the name of the level.
     * @param paddleVelocity        - the velocity of the paddle.
     * @param paddleWidth           - the width of the paddle.
     * @param background            - the background of the level.
     * @param initialBallVelocities - the initialBallVelocities.
     * @param blocks                - the array of blocks.
     * @param blocksToRemove        - the num of blocks to remove.
     */
    public StandardLevel(String levelName, Velocity paddleVelocity, int paddleWidth, Sprite background,
                         List<Velocity> initialBallVelocities, List<Block> blocks, int blocksToRemove) {
        this.levelName = levelName;
        this.paddleVelocity = paddleVelocity;
        this.paddleWidth = paddleWidth;
        this.background = background;
        this.initialBallVelocities = initialBallVelocities;
        this.blocks = blocks;
        this.blocksToRemove = blocksToRemove;
    }

    /**
     * numberOfballs -- return the number of balls in the game.
     * <p>
     *
     * @return int - the number of balls.
     */
    @Override
    public int numberOfBalls() {
        return initialBallVelocities.size();

    }

    /**
     * initialBallVelocities -- return the initialBallVelocities in the game.
     * <p>
     *
     * @return List<Velocity>  - the initialBallVelocities.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        return initialBallVelocities;
    }

    /**
     * paddleVelocity -- returns the velocity of the paddle.
     * <p>
     *
     * @return Velocity - the velocity of the paddle.
     */
    @Override
    public Velocity paddleVelocity() {
        return this.paddleVelocity;
    }

    /**
     * paddleWidth -- return the width of the paddle.
     * <p>
     *
     * @return int - the width of the paddle.
     */
    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * levelName -- return the name in the game.
     * <p>
     *
     * @return String - the name of the level.
     */
    @Override
    public String levelName() {
        return this.levelName;
    }

    /**
     * getBackground -- return the background in the game.
     * <p>
     *
     * @return Sprite - the background of game.
     */
    @Override
    public Sprite getBackground() {
        return this.background;
    }

    /**
     * blocks -- return the List<Block> of the game.
     * <p>
     *
     * @return List<Block>  - the List<Block>.
     */
    @Override
    public List<Block> blocks() {
        return blocks;
    }

    /**
     * numberOfBlocks -- return the number of block in the game.
     * <p>
     *
     * @return int - the number of blocks.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return blocksToRemove;
    }


    /**
     * borders -- return the List<Block> borders of the game.
     * <p>
     *
     * @return List<Block>  - the List<Block> borders.
     */
    @Override
    public List<Block> borders() {
        List<Block> borderBlocks = new ArrayList<>();

        // borders
        int scInHeight = 25;  // scoreIndicator height
        int recSize = 10;
        Rectangle recUp = new Rectangle(new Point(0, scInHeight), frameWidth, recSize);
        Rectangle recLeft = new Rectangle(new Point(0, scInHeight + recSize), recSize, frameHeight - recSize);
        Rectangle recRight = new Rectangle(new Point(frameWidth - recSize, scInHeight + recSize),
                recSize, frameHeight - recSize);

        Block up = new Block(recUp, Color.GRAY, null, null);
        Block left = new Block(recLeft, Color.GRAY, null, null);
        Block right = new Block(recRight, Color.GRAY, null, null);

        borderBlocks.add(up);
        borderBlocks.add(left);
        borderBlocks.add(right);
        return borderBlocks;
    }

    /**
     * deathRegion -- return the deathRegion  of the game.
     * <p>
     *
     * @return block  - the deathRegion of the game.
     */
    @Override
    public Block deathRegion() {
        int deathWidth = this.frameWidth;
        int deathHeight = 1;
        int deathX = 0;
        int deathY = this.frameHeight + 15;
        Rectangle deathRec = new Rectangle(new Point(deathX, deathY), deathWidth, deathHeight);
        return new Block(deathRec, Color.BLACK, null, null);
    }


    /**
     * paddle -- return the paddle of the game.
     * <p>
     *
     * @return rectangle  - the paddle of the game.
     */
    @Override
    public Rectangle paddle() {
        // paddle
        int paddleX = frameWidth / 2 - (paddleWidth / 2);
        int paddleY = frameHeight - (paddleHeight + 7);
        return new Rectangle(new Point(paddleX, paddleY), paddleWidth, paddleHeight);
    }
}
