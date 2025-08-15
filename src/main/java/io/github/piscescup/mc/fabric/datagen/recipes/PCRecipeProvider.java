package io.github.piscescup.mc.fabric.datagen.recipes;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;

/**
 *
 * @author REN YuanTong
 * @Date 2025-04-18
 * @since 1.1.2
 */
public final class PCRecipeProvider
    extends FabricRecipeProvider
{
    private final BiFunction<RegistryWrapper.WrapperLookup, RecipeExporter, ? extends PCRecipeGenerator> generator;

    public PCRecipeProvider(
        FabricDataOutput output,
        CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture,
        BiFunction<RegistryWrapper.WrapperLookup, RecipeExporter, ? extends PCRecipeGenerator> generator
    ) {
        super(output, registriesFuture);
        this.generator = generator;
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        // return new PCRecipeGenerator(wrapperLookup, recipeExporter);
        return generator.apply(wrapperLookup, recipeExporter);
    }

    @Override
    public String getName() {
        return "PCRecipeProvider";
    }
}
