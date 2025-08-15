package io.github.piscescup.mc.fabric.interfaces.exfunctions;

import java.util.Objects;
import java.util.function.Function;

/**
 * Represents a function that accepts three arguments and produces a result.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #apply(Object, Object, Object)}W.
 *
 * @param <X> the type of the first argument to the function
 * @param <Y> the type of the second argument to the function
 * @param <Z> the type of the third argument to the function
 * @param <R> the type of the result of the function
 */
@FunctionalInterface
public interface TriFunction<X, Y, Z, R> {

    /**
     * Applies this function to the given arguments.
     *
     * @param x the first function argument
     * @param y the second function argument
     * @param z the third function argument
     * @return the function result
     */
    R apply(X x, Y y, Z z);

    /**
     * Returns a composed function that first applies this function to
     * its input, and then applies the {@code after} function to the result.
     * If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <V>   the type of output of the {@code after} function, and of the
     *              composed function
     * @param after the function to apply after this function is applied
     * @return a composed function that first applies this function and then
     * applies the {@code after} function
     * @throws NullPointerException if after is null
     */
    default <V> TriFunction<X, Y, Z, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (x, y, z) -> after.apply(apply(x, y, z));
    }
}
