// ID: 556693885
package interfaces;

import biuoop.DrawSurface;
import animations.GameLevel;

/**
 * In this Interface we define the Interfaces.Sprite fucntions.
 */
public interface Sprite {
    /**
     * drawOn -- draws the sprite on the surface.
     * <p>
     *
     * @param d - the surface to draw on.
     */
    void drawOn(DrawSurface d);

    /**
     * timePassed -- notify the sprite that time has passed.
     * <p>
     */
    void timePassed();

    /**
     * addToGame --notify that the time has passed.
     * <p>
     *
     * @param g - the game in which to add
     */
    void addToGame(GameLevel g);
}