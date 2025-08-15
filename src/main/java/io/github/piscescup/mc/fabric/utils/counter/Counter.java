package io.github.piscescup.mc.fabric.utils.counter;

/**
 * Defines a contract for a numeric counter.
 */
public interface Counter {

    void increment();

    void decrement();

    /**
     * Gets the current value of the counter.
     * @return the current value.
     */
    long getValue();

    /**
     * Sets the counter to a new value.
     * @param newValue the value to set.
     */
    void set(long newValue);

    /**
     * Atomically increments the value by one and returns the new value.
     * @return the updated value.
     */
    long incrementAndGet();

    /**
     * Atomically decrements the value by one and returns the new value.
     * @return the updated value.
     */
    long decrementAndGet();

    /**
     * Atomically adds the given amount and returns the new value.
     * @param delta the amount to add.
     * @return the updated value.
     */
    long addAndGet(long delta);

    /**
     * Atomically subtracts the given amount and returns the new value.
     * @param delta the amount to subtract.
     * @return the updated value.
     */
    long subtractAndGet(long delta);

    /**
     * Resets the counter to its initial value.
     */
    void reset();


    static Counter atomic() {
        return AtomicCounter.create();
    }

    static Counter simple() {
        return SimpleCounter.create();
    }

}