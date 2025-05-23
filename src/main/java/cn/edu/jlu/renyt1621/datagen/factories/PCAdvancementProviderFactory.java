package cn.edu.jlu.renyt1621.datagen.factories;

import cn.edu.jlu.renyt1621.datagen.advancements.PCAdvancementContainer;
import cn.edu.jlu.renyt1621.interfaces.TriFunction;
import net.minecraft.data.DataOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.advancement.AdvancementTabGenerator;
import net.minecraft.registry.BuiltinRegistries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Util;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;

/**
 * <h2>Description</h2>
 *
 * <h2>Usages</h2>
 *
 * @author REN YuanTong
 * @Date 2025-05-21
 * @since 1.1.0
 */
public final class PCAdvancementProviderFactory {
    private PCAdvancementProviderFactory() {}

    public static List<DataProvider.Factory<? extends DataProvider>> createFor(PCAdvancementContainer container) {
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
