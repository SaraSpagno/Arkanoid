//ID:556693885
package keypress;

import biuoop.DrawSurface;
import interfaces.Animation;

import java.awt.Color;

/**
 * In this Interface we define the KeyPressStoppableAnimation functions.
 */
public class PauseScreen implements Animation {

    // fields
    private boolean stop;

    /**
     * Constructor.
     * <p>
     */
    public PauseScreen() {
    }

    /**
     * doOneFrame -- what the animation does in one frame of the game.
     * <p>
     *
     * @param d - the surface.
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.darkGray);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.black);
        d.drawText(160 + 2, (d.getHeight() / 5) * 2 + 2, "paused -- press space to continue", 32);
        d.setColor(Color.RED);
        d.drawText(160, (d.getHeight() / 5) * 2, "paused -- press space to continue", 32);
    }


    /**
     * shouldStop -- returns when the animation should stop.
     * <p>
     *
     * @return boolean.
     */
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * next -- runs the next animation.
     * <p>
     */
    @Override
    public void next() {

    }
}