package cn.edu.jlu.renyt1621.datagen.loot;

import cn.edu.jlu.renyt1621.datagen.factories.PCLootTableProviderFactory;
import cn.edu.jlu.renyt1621.datagen.loot.map.PCBlockDropMap;
import cn.edu.jlu.renyt1621.datagen.loot.map.PCSilkTouchDropMap;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.item.ItemConvertible;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

/**
 * <h2>Description</h2>
 * <p>
 *     A provider for generating block loot table.
 * </p>
 * <h2>Usages</h2>
 * <p>
 *     This class is used in {@link PCLootTableProviderFactory} to generate block loot table.
 * </p>
 *
 * @see PCLootTableProviderFactory
 * @author REN YuanTong
 * @Date 2025-04-17
 * @since 1.0.0
 */
public class PCBlockLootProvider
    extends FabricBlockLootTableProvider
{
    private final PCBlockDropMap blockDropMap = PCBlockDropMap.getInstance();
    private final PCSilkTouchDropMap silkTouchDropMap = PCSilkTouchDropMap.instance();

    public PCBlockLootProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    /**
     *
     */
    @Override
    public void generate() {
        blockDropMap.get()
            .forEach(
                (block, pcBlockDrop) -> {
                    // Block block1 = pcBlockDrop.getBlock();
                    Block drop = pcBlockDrop.getDrop();
                    ItemConvertible itemDropped = pcBlockDrop.getItemDropped();
                    int itemCount = pcBlockDrop.getItemCount();
                    LootTable.Builder lootBuilder = pcBlockDrop.getLootBuilder();

                    if (drop != null) {
                        addDrop(block, drop);
                    } else if (itemDropped != null) {
                        addDrop(
                            block,
                            drops(itemDropped)
                                .apply(
                                    SetCountLootFunction.builder(
                                        ConstantLootNumberProvider.create(itemCount)
                                    )
                                )
                        );
                    }

                    if (lootBuilder != null) {
                        addDrop(block, lootBuilder);
                    }

                }
            );

    }


}
