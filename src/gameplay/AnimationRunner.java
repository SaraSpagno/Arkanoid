// ID: 556693885
package gameplay;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import interfaces.Animation;

/**
 * In this class we define the AnimationRunner object.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private biuoop.Sleeper sleeper = new biuoop.Sleeper();

    /**
     * Constructor.
     * <p>
     *
     * @param gui             - the gui.
     * @param framesPerSecond - the framesPerSecond.
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
    }


    /**
     * run.
     * <p>
     *
     * @param animation - the animation to run.
     * @param firstTime - if firstTime is true, the animations sleeps.
     *                  for a few seconds at the beginning to give it time to draw.
     */
    public void run(Animation animation, boolean firstTime) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);

            if (firstTime) {
                sleeper.sleepFor(1000);
                firstTime = false;
            }

            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * Accessor.
     * <p>
     *
     * @return Sleeper.
     */
    public Sleeper getSleeper() {
        return sleeper;
    }
}