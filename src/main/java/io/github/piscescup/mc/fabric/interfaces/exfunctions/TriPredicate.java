package io.github.piscescup.mc.fabric.interfaces.exfunctions;

import java.util.Objects;

/**
 * Represents a predicate (boolean-valued function) of three arguments. This is
 * the three-arity specialization of {@link java.util.function.Predicate}.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #test(Object, Object, Object)}.
 *
 * @param <X> the type of the first argument to the predicate
 * @param <Y> the type of the second argument to the predicate
 * @param <Z> the type of the third argument to the predicate
 *
 * @see java.util.function.Predicate
 * @see java.util.function.BiPredicate
 * @since 1.1.0
 * @author REN YuanTong
 */
@FunctionalInterface
public interface TriPredicate<X, Y, Z> {

    /**
     * Evaluates this predicate on the given arguments.
     *
     * @param x the first input argument
     * @param y the second input argument
     * @param z the third input argument
     * @return {@code true} if the input arguments match the predicate,
     *         otherwise {@code false}
     */
    boolean test(X x, Y y, Z z);

    /**
     * Returns a composed predicate that represents a short-circuiting logical
     * AND of this predicate and another. When evaluating the composed
     * predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     *
     * <p>Any exceptions thrown during evaluation of either predicate are relayed
     * to the caller; if evaluation of this predicate throws an exception, the
     * {@code other} predicate will not be evaluated.
     *
     * @param other a predicate that will be logically-ANDed with this predicate
     * @return a composed predicate that represents the short-circuiting logical
     *         AND of this predicate and the {@code other} predicate
     * @throws NullPointerException if {@code other} is null
     */
    default TriPredicate<X, Y, Z> and(TriPredicate<? super X, ? super Y, ? super Z> other) {
        Objects.requireNonNull(other);
        return (x, y, z) -> test(x, y, z) && other.test(x, y, z);
    }

    /**
     * Returns a predicate that represents the logical negation of this
     * predicate.
     *
     * @return a predicate that represents the logical negation of this predicate
     */
    default TriPredicate<X, Y, Z> negate() {
        return (x, y, z) -> !test(x, y, z);
    }

    /**
     * Returns a composed predicate that represents a short-circuiting logical
     * OR of this predicate and another. When evaluating the composed
     * predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     *
     * <p>Any exceptions thrown during evaluation of either predicate are relayed
     * to the caller; if evaluation of this predicate throws an exception, the
     * {@code other} predicate will not be evaluated.
     *
     * @param other a predicate that will be logically-ORed with this predicate
     * @return a composed predicate that represents the short-circuiting logical
     *         OR of this predicate and the {@code other} predicate
     * @throws NullPointerException if {@code other} is null
     */
    default TriPredicate<X, Y, Z> or(TriPredicate<? super X, ? super Y, ? super Z> other) {
        Objects.requireNonNull(other);
        return (x, y, z) -> test(x, y, z) || other.test(x, y, z);
    }
}