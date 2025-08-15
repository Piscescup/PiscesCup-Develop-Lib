package io.github.piscescup.mc.fabric.register.sound;

import net.minecraft.util.Identifier;

/**
 * <p>
 * Apply a sound event {@code Identifier} to a special repository by the method {@link #apply(Identifier)}.
 * </p>
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #apply(Identifier)}.
 *
 *
 * @author REN YuanTong
 * @Date 2025-07-19
 * @since 1.1.2
 * @see SoundEventRepository
 */
@FunctionalInterface
public interface SoundEventRepositoryElement {
    /**
     * Applies a transformation to the given {@link Identifier}.
     *
     * @param id The original, base identifier to be transformed.
     * @return A new, transformed {@link Identifier} that conforms to the rule
     *         defined by this element.
     */
    Identifier apply(Identifier id);
}