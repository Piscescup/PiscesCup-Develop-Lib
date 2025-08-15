package io.github.piscescup.mc.fabric.datagen.factories.option;

import io.github.piscescup.mc.fabric.datagen.loot.PCBlockLootTableProvider;
import io.github.piscescup.mc.fabric.datagen.loot.PCEntityLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.data.DataProvider;

/**
 * <h1>Description</h1>
 *
 * <h1>Usages</h1>
 *
 * @author REN YuanTong
 * @Date 2025-04-26
 * @since 1.0.0
 */
public class PCLootTableProviderOption
{
    public static final FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider> BLOCK_LOOT_TABLE_PROVIDER =
        PCBlockLootTableProvider::new;

    /**
     * <p>
     *     This option is deprecated.<br>
     *     The PCEntityLootTableProvider cannot be used.
     * </p>
     *
     */
    @Deprecated
    public static final FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider> ENTITY_LOOT_TABLE_PROVIDER =
        PCEntityLootTableProvider::new;

}
