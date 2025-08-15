package io.github.piscescup.mc.fabric.register.loot;

import io.github.piscescup.mc.fabric.register.PCRegister;
import io.github.piscescup.mc.fabric.register.PostRegisterConfig;
import net.minecraft.loot.LootTable;

/**
 * <h2>Description</h2>
 *
 * <h2>Usages</h2>
 *
 * @author REN YuanTong
 * @Date 2025-07-31
 * @since 1.1.2
 */
public interface LootTablePostRegisterConfig<R extends PCRegister<LootTable, R, LootTablePostRegisterConfig<R>>>
    extends PostRegisterConfig<LootTablePostRegisterConfig<R>, LootTable, R>
{
}
