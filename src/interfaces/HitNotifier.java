// ID: 556693885
package interfaces;

/**
 * In this interface we define the hitNotifier functions.
 */
public interface HitNotifier {

    /**
     * addHitListener - adds a hitListener to the list of listeners.
     * <p>
     *
     * @param hl - the hitlistenet to being added
     */
    void addHitListener(HitListener hl);

    /**
     * removeHitListener - removes a hitListener to the list of listeners.
     * <p>
     *
     * @param hl - the hitlistenet to being removed
     */
    void removeHitListener(HitListener hl);
}