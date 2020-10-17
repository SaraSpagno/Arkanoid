// ID: 556693885
package hittings;

/**
 * In this class we define the counter class.
 */
public class Counter {
    // field
    private int count;

    /**
     * Constructors.
     * <p>
     *
     * @param count - the count of the counter
     */
    public Counter(int count) {
        this.count = count;
    }

    /**
     * Increase - it increases the count.
     * <p>
     *
     * @param number - the number we want to increase of
     */
    public void increase(int number) {
        count += number;
    }

    /**
     * decrease - it decreases the count.
     * <p>
     *
     * @param number - the number we want to decrease of
     */
    public void decrease(int number) {
        count -= number;
    }

    /**
     * Accessor - return this count.
     * <p>
     * @return int - the value of count
     */
    public int getValue() {
        return this.count;
    }
}