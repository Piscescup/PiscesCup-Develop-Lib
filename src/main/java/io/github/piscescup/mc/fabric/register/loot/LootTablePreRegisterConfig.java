package io.github.piscescup.mc.fabric.register.loot;

import io.github.piscescup.mc.fabric.register.PCRegister;
import io.github.piscescup.mc.fabric.register.PreRegisterConfig;
import net.minecraft.item.ItemConvertible;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.provider.number.LootNumberProvider;
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
public interface LootTablePreRegisterConfig<R extends PCRegister<LootTable, R, LootTablePostRegisterConfig<R>>>
    extends PreRegisterConfig<LootTablePostRegisterConfig<R>>
{
    /**
     * Set the loot table builder.
     * @param lootBuilder The loot table builder.
     */
    LootTablePostRegisterConfig<R> dropBuilder(@NotNull LootTable.Builder lootBuilder);

    /**
     * Set the item dropped and the count.
     * @param itemDropped The item dropped.
     * @param itemCount The count of the item dropped.
     * @param bonus Whether apply the enchantment {@code Fortune}/{@code Looting} or not.<br>
     *              If {@code true}, the item will be dropped with the enchantment {@code Fortune}/{@code Looting}.
     */
    LootTablePostRegisterConfig<R> drops(@NotNull ItemConvertible itemDropped, LootNumberProvider itemCount, boolean bonus);

    default LootTablePostRegisterConfig<R> drops(@NotNull ItemConvertible itemDropped, LootNumberProvider itemCount) {
        return drops(itemDropped, itemCount, true);
    }
}
