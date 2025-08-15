package io.github.piscescup.mc.fabric.interfaces.exfunctions;

import java.util.Comparator;
import java.util.Objects;


/**
 * Represents an operation upon three operands of the same type, producing a result
 * of the same type as the operands. This is a specialization of
 * {@link TriFunction} for the case where the operands and the result are all of
 * the same type.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #apply(Object, Object, Object)}.
 *
 * @author REN YuanTong
 * @Date 2025-07-24
 * @param <T> the type of the operands and result of the operator
 * @see TriFunction
 * @see java.util.function.BinaryOperator
 * @see java.util.function.UnaryOperator
 * @since 1.1.2
 */
@FunctionalInterface
public interface TernaryOperator<T> extends TriFunction<T, T, T, T> {

    /**
     * Returns a {@link TernaryOperator} which returns the minimum of three elements
     * according to the specified {@code Comparator}.
     *
     * @param <T>        the type of the input arguments of the comparator
     * @param comparator a {@code Comparator} for comparing the three values
     * @return a {@code TernaryOperator} which returns the minimum of its operands,
     *         according to the supplied {@code Comparator}
     * @throws NullPointerException if the comparator is null
     */
    public static <T> TernaryOperator<T> minBy(Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b, c) -> comparator.compare(a, b) <= 0 ?
            (comparator.compare(a, c) <= 0 ? a : c) :
            (comparator.compare(b, c) <= 0 ? b : c);
    }

    /**
     * Returns a {@link TernaryOperator} which returns the maximum of three elements
     * according to the specified {@code Comparator}.
     *
     * @param <T>        the type of the input arguments of the comparator
     * @param comparator a {@code Comparator} for comparing the three values
     * @return a {@code TernaryOperator} which returns the maximum of its operands,
     *         according to the supplied {@code Comparator}
     * @throws NullPointerException if the comparator is null
     */
    public static <T> TernaryOperator<T> maxBy(Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b, c) -> comparator.compare(a, b) >= 0 ?
            (comparator.compare(a, c) >= 0 ? a : c) :
            (comparator.compare(b, c) >= 0 ? b : c);
    }
}
