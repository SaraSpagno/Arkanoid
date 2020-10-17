//ID: 556693885
package backgrounds;

import biuoop.DrawSurface;
import animations.GameLevel;
import interfaces.Sprite;

import java.awt.Color;
import java.awt.Image;

/**
 * In this Interface we define the background 1 object.
 */
public class StandardBackground implements Sprite {

    private Color color;
    private Image image;

    /**
     * Constructor.
     * <p>
     *
     * @param color - the color of the background.
     * @param image - the image of the background.
     */
    public StandardBackground(Color color, Image image) {
        this.color = color;
        this.image = image;
    }

    /**
     * drawOn -- draws the sprite on the surface.
     * <p>
     *
     * @param d - the surface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        if (color != null) {
            d.setColor(color);
            d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        } else if (image != null) {
            d.drawImage(0, 0, image);
        }
    }


    /**
     * timePassed -- notify the sprite that time has passed.
     * <p>
     */
    @Override
    public void timePassed() {
    }

    /**
     * addToGame --notify that the time has passed.
     * <p>
     *
     * @param g - the game in which to add
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
