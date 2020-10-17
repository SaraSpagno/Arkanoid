//ID: 556693885
package interfaces;

/**
 * In this Interface we define task fucntions.
 *
 * @param <T>.
 */
public interface Task<T> {
    /**
     * run -- runs the task.
     * <p>
     *
     * @return T.
     */
    T run();
}