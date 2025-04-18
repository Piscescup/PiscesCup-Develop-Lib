package cn.edu.jlu.renyt1621.datagen.factorys;

import cn.edu.jlu.renyt1621.datagen.recipes.PCRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * <h1>Description</h1>
 *
 * <h1>Usages</h1>
 *
 * @author REN YuanTong
 * @Date 2025-04-18
 * @since 1.0.0
 */
public final class PCRecipesProviderFactory {
    private PCRecipesProviderFactory() {}

    public static List<FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider>> recipesProvider() {
        return List.of(
            PCRecipeProvider::new
        );
    }

}
