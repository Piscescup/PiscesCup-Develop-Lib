package io.github.piscescup.mc.fabric.register.loot;

import net.minecraft.item.ItemConvertible;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.provider.number.LootNumberProvider;

/**
 * This interface defines loot drop behavior for configurable targets.
 * Provides methods to define and configure loot table generation for items.
 *
 * <h2>Usages</h2>
 * Implement this interface in objects that require custom loot table generation.
 * Used during loot table building process to register droppable items and quantities.
 *
 * @param <T> The type of target object that has loot table.
 * @author REN YuanTong
 * @since 1.1.0
 */
public interface Droppable<T> {
    /**
     * Gets the target object associated with this droppable item.
     *
     * @return The target object that has loot table.
     */
    T getTarget();

    /**
     * Gets the item to be dropped when the target is defeated/destroyed.
     *
     * @return The dropped item as an ItemConvertible
     */
    ItemConvertible getDroppedItem();

    /**
     * Gets the quantity of items to be dropped.
     *
     * @return The number of items to drop as integer
     */
    LootNumberProvider getTargetCount();

    /**
     * Gets the loot table builder configured for this droppable item.
     *
     * @return A LootTable.Builder instance with configured drops
     */
    LootTable.Builder getLootBuilder();
}