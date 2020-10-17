//ID : 556693885
package animations;

import biuoop.DrawSurface;
import gameplay.GameFlow;
import interfaces.Animation;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * In this class we define the HighSchoreAnimation object.
 */
public class HighScoresAnimation implements Animation {
    private String message;
    private GameFlow gameFlow;

    /**
     * Constructor.
     * <p>
     *
     * @param gameFlow - the gameFlow in which we play.
     */
    public HighScoresAnimation(GameFlow gameFlow) {
        this.gameFlow = gameFlow;
        File f = new File("highscore.txt");
        if (f.exists()) {
            BufferedReader is = null;
            try {
                is = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("highscore.txt")));
                String line;
                while ((line = is.readLine()) != null) {
                    this.message = "The HighScore is " + line.substring(line.lastIndexOf(" ") + 1);
                }
            } catch (IOException e) {
                System.out.println("Something went wrong while reading!");
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        System.out.println(" Failed closing the file !");
                    }
                }
            }
        } else {
            this.message = "Sorry, you must play at least once before";
        }
    }

    /**
     * doOneFrame -- what the animation does in one frame of the game.
     * <p>
     *
     * @param d - the surface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.darkGray);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.black);
        d.drawText(80 + 2, (d.getHeight() / 5) * 2 + 2, message, 32);
        d.setColor(Color.RED);
        d.drawText(80, (d.getHeight() / 5) * 2, message, 32);
    }

    /**
     * shouldStop -- returns when the animation should stop.
     * <p>
     *
     * @return boolean.
     */
    @Override
    public boolean shouldStop() {
        return false;
    }

    /**
     * next -- goes to the next animation needed.
     * <p>
     */
    @Override
    public void next() {
        gameFlow.runMenu();
    }
}
