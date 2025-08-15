package io.github.piscescup.mc.fabric.datagen.loot;

import io.github.piscescup.mc.fabric.datagen.factories.option.PCLootTableProviderOption;
import io.github.piscescup.mc.fabric.datagen.loot.lists.PCBlockDropperList;
import io.github.piscescup.mc.fabric.register.loot.PCBlockDropRegister;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemConvertible;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.provider.number.LootNumberProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * <h2>Description</h2>
 * <p>
 *     A provider for generating block loot table.
 * </p>
 * <h2>Usages</h2>
 * <p>
 *     This class is used in {@link PCLootTableProviderOption} to generate block loot table.
 * </p>
 *
 * @see PCLootTableProviderOption
 * @author REN YuanTong
 * @Date 2025-04-17
 * @since 1.0.0
 */
public class PCBlockLootTableProvider
    extends FabricBlockLootTableProvider
{
    private final List<PCBlockDropRegister> dropperList = PCBlockDropperList.instance().getDropperList();

    public PCBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    /**
     *
     */
    @Override
    public void generate() {
        dropperList.forEach(this::addDropper);
    }

    private void addDropper(PCBlockDropRegister dropper) {
        Block target = dropper.getTarget();
        LootNumberProvider targetCount = dropper.getTargetCount();

        boolean silkTouch = dropper.isSilkTouch();

        if (silkTouch) {
            Block blockWithoutMatchTool = dropper.getBlockWithoutMatchTool();
            addDropWithSilkTouch(target, blockWithoutMatchTool);
            return;
        }
        ItemConvertible droppedItem = dropper.getDroppedItem();
        LootTable.Builder drop = dropper.getLootBuilder();

        LootTable.Builder lootBuilder = Optional.ofNullable(drop)
            .orElseGet(() -> drops(droppedItem, targetCount));

        if (dropper.isFortune()) {
            RegistryWrapper.Impl<Enchantment> impl = this.registries.getOrThrow(RegistryKeys.ENCHANTMENT);
            lootBuilder.apply(
                ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE))
            );
        }
    }

}
