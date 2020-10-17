// ID: 556693885
package interfaces;

import biuoop.DrawSurface;

/**
 * In this Interface we define the Animation functions.
 */
public interface Animation {
    /**
     * doOneFrame -- what the animation does in one frame of the game.
     * <p>
     *
     * @param d - the surface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * shouldStop -- returns when the animation should stop.
     * <p>
     *
     * @return boolean.
     */
    boolean shouldStop();

    /**
     * next -- goes to the next animation needed.
     * <p>
     */
    void next();
}