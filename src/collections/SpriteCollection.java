// ID: 556603885
package collections;

import interfaces.Sprite;


import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * In this class we define the Collections.SpriteCollection object.
 */
public class SpriteCollection {
    // fields
    private List<Sprite> sprites = new ArrayList<>();

    /**
     * addSprite -- adds the sprite to the list.
     * <p>
     *
     * @param s - the sprite to add.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * notifyAllTimePassed -- notify all the sprites
     * that the time has passed.
     * <p>
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesCopy = new ArrayList<>(this.sprites);
        // Notify all listeners about a hit event:
        for (Sprite s : spritesCopy) {
            s.timePassed();
        }
    }


    /**
     * drawAllOn -- draws all the sprites
     * that the time has passed.
     * <p>
     *
     * @param d - the surface to draw on
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }


    /**
     * removeSprite -- removes the sprite from the list.
     * <p>
     *
     * @param s - the sprite to remove.
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }
}