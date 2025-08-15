package io.github.piscescup.mc.fabric.annotations.schedule;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.MODULE;
import static java.lang.annotation.ElementType.PACKAGE;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;

/**
 * An annotation indicating that a program element is temporary and is scheduled
 * to be removed in a future version or by a specific date.
 *
 * <p>This annotation provides a more explicit alternative to {@link Deprecated},
 * communicating a clear removal timeline. Developers should migrate away from
 * the annotated element to prevent breaking changes when the target version or
 * date is reached.
 *
 * <p><b>Usage:</b>
 * The {@link #value()} attribute specifies the version in which removal will occur.
 * The optional {@link #date()} attribute can be used to provide a specific
 * calendar date for the removal, adding more precise context.
 *
 * <p><b>Example Usage:</b>
 * <pre>{@code
 *   // This method will be removed in version 3.0.0
 *   @Until("3.0.0")
 *   public void legacyMethod() { ... }
 *
 *   // This class is scheduled for removal in version 4.0, which is targeted
 *   // for release around January 1, 2027.
 *   @Until(value = "4.0.0", date = "2027-01-01")
 *   public class OldApiHandler { ... }
 * }</pre>
 *
 * @see Deprecated
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(
    value={CONSTRUCTOR, FIELD, LOCAL_VARIABLE, METHOD, PACKAGE, MODULE, PARAMETER, TYPE}
)
public @interface Until {
    /**
     * An optional string specifying the calendar date by which the annotated
     * element is expected to be removed.
     * <p>
     * The recommended format is ISO 8601 (e.g., "2026-12-31").
     *
     * @return The removal date as a string, or an empty string if not provided.
     */
    String date() default "";

    /**
     * Specifies the version in which the annotated element is expected
     * to be removed.
     * <p>
     * This is the primary, required field for identifying the removal target.
     *
     * @return A string representing the removal version (e.g., "2.0.0").
     */
    String value();
}