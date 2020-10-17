//ID : 556693885
package sprites;

import animations.GameLevel;
import geometry.Rectangle;
import hittings.Counter;
import interfaces.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * In this class we define the Sprites.ScoreIndicator object.
 */
public class ScoreIndicator implements Sprite {
    //fields
    private Rectangle rec;
    private java.awt.Color color;
    private Counter score;
    private String name;

    /**
     * Constructors.
     * <p>
     *
     * @param rec   - the rec of the scoreIndicator
     * @param color - the color of the scoreIndicator
     * @param score - the current score
     * @param name - the name of the level
     */
    public ScoreIndicator(Rectangle rec, Color color, Counter score, String name) {
        this.rec = rec;
        this.color = color;
        this.score = score;
        this.name = name;
    }


    @Override
    /**
     * drawOn -- draws the sprite on the surface.
     * <p>
     *
     * @param d - the surface to draw on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY(),
                (int) this.rec.getWidth(), (int) this.rec.getHeight());
        d.fillRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY(),
                (int) this.rec.getWidth(), (int) this.rec.getHeight());
        d.setColor(Color.BLACK);
        int centerX = (int) (this.rec.getWidth() / 2 - 30);
        int centerY = (int) (this.rec.getHeight() / 2 + 4);
        d.drawText(centerX, centerY, "Score: " + score.getValue(), 12);
        d.drawText((int) this.rec.getWidth() - 200, centerY, "Level Name: " + this.name, 12);

    }

    @Override
    /**
     * timePassed -- notify the sprite that time has passed.
     * <p>
     */
    public void timePassed() {
    }


    @Override
    /**
     * addToGame --notify that the time has passed.
     * <p>
     *
     * @param g - the game in which to add
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
