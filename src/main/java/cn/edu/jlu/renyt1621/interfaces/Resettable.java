package cn.edu.jlu.renyt1621.interfaces;

/**
 * <h2>Description</h2>
 *
 * <p>
 *     The interface is used to set the value to the default value.
 * </p>
 *
 * <h2>Usage</h2>
 * Below is a simple example of how to use the interface:
 * <blockquote><pre>
 *     public class KeyBind
 *          implements Resettable<KeyBind>
 *     {
 *         private final String defaultKeybinds;
 *         private String keybinds;
 *
 *         public KeyBind(String defaultKeybinds) {
 *             this.defaultKeybinds = defaultKeybinds;
 *             this.keybinds = defaultKeybinds;
 *         }
 *
 *         &#64;Override
 *         public KeyBind reset() {
 *             KeyBind bind = new KeyBind(this.keybinds);
 *             this.keybinds = defaultKeybinds;
 *             return bind;
 *         }
 *     }
 * </pre></blockquote>
 *
 * @param <T> The type of the value which can be reset.
 * @author REN YuanTong
 * @Date 2025-04-06
 * @since 1.0.0
 */
@FunctionalInterface
public interface Resettable<T> {
    /**
     * The method is a method to set to the default value.
     * @return The value before reset.
     */
    T reset();
}

