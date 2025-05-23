package cn.edu.jlu.renyt1621.datagen.factories;

import cn.edu.jlu.renyt1621.datagen.loot.PCBlockLootProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.data.DataProvider;

import java.util.Arrays;
import java.util.List;

/**
 * <h1>Description</h1>
 *
 * <h1>Usages</h1>
 *
 * @author REN YuanTong
 * @Date 2025-04-26
 * @since 1.0.0
 */
public enum PCLootTableProviderFactory {
    BLOCK_LOOT_TABLE_PROVIDER(
        List.of(PCBlockLootProvider::new)
    ),
    ;

    private final List<FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider>> factories;
    PCLootTableProviderFactory(List<FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider>> factories) {
        this.factories = factories;
    }

    public List<FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider>> factories() {
        return factories;
    }

    public static List<FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider>> allLootTableProviderFactories() {
        return Arrays.stream(PCLootTableProviderFactory.values())
            .map(PCLootTableProviderFactory::factories)
            .flatMap(List::stream)
            .toList();
    }
}
