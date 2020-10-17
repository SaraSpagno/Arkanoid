// ID: 556693885
package sprites;

import animations.GameLevel;
import gameplay.Velocity;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Collidable;
import interfaces.Sprite;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * In this class we define the Sprites.Paddle object.
 */
public class Paddle implements Sprite, Collidable {
    // fields
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rec;
    private Point upperLeft;
    private double width;
    private double height;
    private Color color;
    private Velocity v;
    private Color edges = Color.red.darker();


    /**
     * Constructors.
     * <p>
     *
     * @param rec      - the rec of the paddle
     * @param color    - the color of the paddle
     * @param keyboard - the keyboard sensor of the paddle
     */
    public Paddle(Rectangle rec, Color color, KeyboardSensor keyboard) {
        this.rec = rec;
        this.upperLeft = rec.getUpperLeft();
        this.width = rec.getWidth();
        this.height = rec.getHeight();
        this.color = color;
        this.keyboard = keyboard;
    }

    /**
     * setVelocity -- sets the velocity from dx,dy.
     * <p>
     *
     * @param dxPaddle - the dx of velocity
     * @param dyPaddle - the dy of velocity
     */
    public void setVelocity(double dxPaddle, double dyPaddle) {
        this.v = new Velocity(dxPaddle, dyPaddle);
    }

    /**
     * moveLeft -- moves the Sprites.Paddle to the left.
     * <p>
     */
    public void moveLeft() {
        // checking the left border for the Sprites.Paddle
        int leftBlockWidth = 7;
        int leftBorder = leftBlockWidth;
        // if the paddle is in the correct border range
        if (this.v.applyToPoint(this.upperLeft).getX() >= leftBorder) {
            this.setVelocity(-Math.abs(this.v.getX()), this.v.getY());
            this.upperLeft = this.v.applyToPoint(this.upperLeft);
            this.rec = new Rectangle(this.upperLeft, this.width, this.height);
        }
    }

    /**
     * moveRight -- moves the Sprites.Paddle to the right.
     * <p>
     */
    public void moveRight() {
        // checking the left border for the Sprites.Paddle
        int rightBlockWidth = 7;
        int frameWidth = 800;
        // if the paddle in on the correct border frame
        int rightBorder = frameWidth - rightBlockWidth;
        if (this.v.applyToPoint(this.upperLeft).getX() + this.width <= rightBorder) {
            this.setVelocity(Math.abs(this.v.getX()), this.v.getY());
            this.upperLeft = this.v.applyToPoint(this.upperLeft);
            this.rec = new Rectangle(this.upperLeft, this.width, this.height);
        }
    }

    /**
     * timePassed -- notify the Sprites.Paddle that the time passed.
     * <p>
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * drawOn -- draws a Sprites.Paddle on the surface.
     * <p>
     *
     * @param d - the surface to draw on.
     */
    public void drawOn(DrawSurface d) {
        int distance = (int) height / 2;
        int paddleX = (int) this.upperLeft.getX() + distance;
        int paddleY = (int) this.upperLeft.getY();
        int paddleWidth = (int) this.width - 2 * distance;
        int paddleHeight = (int) this.height;

        // circles
        d.setColor(this.edges);
        d.fillCircle(paddleX, paddleY + (paddleHeight / 2), distance);
        d.fillCircle(paddleX + paddleWidth, paddleY + (paddleHeight / 2), distance);
        // frames
        d.setColor(Color.BLACK);
        d.drawCircle(paddleX, paddleY + (paddleHeight / 2), distance);
        d.drawCircle(paddleX + paddleWidth, paddleY + (paddleHeight / 2), distance);


        // paddle
        d.setColor(this.color);
        d.fillRectangle(paddleX, paddleY, paddleWidth, paddleHeight);

        // shadow:
        int space = 2;
        d.setColor(this.color.darker().darker());
        /*d.fillRectangle(paddleX+paddleWidth-space,paddleY+space, space, paddleHeight-space);*/
        d.fillRectangle(paddleX + space, paddleY + paddleHeight - space, paddleWidth - 2 * space, space);
        d.setColor(Color.white);
        /*d.fillRectangle(paddleX, paddleY, space,paddleHeight);*/
        d.fillRectangle(paddleX + space, paddleY, paddleWidth - 2 * space, space);


        d.setColor(Color.black);
        d.drawRectangle(paddleX, paddleY, paddleWidth, paddleHeight);

        this.color = Color.orange;
    }

    /**
     * Accessors -- return the rec of the Sprites.Paddle.
     * <p>
     *
     * @return Shapes.Rectangle - the rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rec;
    }

    /**
     * hit -- notify the object that it had been hit.
     * <p>
     *
     * @param collisionPoint  - the point of the collision.
     * @param currentVelocity - the velocity of the collision.
     * @param hitter          - the ball hitting the paddle.
     * @return GameInfos.Velocity - the new velocity;
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // creating a nano double for accuracy
        double nanoDouble = 0.001;
        // getting the range in which the ball has hit on the Sprites.Paddle
        int range = (int) ((collisionPoint.getX() - rec.getUpperLeft().getX()) / (rec.getWidth() / 5));
        // getting the speed at which the ball was going
        double speed = Math.sqrt(Math.pow(currentVelocity.getX(), 2) + Math.pow(currentVelocity.getY(), 2));

        // changing the velocity base on the range (for the horizontal)
        if ((Math.abs(collisionPoint.getY() - rec.getUpperLeft().getY()) < nanoDouble)
                || (Math.abs(collisionPoint.getY()
                - (rec.getUpperLeft().getY() + rec.getHeight())) < nanoDouble)) {
            this.color = Color.darkGray;
            switch (range) {
                case 0:
                    currentVelocity = Velocity.fromAngleAndSpeed(300, speed);
                    break;
                case 1:
                    currentVelocity = Velocity.fromAngleAndSpeed(330, speed);
                    break;
                case 2:
                    currentVelocity = new Velocity(currentVelocity.getX(), -currentVelocity.getY());
                    break;
                case 3:
                    currentVelocity = Velocity.fromAngleAndSpeed(30, speed);
                    break;
                case 4:
                    currentVelocity = Velocity.fromAngleAndSpeed(60, speed);
                    break;
                default:
                    // do nothing
            }
        }
        // changing the velocity (for the vertical)
        if (Math.abs(collisionPoint.getX() - rec.getUpperLeft().getX()) < 0.001
                || Math.abs(collisionPoint.getX() - (rec.getUpperLeft().getX() + rec.getWidth())) < 0.001) {
            currentVelocity = new Velocity(-currentVelocity.getX(), currentVelocity.getY());
        }
        return currentVelocity;


    }

    /**
     * equals -- checks if two paddles are equal.
     * <p>
     *
     * @param other - the other collidable.
     * @return Boolean - true if equal false otherwise.
     */
    public Boolean equals(Collidable other) {
        if (this.upperLeft.equals(other.getCollisionRectangle().getUpperLeft())) {
            return this.width == other.getCollisionRectangle().getWidth()
                    && this.height == other.getCollisionRectangle().getHeight();
        }
        return false;
    }

    /**
     * addToGame -- adds the Sprites.Paddle to the game.
     * <p>
     *
     * @param g - the  game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

}