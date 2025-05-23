package cn.edu.jlu.renyt1621.interfaces;

import net.minecraft.text.Text;

/**
 * <h2>Description</h2>
 * <p>
 *     Classes implemented this interface <b>Translatable</b> is used to translate the information to text.
 * </p>
 *
 * <p>
 *     The interface also offer a static method <code>translate(String key)</code>
 *     to translate the translation Key {@code key} to text.
 * </p>
 *
 * @author REN YuanTong
 * @Date 2025-04-05
 * @since 1.0.0
 */
public interface Translatable<T> {
    /**
     * The method to translate the information to text.
     * @return the text formatted to {@link Text}
     */
    Text translate();

    /**
     * <p>
     * A static Method.
     * </p>
     *
     * <p>
     *     The method is used to translate from the translation Key {@code key} to text.
     * </p>
     *
     * @param key the translation key
     * @return the text formatted to {@link Text}
     */
    static Text translate(String key) {
        return Text.translatable(key);
    }
}
