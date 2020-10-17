// ID: 556693885

package animations;

import collections.GameEnvironment;
import collections.SpriteCollection;

import gameplay.AnimationRunner;
import geometry.Point;
import geometry.Rectangle;
import hittings.BallRemover;
import hittings.BlockRemover;
import hittings.Counter;
import hittings.ScoreTrackingListener;
import interfaces.Collidable;
import interfaces.LevelInformation;
import interfaces.Sprite;
import interfaces.Animation;
import keypress.KeyPressStoppableAnimation;
import keypress.PauseScreen;
import sprites.Ball;
import sprites.Block;
import sprites.Paddle;
import sprites.ScoreIndicator;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * In this class we define the Game object.
 */
public class GameLevel implements Animation {
    // fields
    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private int frameWidth = 800;
    private Counter counterBlocks;
    private Counter counterBalls;
    private Counter score;
    private GUI gui;

    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;

    private LevelInformation levelInfo;


    /**
     * Constructor.
     * <p>
     *
     * @param levelInfo - the level info.
     * @param score     - the score.
     * @param gui       - the gui.
     * @param keyboard  - the keyboard sensor.
     * @param runner    - the animation runner.
     */
    public GameLevel(LevelInformation levelInfo, Counter score, GUI gui,
                     KeyboardSensor keyboard, AnimationRunner runner) {
        this.levelInfo = levelInfo;
        this.score = score;
        this.gui = gui;
        this.keyboard = keyboard;
        this.runner = runner;
    }

    /**
     * addCollidable -- adds a collidable to the collection of the game.
     * <p>
     *
     * @param c - the collidable
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * addSprites -- adds a sprite to the collection of the game.
     * <p>
     *
     * @param s - the sprite
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * initialize -- create the Blocks and Sprites.Ball and add them
     * to the game.
     * <p>
     */
    public void initialize() {
        this.levelInfo.getBackground().addToGame(this);

        // blocks at the edges
        for (Block b : levelInfo.borders()) {
            b.addToGame(this);
        }

        // paddle
        Paddle paddle = new Paddle(levelInfo.paddle(), Color.orange, keyboard);
        paddle.setVelocity(levelInfo.paddleVelocity().getX(), levelInfo.paddleVelocity().getY());
        paddle.addToGame(this);

        // balls
        for (int i = 0; i < levelInfo.numberOfBalls(); i++) {
            int ballX = (int) levelInfo.paddle().getUpperLeft().getX() + (levelInfo.paddleWidth() / 2);
            int ballY = (int) levelInfo.paddle().getUpperLeft().getY() - 10;
            Ball ball = new Ball(ballX, ballY, 6, Color.WHITE, this.environment);
            ball.setVelocity(levelInfo.initialBallVelocities().get(i).getX(),
                    levelInfo.initialBallVelocities().get(i).getY());
            ball.addToGame(this);
            ball.setMovingBlock(paddle);
        }


        // death region block
        this.counterBalls = new Counter(levelInfo.numberOfBalls());
        BallRemover ballRemover = new BallRemover(this, counterBalls);
        Block deathRegion = levelInfo.deathRegion();
        deathRegion.addHitListener(ballRemover);
        deathRegion.addToGame(this);


        // blocks
        this.counterBlocks = new Counter(levelInfo.numberOfBlocksToRemove());
        BlockRemover blockRemover = new BlockRemover(this, counterBlocks);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(score);
        for (Block b : levelInfo.blocks()) {
            b.addHitListener(blockRemover);
            b.addHitListener(scoreTrackingListener);
            b.addToGame(this);
        }


        // ScoreIndicator
        int scInWidth = this.frameWidth;
        int scInHeight = 25;
        int scInX = 0;
        int scInY = 0;
        Rectangle scoreIndicatorRec = new Rectangle(new Point(scInX, scInY), scInWidth, scInHeight);
        ScoreIndicator scoreIndicator = new ScoreIndicator(scoreIndicatorRec,
                Color.WHITE, this.score, levelInfo.levelName());
        scoreIndicator.addToGame(this);

    }


    /**
     * playOneTurn -- runs the game animation.
     * <p>
     */
    public void playOneTurn() {
        this.runner.run(new CountdownAnimation(2, 3, this.sprites), true);
        this.running = true;
        this.runner.run(this, false);
        if (counterBlocks.getValue() == 0) {
            score.increase(100);
        }
    }


    /**
     * removeCollidable -- removes a collidable from the game environment.
     * <p>
     *
     * @param c - the collidable to remove
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * removeSprite -- removes a sprite from the game environment.
     * <p>
     *
     * @param s - the sprite to remove
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * getNumBalls -- get The Number of Balls in the game.
     * <p>
     *
     * @return int - the number of balls in the game.
     */
    public int getNumBalls() {
        return this.counterBalls.getValue();
    }

    /**
     * doOneFrame -- what the animation does in one frame of the game.
     * <p>
     *
     * @param d - the surface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        // game-specific logic
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.counterBlocks.getValue() == 0 || this.counterBalls.getValue() == 0) {
            this.running = false;
        }
        if (this.keyboard.isPressed("p")) {
            runner.run(new KeyPressStoppableAnimation(keyboard, "space", new PauseScreen()), false);
        }
    }

    /**
     * shouldStop -- returns when the animation should stop.
     * <p>
     *
     * @return boolean.
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }


    /**
     * next -- goes to the next animation needed.
     * <p>
     */
    @Override
    public void next() {

    }
}