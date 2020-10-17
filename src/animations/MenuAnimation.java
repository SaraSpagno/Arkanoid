//ID : 556693885
package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Menu;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * In  class we define the MenuAnimation object.
 *
 * @param <T> - the object using the menu.
 */
public class MenuAnimation<T> implements Menu {
    private Map<String, String> messages = new HashMap<>();
    private Map<String, Object> map = new HashMap<>();
    private boolean stop = false;
    private KeyboardSensor sensor;
    private Object status;


    /**
     * Constructor.
     * <p>
     *
     * @param sensor - the keyboard sensor.
     */
    public MenuAnimation(KeyboardSensor sensor) {
        this.sensor = sensor;
    }


    /**
     * addSelection -- adds a selection to a menu.
     * <p>
     *
     * @param key       - the key to press.
     * @param message   - the message to display.
     * @param returnVal - the action to do.
     */
    @Override
    public void addSelection(String key, String message, Object returnVal) {
        this.messages.put(key, message);
        this.map.put(key, returnVal);
    }

    /**
     * getStatus -- return a status.
     * <p>
     *
     * @return Obejct - the action to do.
     */
    @Override
    public Object getStatus() {
        return this.status;
    }

    /**
     * doOneFrame -- what the animation does in one frame of the game.
     * <p>
     *
     * @param d - the surface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.displayBackground(d);
        for (String k : map.keySet()) {
            if (this.sensor.isPressed(k)) {
                this.status = map.get(k);
                this.stop = true;
            }
        }
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
     * displayBackground - draw the menu.
     * <p>
     *
     * @param d - the surface to draw on.
     */
    public void displayBackground(DrawSurface d) {
        // black background
        d.setColor(Color.black);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());


        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("arkanoid.jpg");
        try {
            Image image = ImageIO.read(is);
            d.drawImage(50, -10, image);
        } catch (IOException e) {
            e.printStackTrace();
        }


        int y = 250;
        int space = (d.getHeight() - y) / map.size();
        for (String s : messages.keySet()) {
            /*d.setColor(Color.pink.darker());
            //d.fillRectangle(30, y + 5, 800, 4);
            d.drawOval(30, y-40, 700, 70);
            d.drawOval(29, y-40, 701, 71);
            d.drawOval(28, y-40, 702, 72);

            //d.fillCircle(10, y - 10, 5);
            d.setColor(Color.blue.darker());
            d.drawText(125+2, y+5+ 2, "Press the key \"" + s + "\" for: " + messages.get(s), 30);
            d.setColor(Color.white);
            d.drawText(125, y+5, "Press the key \"" + s + "\" for: " + messages.get(s), 30);
             */

            /*space = 4;
            int width = 600;
            int x = (d.getWidth()-2*10 - width)/2;
            int currentY = y-40;
            int height = 80;

            // blocks
            d.setColor(Color.pink);
            d.fillRectangle(x,  currentY,  width,  height);

            // shadow:
            d.setColor(Color.pink.darker().darker());
            d.fillRectangle( x + width- space,
                     currentY + space, space,  height - space);
            d.fillRectangle( (x) + space,
                    (currentY + height - space),  width - space, space);
            d.setColor(Color.white);
            d.fillRectangle(x,  currentY, space,  height);
            d.fillRectangle(x,  currentY,  width, space);


            d.drawText(125, y+5, "Press the key \"" + s + "\" for: " + messages.get(s), 30);



            // frame
            d.setColor(Color.BLACK);
            d.drawRectangle(30,  y-40,  700,  80);

            y += (d.getHeight() - 250) / map.size();

             */

            // image
            is = ClassLoader.getSystemClassLoader().getResourceAsStream("button.jpg");
            try {
                Image image = ImageIO.read(is);
                d.drawImage(85, y - 40, image);
            } catch (IOException e) {
                e.printStackTrace();
            }
            d.drawText(155, y, "Press the key \"" + s + "\" for: " + messages.get(s), 26);
            y += (d.getHeight() - 250) / map.size();
        }


        d.setColor(Color.white);
        d.drawText(680, 580, "from spagnos", 15);
    }
}
