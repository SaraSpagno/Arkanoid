//ID: 556693885
package tasks;


import gameplay.GameFlow;
import interfaces.Task;

/**
 * In this class we define the PlayGameTask object.
 */
public class PlayGameTask implements Task<Void> {

    // fields
    private GameFlow gameFlow;

    /**
     * Constructors.
     * <p>
     *
     * @param gameFlow - the gameflow.
     */
    public PlayGameTask(GameFlow gameFlow) {
        this.gameFlow = gameFlow;
    }

    /**
     * run -- runs the task.
     * <p>
     *
     * @return Void.
     */
    public Void run() {
        gameFlow.runLevels();
        return null;
    }
}