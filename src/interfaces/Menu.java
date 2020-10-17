//ID:556693885
package interfaces;


/**
 * In this class we define the Menu Interface.
 * @param <T> - the object of the menu.
 */
public interface Menu<T> extends Animation {

    /**
     * addSelection -- adds a selection to a menu.
     * <p>
     *
     * @param key       - the key to press.
     * @param message   - the message to display.
     * @param returnVal - the action to do.
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * getStatus -- return a status.
     * <p>
     *
     * @return Obejct - the action to do.
     */
    T getStatus();
}