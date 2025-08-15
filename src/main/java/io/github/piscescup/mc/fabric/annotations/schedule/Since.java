package io.github.piscescup.mc.fabric.annotations.schedule;

import io.github.piscescup.mc.fabric.References;

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
 * An annotation used to document the version and/or date in which a program
 * element was introduced.
 *
 * <p>This provides a clear history of the API's evolution, helping developers
 * understand feature availability across different versions of a library or module.
 * It is intended purely for documentation purposes and has no effect on runtime
 * behavior.
 *
 * <p><b>Usage:</b>
 * Developers should specify the version via {@link #value()} and can optionally
 * provide the introduction date via {@link #date()}.
 *
 * <p><b>Example Usage:</b>
 * <pre>{@code
 *   // A new method introduced in version 1.2.0
 *   @Since("1.2.0")
 *   public void newFeatureMethod() { ... }
 *
 *   // A class added in version 2.1.0 on a specific date
 *   @Since(value = "2.1.0", date = "2025-10-15")
 *   public class AdvancedFeature { ... }
 * }</pre>
 *
 * @author REN YuanTong
 * @Date 2025-07-02
 * @since 1.1.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(
    value={CONSTRUCTOR, FIELD, LOCAL_VARIABLE, METHOD, PACKAGE, MODULE, PARAMETER, TYPE}
)
public @interface Since {
    /**
     * An optional string specifying the date when the annotated element was introduced.
     * <p>
     * The recommended format is ISO 8601 (e.g., "2025-07-02").
     *
     * @return The introduction date as a string, or an empty string if not provided.
     */
    String date() default "";

    /**
     * The version in which the annotated element was introduced.
     * <p>
     * The value should be a string that represents the version number,
     * for example, "1.1.0" or "2.0-beta".
     * <p>
     * If not specified, it defaults to {@code References.ORIGIN_MOD_VERSION},
     * which typically represents the initial version of the module.
     *
     * @return The version string.
     */
    String value() default References.ORIGIN_MOD_VERSION;
}