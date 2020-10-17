//ID:556693885
package keypress;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Animation;


/**
 * In this Interface we define the KeyPressStoppableAnimation functions.
 */
public class KeyPressStoppableAnimation  implements Animation {

    // fields
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed = true;

    /**
     * Constructor.
     * <p>
     *
     * @param sensor    - the keyboard sensor.
     * @param key       - the key that stops the animation.
     * @param animation - the animation to run.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
    }

    /**
     * doOneFrame -- what the animation does in one frame of the game.
     * <p>
     *
     * @param d - the surface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (this.sensor.isPressed(key) && !isAlreadyPressed) {
            this.stop = true;
            animation.next();
        }
        if (!(this.sensor.isPressed(key))) {
            isAlreadyPressed = false;
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
     * next -- runs the next animation.
     * <p>
     */
    @Override
    public void next() {
    }
}
