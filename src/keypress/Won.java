//ID:556693885
package keypress;

import biuoop.DrawSurface;
import hittings.Counter;
import interfaces.Animation;
import tasks.ShowHiScoresTask;

import java.awt.Color;

/**
 * In this Interface we define the Won functions.
 */
public class Won implements Animation {

    private Counter score;
    private boolean stop;
    private ShowHiScoresTask showHiScoresTask;


    /**
     * Constructor.
     * <p>
     *
     * @param score - the score to display.
     * @param showHiScoresTask - the showHiScoresTask to run.
     */
    public Won(Counter score, ShowHiScoresTask showHiScoresTask) {
        this.score = score;
        this.showHiScoresTask = showHiScoresTask;
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
        d.drawText(160 + 2, (d.getHeight() / 5) * 2 + 2, "You Win! Your score is " + score.getValue(), 32);
        d.setColor(Color.RED);
        d.drawText(160, (d.getHeight() / 5) * 2, "You Win! Your score is " + score.getValue(), 32);
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
        showHiScoresTask.run();
    }
}