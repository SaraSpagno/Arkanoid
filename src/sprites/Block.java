package sprites;

import animations.GameLevel;
import gameplay.Velocity;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * In this class we define the block object.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    // fields
    private Point upperLeft;
    private double width;
    private double height;
    private Rectangle rec;
    private java.awt.Color color = null;
    private java.awt.Image image = null;
    private Color stroke = null;

    // Assignment 5
    private List<HitListener> hitListeners = new ArrayList<>();


    /**
     * Constructors.
     * <p>
     *
     * @param rec    - the rec of the block
     * @param color  - the color of the block
     * @param stroke - the stroke of the block
     * @param image  - the image of the block
     */
    public Block(Rectangle rec, Color color, Color stroke, java.awt.Image image) {
        this.rec = rec;
        this.upperLeft = rec.getUpperLeft();
        this.width = rec.getWidth();
        this.height = rec.getHeight();
        this.color = color;
        this.stroke = stroke;
        this.image = image;
    }

    /**
     * drawOn -- draws a block on the surface.
     * <p>
     *
     * @param d - the surface to draw on.
     */
    public void drawOn(DrawSurface d) {

        if (this.color != null) {
            // blocks
            d.setColor(this.color);
            d.fillRectangle((int) this.upperLeft.getX(),
                    (int) this.upperLeft.getY(), (int) this.width, (int) this.height);
        } else if (this.image != null) {
            d.drawImage((int) this.upperLeft.getX(),
                    (int) this.upperLeft.getY(), this.image);

        }

        // the stroke
        if (stroke != null) {
            // frame
            d.setColor(stroke);
            d.drawRectangle((int) this.upperLeft.getX(),
                    (int) this.upperLeft.getY(), (int) this.width, (int) this.height);

        }
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

    @Override
    /**
     * timePassed -- notify the block that the time passed.
     * <p>
     */
    public void timePassed() {

    }


    // Return the "collision shape" of the object.
    @Override
    /**
     * Accessors -- return the rec of the block.
     * <p>
     *
     * @return Shapes.Rectangle - the rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rec;
    }


    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).
    @Override
    /**
     * hit -- notify the object that it had been hit.
     * <p>
     *
     * @param collisionPoint  - the point of the collision.
     * @param currentVelocity - the velocity of the collision.
     * @return GameInfos.Velocity - the new velocity;
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        double x = collisionPoint.getX();
        double y = collisionPoint.getY();
        double recX = rec.getUpperLeft().getX();
        double recY = rec.getUpperLeft().getY();

        // vertically
        if (Math.abs(x - recX) < 0.001 || Math.abs(x - (recX + rec.getWidth())) < 0.001) {
            currentVelocity = new Velocity(-currentVelocity.getX(), currentVelocity.getY());
            this.notifyHit(hitter);
        }
        // horizontally
        if (Math.abs(y - recY) < 0.001 || Math.abs(y - (recY + rec.getHeight())) < 0.001) {
            currentVelocity = new Velocity(currentVelocity.getX(), -currentVelocity.getY());
            this.notifyHit(hitter);
        }

        return currentVelocity;
    }

    @Override
    /**
     * equals -- checks if two blocks are equal.
     * <p>
     *
     * @param other - the other blocks.
     * @return Boolean - true if equal false otherwise.
     */
    public Boolean equals(Collidable other) {
        if (this.upperLeft.getX() == other.getCollisionRectangle().getUpperLeft().getX()) {
            if (this.upperLeft.getY() == other.getCollisionRectangle().getUpperLeft().getY()) {
                return this.width == other.getCollisionRectangle().getWidth()
                        && this.height == other.getCollisionRectangle().getHeight();
            }
        }
        return false;
    }


    /**
     * removeFromGame -- removes a sprite from a game.
     * <p>
     *
     * @param gameLevel - the game from which to remove
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }


    /**
     * notifyHit -- notifies that a hit with the block has happened.
     * <p>
     *
     * @param hitter - the ball hitting the block
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>();
        listeners.addAll(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }


    @Override
    /**
     * addHitListener - adds a hitListener to the list of listeners.
     * <p>
     *
     * @param hl - the hitlistenet to being added
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    /**
     * removeHitListener - removes a hitListener to the list of listeners.
     * <p>
     *
     * @param hl - the hitlistenet to being removed
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

}
