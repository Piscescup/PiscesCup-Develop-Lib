package io.github.piscescup.mc.fabric.interfaces.exfunctions;

/**
 * Represents an operation upon three {@code double}-valued operands and producing a
 * {@code double}-valued result. This is the primitive type specialization of
 * {@link TernaryOperator} for {@code double}.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #applyAsDouble(double, double, double)}.
 *
 * @author REN YuanTong
 * @Date 2025-07-24
 * @since 1.1.2
 */
@FunctionalInterface
public interface DoubleTernaryOperator {

    /**
     * Applies this operator to the given operands.
     *
     * @param left   the first operand
     * @param middle the second operand
     * @param right  the third operand
     * @return the operator result
     */
    double applyAsDouble(double left, double middle, double right);
}