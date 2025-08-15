package io.github.piscescup.mc.fabric.utils.counter;

import org.jetbrains.annotations.NotNull;

/**
 * <h2>Description</h2>
 *
 * <h2>Usages</h2>
 *
 * @author REN YuanTong
 * @Date 2025-07-16
 * @since
 */
public class SimpleCounter
    extends Number
    implements Comparable<SimpleCounter>, Counter
{
    private long value;
    private final long defaultValue;

    private SimpleCounter(final long value) {
        this.value = value;
        this.defaultValue = value;
    }

    public static SimpleCounter create() {
        return new SimpleCounter(0);
    }

    public static SimpleCounter createFor(final Number value) {
        return new SimpleCounter(value.longValue());
    }

    public static SimpleCounter createFor(final String value) {
        return new SimpleCounter(Long.parseLong(value));
    }

    @Override
    public long getValue() {
        return this.value;
    }

    @Override
    public void increment() {
        this.value++;
    }

    @Override
    public void decrement() {
        this.value--;
    }

    @Override
    public void set(long value) {
        this.value = value;
    }

    @Override
    public void reset() {
        this.value = defaultValue;
    }

    @Override
    public long addAndGet(long value) {
        this.value += value;
        return value;
    }

    @Override
    public long subtractAndGet(long value) {
        this.value -= value;
        return value;
    }

    @Override
    public long incrementAndGet() {
        return ++this.value;
    }

    @Override
    public long decrementAndGet() {
        return --this.value;
    }

    @Override
    public int compareTo(@NotNull SimpleCounter other) {
        return Long.compare(this.value, other.value);
    }

    @Override
    public int intValue() {
        return (int) this.value;
    }

    @Override
    public long longValue() {
        return this.value;
    }

    @Override
    public float floatValue() {
        return this.value;
    }

    @Override
    public double doubleValue() {
        return this.value;
    }
}
