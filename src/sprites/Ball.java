// ID: 556693885
package sprites;

import animations.GameLevel;
import hittings.CollisionInfo;
import collections.GameEnvironment;
import geometry.Line;
import geometry.Point;
import gameplay.Velocity;

import interfaces.Collidable;
import interfaces.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.Map;


/**
 * In this class we define the Sprites.Ball object.
 */
public class Ball implements Sprite {

    // fields
    private int r;
    private java.awt.Color color;
    private Point center;
    private Velocity v;
    private GameEnvironment gameEnvironment;
    private Collidable movingBlock;


    /**
     * Constructors.
     * <p>
     *
     * @param center - the center of the circle
     * @param r      - the radius of the circle
     * @param color  - the color of the circle
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * Constructors.
     * <p>
     *
     * @param x               - the x center of the circle
     * @param y               - the y center of the circle
     * @param r               - the radius of the circle
     * @param color           - the color of the circle
     * @param gameEnvironment - the game environment of the circle
     */
    public Ball(int x, int y, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * setMovingBlock -- sets the moving block of the game.
     * <p>
     *
     * @param paddle - the movingBlock (the paddle for now)
     */
    public void setMovingBlock(Collidable paddle) {
        this.movingBlock = paddle;
    }

    /**
     * setVelocity -- sets the velocity from v.
     * <p>
     *
     * @param velocity - the velocity
     */
    public void setVelocity(Velocity velocity) {
        this.v = velocity;
    }

    /**
     * setVelocity -- sets the velocity from dx dy.
     * <p>
     *
     * @param dx - the x of the center of the circle
     * @param dy - the y of the center of the circle
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * getVelocity -- gets the velocity of a ball.
     * <p>
     *
     * @return GameInfos.Velocity - the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.v;

    }

    /**
     * outHeight -- checks if the ball if going out of the frame.
     * <p>
     *
     * @param maxH - the maximum Height of the frame
     * @param minH - the minim Height of the frame
     * @return boolean - true if its out of the frame
     */
    public boolean outHeight(int maxH, int minH) {
        return center.getY() + this.r > maxH || center.getY() - this.r < minH;
    }

    /**
     * outWidth -- checks if the ball if going out of the frame.
     * <p>
     *
     * @param maxW - the maximum Width of the frame
     * @param minW - the minim Width of the frame
     * @return boolean - true if its out of the frame
     */
    public boolean outWidth(int maxW, int minW) {
        return center.getX() + this.r > maxW || center.getX() - this.r < minW;
    }

    /**
     * moveOneStep -- moves the ball one step.
     * <p>
     *
     * @param maxW - the maximum Width of the frame
     * @param maxH - the maximum Height of the frame
     * @param minW - the minimum Width of the frame
     * @param minH - the minimum Height of the frame
     */
    public void moveOneStep(int maxW, int maxH, int minW, int minH) {
        // changing the center of the ball
        this.center = this.getVelocity().applyToPoint(this.center);
        // if the new center point, is out outWidth (but not Height)
        if ((outWidth(maxW, minW)) && !(outHeight(maxH, minH))) {
            // chancing the velocity direction
            this.setVelocity(-this.getVelocity().getX(), this.getVelocity().getY());
            //changing the center again
            this.center = this.getVelocity().applyToPoint(this.center);
        }
        // if the new center point, is out outHeight (but not Width)
        if ((outHeight(maxH, minH) && !(outWidth(maxW, minW)))) {
            this.setVelocity(this.getVelocity().getX(), -this.getVelocity().getY());
            this.center = this.getVelocity().applyToPoint(this.center);
        }
        // if the new center is both outHeight and outWidth
        if ((outWidth(maxW, minW)) && (outWidth(maxH, minH))) {
            this.setVelocity(-this.getVelocity().getX(), -this.getVelocity().getY());
            this.center = this.getVelocity().applyToPoint(this.center);
        }
    }

    /**
     * moveOneStep -- moves the ball one step.
     * <p>
     */
    public void moveOneStep() {
        // special case - the ball is inside the paddle
        Map<Integer, Point> result = movingBlock.getCollisionRectangle().insideRectangle(this.center);
        if (result != null) {
            // getting a pair information of:
            // A point: the new point of the center outside the paddle
            // An Integer: 1 if it's on the right, -1 if on the left
            for (Integer direction : result.keySet()) {
                // setting the new center, and the new velocity, based on the result
                this.center = result.get(direction);
                this.setVelocity(Math.abs(this.getVelocity().getX()) * direction, -Math.abs(this.getVelocity().getY()));
            }
            // normal case - the ball is not inside the paddle
        } else {
            // getting the line trajectory
            Line trajectory = new Line(this.center, this.getVelocity().applyToPoint(this.center));
            // getting the collision info
            CollisionInfo info = this.gameEnvironment.getClosestCollision(trajectory);
            int count = 0;
            // while this info is not null
            while (info != null) {
                // changing the velocity of the ball
                // notify the object it has been hit
                this.setVelocity(info.collisionObject().hit(this, info.collisionPoint(), this.getVelocity()));
                // getting the update trajectory and info (while they're not null!)
                trajectory = new Line(this.center, this.getVelocity().applyToPoint(this.center));
                info = this.gameEnvironment.getClosestCollision(trajectory);
                // if the count is 3, it means we are into an infinite loop!
                // oppose the velocity and break
                if (count == 3) {
                    this.setVelocity(-this.getVelocity().getX(), -this.getVelocity().getY());
                    break;
                }
                count++;
            }
            this.center = this.getVelocity().applyToPoint(this.center);
        }
    }

    /**
     * drawOn -- draws a circle on the surface.
     * <p>
     *
     * @param surface -- the surface t draw the circle on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.fillCircle(this.getX() + 1, this.getY() + 1, this.r);
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.r);
        surface.setColor(Color.black);
        surface.drawCircle(this.getX(), this.getY(), this.r);
    }

    /**
     * addToGame --notify that the time has passed.
     * <p>
     *
     * @param g - the game in which to add
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * timePassed --notify that the time has passed.
     * <p>
     */
    public void timePassed() {
        this.moveOneStep();
    }


    /**
     * Accessors -- return the x of the center of the circle.
     * <p>
     *
     * @return Int -- the x of the center of the circle
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * Accessors -- return the y of the center of the circle.
     * <p>
     *
     * @return Int -- the y of the center of the circle
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * Accessors -- return the size of a circle.
     * <p>
     *
     * @return Int -- the radius of the circle
     */
    public int getSize() {
        return this.r;
    }

    /**
     * Accessors -- return the color of the circle.
     * <p>
     *
     * @return java.awt.Color -- the color of the circle
     */
    public java.awt.Color getColor() {
        return this.color;
    }


    /**
     * removeFromGame -- removes a sprite from a game.
     * <p>
     *
     * @param gameLevel - the game from which to remove
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }

}