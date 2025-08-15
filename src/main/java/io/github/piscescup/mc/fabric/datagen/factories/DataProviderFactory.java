package io.github.piscescup.mc.fabric.datagen.factories;

import io.github.piscescup.mc.fabric.datagen.factories.option.DataGenFactoryOption;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.data.DataProvider;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

/**
 * <h2>Description</h2>
 *
 * <h2>Usages</h2>
 *
 * @author REN YuanTong
 * @Date 2025-07-24
 * @since 1.1.2
 */
public interface DataProviderFactory<O extends DataGenFactoryOption> {
    FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider> apply(@NotNull O option);

    default List<FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider>> applyAll(@NotNull List<O> options) {
        return options.stream()
            .map(this::apply)
            .toList();
    }

    default List<FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider>> applyAll(@NotNull O... options) {
        return applyAll(Arrays.asList(options));
    }


    List<FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider>> factories();
}
