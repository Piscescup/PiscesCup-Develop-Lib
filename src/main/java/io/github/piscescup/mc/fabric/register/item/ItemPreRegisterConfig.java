package io.github.piscescup.mc.fabric.register.item;

import io.github.piscescup.mc.fabric.register.PCRegister;
import io.github.piscescup.mc.fabric.register.PreRegisterConfig;
import net.minecraft.item.Item;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

/**
 * A {@link PreRegisterConfig} for {@link Item}.
 * @author REN YuanTong
 * @Date 2025-07-31
 * @since 1.1.2
 */
public interface ItemPreRegisterConfig<R extends PCRegister<Item, R, ItemPostRegisterConfig<R>>>
    extends PreRegisterConfig<ItemPostRegisterConfig<R>>
{
    /**
     * Set the factory of the item. The factory is a {@link Function} that takes a {@link Item.Settings} as input and returns an {@link Item}.
     * @param factory The factory of the item.
     * @throws IllegalArgumentException If the {@code factory} is null.
     */
    ItemPreRegisterConfig<R> factory(@NotNull Function<Item.Settings, Item> factory);

    /**
     * Set the settings of the item.
     * @param settings The settings of the item.
     * @throws IllegalArgumentException If the {@code settings} is null.
     */
    ItemPreRegisterConfig<R> settings(@NotNull Item.Settings settings);

}
