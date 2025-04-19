package cn.edu.jlu.renyt1621.datagen.factorys;

import cn.edu.jlu.renyt1621.datagen.recipes.PCRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author REN YuanTong
 * @Date 2025-04-18
 * @since 1.0.0
 */
public final class PCRecipesProviderFactory {
    private PCRecipesProviderFactory() {}

    /**
     * <p>
     * Provide a list of {@code FabricDataGenerator.Pack.RegistryDependentFactory} for recipe provider.
     * </p>
     *
     * <p>
     * Use this method in {@code DataGenerator} provided by <strong>Fabric</strong>
     * </p>
     *
     * <p>
     *     Below is a simple usage:
     * </p>
     *
     * <blockquote><pre>
     * public class PiscesCupDevelopLibDataGenerator
     *     implements DataGeneratorEntrypoint
     * {
     *    &#64;Override
     *    public void onInitializeDataGenerator(FabricDataGenerator generator) {
     * 		  FabricDataGenerator.Pack pack = generator.createPack();
     *
     * 		  PCRecipesProviderFactory.recipesProvider()
     * 			  .forEach(pack::addProvider);
     *
     *    }
     * }
     * </pre></blockquote>
     *
     *
     * @return A list of {@code RegistryDependentFactory} for recipe provider
     */
    public static List<FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider>> recipesProvider() {
        return List.of(
            PCRecipeProvider::new
        );
    }

}
