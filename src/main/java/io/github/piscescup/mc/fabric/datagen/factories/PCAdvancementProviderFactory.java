package io.github.piscescup.mc.fabric.datagen.factories;

import io.github.piscescup.mc.fabric.datagen.advancements.PCAdvancementTabContainer;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.advancement.AdvancementProvider;
import net.minecraft.registry.RegistryWrapper;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;

/**
 * <h2>Description</h2>
 * <p>
 *     This is a factory class for creating advancement providers.
 * </p>
 * <h2>Usages</h2>
 * <p>
 *     Use the method {@link #createFor} to map the {@link PCAdvancementTabContainer} to a list of {@link DataProvider.Factory}.<br>
 *     Use the list to apply a {@code forEach} method to add the advancement providers to {@link DataGenerator.Pack}.
 * </p>
 * <p>
 *     Below is a simple example:
 * </p>
 * <blockquote><pre>
 * public class PiscesCupDevelopLibDataGenerator
 *     implements DataGeneratorEntrypoint
 * {
 *     &#64;Override
 *     public void onInitializeDataGenerator(FabricDataGenerator generator) {
 *         FabricDataGenerator.Pack pack = generator.createPack();
 *
 *         PCAdvancementProviderFactory.createFor(
 *         	   PCAdvancementTabContainer.instance()
 *         		   .addAdvancementTab(ModTabAdvancement::new)
 *         )
 *             .forEach(pack::addProvider);
 *     }
 * }
 * </pre></blockquote>
 *
 * @author REN YuanTong
 * @Date 2025-05-21
 * @since 1.1.0
 * @see PCAdvancementTabContainer
 */
public final class PCAdvancementProviderFactory
    implements DataProviderFactory<PCAdvancementTabContainer>
{
    PCAdvancementProviderFactory() {}

    @Deprecated
    public static List<FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider>> create() {
        return createFor(PCAdvancementTabContainer.instance());
    }

    private static List<FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider>> createFor(
        PCAdvancementTabContainer container
    ) {

        return List.of(
            toFactory(
                container::createAdvancementProvider
            )
        );
    }

    @Override
    public FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider> apply(
        @NotNull PCAdvancementTabContainer option
    ) {
        return toFactory(
            option::createAdvancementProvider
        );
    }

    @Override
    public List<FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider>> factories() {
        return List.of();
    }

    private static FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider> toFactory(
        BiFunction<
            DataOutput,
            CompletableFuture<RegistryWrapper.WrapperLookup>,
            AdvancementProvider
        > baseFactory
    ) {
        return baseFactory::apply;
    }

}
