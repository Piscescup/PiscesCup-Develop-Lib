package io.github.piscescup.mc.fabric.utils.counter;

import org.jetbrains.annotations.NotNull;

import java.io.Serial;
import java.util.concurrent.atomic.AtomicLong;

/**
 * A thread-safe, mutable long counter that extends {@link Number}.
 * This class provides atomic operations for incrementing, decrementing, and other
 * arithmetic modifications, making it suitable for use in multi-threaded environments.
 * It also supports being reset to its initial value.
 *
 * @author REN YuanTong
 * @Date 2025-07-16
 */
public class AtomicCounter
    extends Number
    implements Comparable<AtomicCounter>, Counter
{
    @Serial
    private static final long serialVersionUID = 112445645L; // Changed for semantic difference

    private final AtomicLong value;
    private final long defaultValue;

    /**
     * Constructs a new counter with an initial and default value of zero.
     */
    private AtomicCounter() {
        this(0);
    }

    /**
     * Constructs a new counter with a specified initial and default value.
     *
     * @param initialValue the value to start with, which also serves as the reset value.
     */
    private AtomicCounter(final long initialValue) {
        this.value = new AtomicLong(initialValue);
        this.defaultValue = initialValue;
    }

    // --- Factory Methods ---
    /**
     * Creates a new AtomicCounter starting at zero.
     * @return a new counter instance.
     */
    public static AtomicCounter create() {
        return new AtomicCounter();
    }

    /**
     * Creates a new AtomicCounter with a specified initial value.
     * @param initialValue the initial value.
     * @return a new counter instance.
     */
    public static AtomicCounter createFor(final long initialValue) {
        return new AtomicCounter(initialValue);
    }

    /**
     * Creates a new AtomicCounter from a Number object.
     * @param initialValue the initial value, must not be null.
     * @return a new counter instance.
     */
    public static AtomicCounter createFor(@NotNull final Number initialValue) {
        return new AtomicCounter(initialValue.longValue());
    }

    /**
     * Creates a new AtomicCounter from a String representation of a number.
     * @param initialValue the string to be converted to a long.
     * @return a new counter instance.
     * @throws NumberFormatException if the string cannot be parsed as a long.
     */
    public static AtomicCounter createFor(@NotNull final String initialValue) {
        return new AtomicCounter(Long.parseLong(initialValue));
    }


    // --- Core Atomic Operations ---
    /**
     * Atomically adds the given value to the current value.
     * @param delta the value to add.
     * @return the updated value.
     */
    public long addAndGet(long delta) {
        return this.value.addAndGet(delta);
    }

    @Override
    public long getValue() {
        return this.value.longValue();
    }

    @Override
    public long incrementAndGet() {
        return this.addAndGet(1);
    }

    @Override
    public long decrementAndGet() {
        return this.addAndGet(-1);
    }

    /**
     * Atomically subtracts the given value from the current value.
     * This implementation is robust against integer overflow issues (e.g., Long.MIN_VALUE).
     * @param delta the value to subtract.
     * @return the updated value.
     */
    public long subtractAndGet(long delta) {
        long current, next;
        do {
            current = this.value.get();
            // Use Math.subtractExact to be safe, or just `current - delta` if you trust the input
            try {
                next = Math.subtractExact(current, delta);
            } catch (ArithmeticException e) {
                // Or handle the overflow case as needed
                throw new IllegalStateException("Long overflow during subtraction.", e);
            }
        } while (!this.value.compareAndSet(current, next));
        return next;
    }

    /**
     * Gets the current value.
     * @return the current value.
     */
    public long get() {
        return this.value.get();
    }

    /**
     * Sets the value to a new value.
     * @param newValue the new value.
     */
    public void set(long newValue) {
        this.value.set(newValue);
    }

    /**
     * Resets the counter to its initial value. This operation is thread-safe.
     */
    public void reset() {
        this.value.set(this.defaultValue);
    }

    // --- Implementation of Number ---

    @Override
    public int intValue() {
        return (int) get();
    }

    @Override
    public long longValue() {
        return get();
    }

    @Override
    public float floatValue() {
        return (float) get();
    }

    @Override
    public double doubleValue() {
        return (double) get();
    }

    @Override
    public void increment() {
        this.value.incrementAndGet();
    }

    @Override
    public void decrement() {
        this.value.decrementAndGet();
    }

    // --- Implementation of Comparable ---

    @Override
    public int compareTo(@NotNull AtomicCounter other) {
        return Long.compare(this.get(), other.get());
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}