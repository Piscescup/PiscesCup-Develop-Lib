package io.github.piscescup.mc.fabric.interfaces;

import io.github.piscescup.mc.fabric.utils.constant.Language;
import net.minecraft.text.Text;

/**
 * <h2>Description</h2>
 * <p>
 *     This interface is used to translate the information in the given language.<br>
 *     The method {@link #translate(Language, String)} is used to translate the information to text and return the implementation class itself.
 * </p>
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #translate(Language, String)}.
 *
 * @param <T> The implementation class.
 * @author REN YuanTong
 * @Date 2025-04-05
 * @since 1.0.0
 * @see Language
 */
public interface Translatable<T> {
    /**
     * Add a translation for the given language.
     * @param lang The language.
     * @param value The translation value.
     * @return The implementation class itself.
     * @see Language
     */
    T translate(Language lang, String value);


    /**
     * <p>
     * A static Method used to translate from the translation Key {@code key} to {@link Text}.
     * </p>
     *
     * @param key the translation key
     * @return the text formatted to {@link Text}
     */
    static Text translateToText(String key, Object... args) {
        return Text.translatable(key, args);
    }

    /**
     * <p>
     * A static Method.
     * </p>
     *
     * <p>
     *     The method is used to translate from the translation Key {@code key} to {@link String}.
     * </p>
     *
     * @param key the translation key
     * @return the text formatted to {@link String}
     */
    static String translateToString(String key, Object... args) {
        return Text.translatable(key, args).getString();
    }
}
