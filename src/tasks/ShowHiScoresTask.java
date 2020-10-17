//ID: 556693885
package tasks;

import biuoop.KeyboardSensor;
import gameplay.AnimationRunner;
import interfaces.Animation;
import interfaces.Task;
import keypress.KeyPressStoppableAnimation;


/**
 * In this class we define the ShowHighScore object.
 */
public class ShowHiScoresTask implements Task<Void> {

    // fields
    private AnimationRunner runner;
    private Animation highScoresAnimation;
    private KeyboardSensor keyboard;

    /**
     * Constructors.
     * <p>
     *
     * @param keyboard            - the keyboard sensor
     * @param runner              - the runner
     * @param highScoresAnimation - the highScoresAnimation
     */
    public ShowHiScoresTask(KeyboardSensor keyboard, AnimationRunner runner, Animation highScoresAnimation) {
        this.runner = runner;
        this.highScoresAnimation = highScoresAnimation;
        this.keyboard = keyboard;
    }

    /**
     * run -- runs the task.
     * <p>
     * @return Void.
     */
    public Void run() {
        this.runner.run(new KeyPressStoppableAnimation(keyboard, "space", this.highScoresAnimation), false);
        return null;
    }

}