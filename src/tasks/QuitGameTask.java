//ID:556693885
package tasks;


import biuoop.GUI;
import interfaces.Task;

/**
 * In this class we define the QuitGameTask object.
 */
public class QuitGameTask implements Task<Void> {

    // fields
    private GUI gui;

    /**
     * Constructors.
     * <p>
     *
     * @param gui - the gui.
     */
    public QuitGameTask(GUI gui) {
        this.gui = gui;
    }

    /**
     * run -- runs the task.
     * <p>
     *
     * @return Void.
     */
    public Void run() {
        System.exit(0);
        return null;
    }
}