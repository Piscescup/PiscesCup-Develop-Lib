package io.github.piscescup.mc.fabric.register.loot;

import net.minecraft.block.Block;
import org.jetbrains.annotations.NotNull;

/**
 * <h2>Description</h2>
 *
 * <h2>Usages</h2>
 *
 * @author REN YuanTong
 * @Date 2025-07-31
 * @since 1.1.2
 */
public interface BlockLootTablePreRegisterConfig
    extends LootTablePreRegisterConfig<PCBlockDropRegister>
{
    /**
     * Set the block to drop when the destroying block without silk touch.
     * @param blockWithoutMatchTool The block to drop when the destroying block without silk touch.
     */
    LootTablePostRegisterConfig<PCBlockDropRegister> dropsWithSilkTouch(@NotNull Block blockWithoutMatchTool);
}
