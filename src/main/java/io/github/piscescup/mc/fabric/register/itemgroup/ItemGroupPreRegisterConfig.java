package io.github.piscescup.mc.fabric.register.itemgroup;

import io.github.piscescup.mc.fabric.register.PreRegisterConfig;
import io.github.piscescup.mc.fabric.utils.constant.Language;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * A {@link PreRegisterConfig} for {@link ItemGroup}
 *
 * @author REN YuanTong
 * @Date 2025-07-31
 * @since 1.1.2
 */
public interface ItemGroupPreRegisterConfig
{
    /**
     * <p>
     * An interface is used to set the position after creating a {@link PCItemGroupRegister} by the method: {@link PCItemGroupRegister#create}
     * </p>
     * <p>
     *     That means the user must use the method {@link #position(ItemGroup.Row, int)} to set the position of the {@link ItemGroup}
     *     after using the method {@link PCItemGroupRegister#create}.
     * </p>
     */
    interface PositionConfig {
        /**
         * Set the position of the {@link ItemGroup}
         * @param row The row of the {@link ItemGroup}
         * @param column The column of the {@link ItemGroup}
         * @return A {@link IconConfig}, which is used to set the icon of the {@link ItemGroup}
         */
        IconConfig position(ItemGroup.Row row, int column);
    }

    /**
     * <p>
     * An interface is used to set the icon after setting the position of the {@code ItemGroup} by the method{@link PositionConfig#position(ItemGroup.Row, int)}.
     * </p>
     * <p>
     *     That means the user must use the method {@link #icon(ItemConvertible)} to set the icon of the {@link ItemGroup}
     *     after using the method {@link PositionConfig#position(ItemGroup.Row, int)}.
     * </p>
     *
     */
    interface IconConfig
        extends PreRegisterConfig<ItemGroupPostRegisterConfig>
    {
        /**
         * Set the icon of the {@link ItemGroup}
         * @param icon The icon of the {@link ItemGroup}
         * @return A {@link OptionalConfig}, which is used to set the optional properties of the {@link ItemGroup}.
         * @throws NullPointerException if the {@code icon} is null.
         */
        OptionalConfig icon(@NotNull ItemConvertible icon);
    }

    /**
     * <p>
     *     An interface is used to set the optional properties after setting the icon of the {@code ItemGroup} by the method{@link IconConfig#icon(ItemConvertible)}.
     * </p>
     */
    interface OptionalConfig
        extends PreRegisterConfig<ItemGroupPostRegisterConfig>
    {
        /**
         * <p>
         *  Set the display name of the {@link ItemGroup}.
         * </p>
         * <p>
         *     The display name for the {@code ItemGroup} will be translated by the method{@link ItemGroupPostRegisterConfig#translate(Language, String)}<br>
         *     So this method will not be used normally.
         * </p>
         * @param displayName The display name of the {@link ItemGroup}.
         * @throws NullPointerException if the {@code displayName} is null.
         * @deprecated Use the method {@link ItemGroupPostRegisterConfig#translate(Language, String)} instead.
         */
        @Deprecated
        OptionalConfig displayName(@NotNull Text displayName);

        /**
         * <p>
         *     Add an item to the {@link ItemGroup}.
         * </p>
         * @param item The item to be added.
         * @throws NullPointerException if the {@code item} is null.
         */
        OptionalConfig addEntry(@NotNull ItemConvertible item);

        /**
         * <p>
         *     Add an item with the given {@link ItemGroup.StackVisibility} to the {@link ItemGroup}.
         * </p>
         * @param item The item to be added.
         * @param visibility The visibility of the item.
         * @throws NullPointerException if the {@code item} is null.
         */
        OptionalConfig addEntry(@NotNull ItemConvertible item, ItemGroup.StackVisibility visibility);

        /**
         * <p>
         *     Add items to the {@link ItemGroup}.
         * </p>
         * @param items The items to be added.
         * @throws NullPointerException if the {@code item} is or contains {@code null}.
         */
        OptionalConfig addEntries(@NotNull ItemConvertible... items);

        /**
         * <p>
         *     Add items to the {@link ItemGroup}.
         * </p>
         * @param entries The {@link Map} of item with special {@link ItemGroup.StackVisibility}.
         * @throws NullPointerException if the {@code entries} is or contains {@code null}.
         */
        OptionalConfig addEntries(@NotNull Map<ItemConvertible, ItemGroup.StackVisibility> entries);

        /**
         * <p>
         *     Set the {@code ItemGroup} as special.
         * </p>
         */
        OptionalConfig special();

        /**
         * <p>
         *     Set the {@code ItemGroup} as no rendered name.
         * </p>
         */
        OptionalConfig noRenderedName();

        /**
         * <p>
         *     Set the {@code ItemGroup} as no scrollbar.
         * </p>
         */
        OptionalConfig noScrollbar();

        /**
         * <p>
         *     Set the texture of the {@code ItemGroup} as the given {@code Identifier}.
         * </p>
         * @param texture The texture {@code Identifier} of the {@code ItemGroup}.
         */
        OptionalConfig texture(Identifier texture);
    }

}
