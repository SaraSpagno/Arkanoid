//ID : 556693885
package animations;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import collections.SpriteCollection;
import interfaces.Animation;

import java.awt.Color;

/**
 * In this class we define the CountDown object.
 */
public class CountdownAnimation implements Animation {
    // fields
    private double numOfSeconds;
    private int countFrom;
    private int currentCount;
    private SpriteCollection gameScreen;
    private biuoop.Sleeper sleeper = new biuoop.Sleeper();
    private boolean stop;
    private boolean firstTime = true;


    /**
     * Constructor.
     * <p>
     *
     * @param numOfSeconds - the seconds to which run the animation.
     * @param countFrom    - the countdown starting from.
     * @param gameScreen   - the game environment / screne in which to disply the countdown.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.currentCount = countFrom;
        this.countFrom = countFrom;
        this.numOfSeconds = numOfSeconds;
        this.gameScreen = gameScreen;
    }


    /**
     * doOneFrame -- what the animation does in one frame of the game.
     * <p>
     *
     * @param d - the surface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        if (firstTime) {
            firstTime = false;
            currentCount++;
        } else if (currentCount != 0) {
            d.setColor(Color.red.darker().darker());
            int font = 120;
            d.drawText(d.getWidth() / 2 - 35 + 3,
                    d.getHeight() / 2 + font / 2 + 3 + 43, String.valueOf(currentCount), font);
            d.setColor(Color.RED.brighter());
            d.drawText(d.getWidth() / 2 - 35, d.getHeight() / 2 + font / 2 + 43, String.valueOf(currentCount), font);
        }
        this.currentCount--;
        if (currentCount == -1) {
            this.stop = true;
        }
        sleeper.sleepFor((int) ((numOfSeconds / countFrom) * 1000));
    }

    /**
     * shouldStop -- returns when the animation should stop.
     * <p>
     *
     * @return boolean.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * next -- goes to the next animation needed.
     * <p>
     */
    @Override
    public void next() {
    }

    /**
     * Accessor -- returns the sleeper.
     * <p>
     *
     * @return Sleeper - the sleeper.
     */
    private Sleeper getSleeper() {
        return sleeper;
    }
}
