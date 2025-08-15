package io.github.piscescup.mc.fabric.register.block;

import io.github.piscescup.mc.fabric.register.PostRegisterConfig;
import net.minecraft.block.Block;
import net.minecraft.item.ItemConvertible;
import net.minecraft.loot.provider.number.LootNumberProvider;
import org.jetbrains.annotations.NotNull;

/**
 * A {@link PostRegisterConfig} for {@link Block}
 * @author REN YuanTong
 * @since 1.1.2
 */
public interface BlockPostRegisterConfig
    extends PostRegisterConfig<BlockPostRegisterConfig, Block, PCBlockRegister>
{
    /**
     * Set the model (Simple Cube All) for a block
     */
    BlockPostRegisterConfig simpleCubeAll();

    /**
     * Set the loot table which drops a block for a block
     * @param drop The block will be dropped.
     * @throws NullPointerException if {@code drop} is null.
     */
    BlockPostRegisterConfig drop(@NotNull Block drop);

    /**
     * Set the loot table which drops item (count: 1) for a block
     * @param itemDropped The item will be dropped.
     * @throws NullPointerException if {@code itemDropped} is null.
     */
    BlockPostRegisterConfig drop(@NotNull ItemConvertible itemDropped);

    /**
     * Set the loot table which drops items (count: itemCount) for a block
     * @param itemDropped The item will be dropped.
     * @param itemCount The count of item will be dropped.
     * @throws NullPointerException if {@code itemDropped} is null.
     */
    BlockPostRegisterConfig drop(@NotNull ItemConvertible itemDropped, LootNumberProvider itemCount);
}
