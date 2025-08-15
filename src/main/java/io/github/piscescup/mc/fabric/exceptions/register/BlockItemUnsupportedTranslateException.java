package io.github.piscescup.mc.fabric.exceptions.register;

import net.minecraft.item.Item;

import java.io.Serial;

/**
 * <h1>Description</h1>
 *
 * <h1>Usages</h1>
 *
 * @author REN YuanTong
 * @Date 2025-05-17
 * @since 1.0.2
 */
public class BlockItemUnsupportedTranslateException extends RuntimeException {
    public BlockItemUnsupportedTranslateException() {
        super();
    }

    public BlockItemUnsupportedTranslateException(Item item) {
        super(
            "Shouldn't try to translate 'Block Item': %s by the method 'translate(Language, String)' in the class 'PCBlockItemRegister'"
                .formatted(item.getTranslationKey())
        );
    }

    @Serial
    private static final long serialVersionUID = -1242599979026084673L;
}
