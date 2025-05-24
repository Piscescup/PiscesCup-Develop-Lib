package cn.edu.jlu.renyt1621.datagen.factories;

import cn.edu.jlu.renyt1621.datagen.advancements.PCAdvancementTabContainer;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.registry.BuiltinRegistries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Util;

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
public final class PCAdvancementProviderFactory {
    private PCAdvancementProviderFactory() {}

    public static List<DataProvider.Factory<? extends DataProvider>> createFor(PCAdvancementTabContainer container) {
        CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture = CompletableFuture.supplyAsync(
            BuiltinRegistries::createWrapperLookup, Util.getMainWorkerExecutor()
        );
        return List.of(
            toFactory(
                container::createAdvancementProvider,
                completableFuture
            )
        );
    }

    private static <T extends DataProvider> DataProvider.Factory<T> toFactory(
        BiFunction<
            DataOutput,
            CompletableFuture<RegistryWrapper.WrapperLookup>,
            T
        > baseFactory,
        CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture
    ) {
        return output -> (T)baseFactory.apply(output, registriesFuture);
    }

}
