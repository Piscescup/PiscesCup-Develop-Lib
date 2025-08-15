package io.github.piscescup.mc.fabric.datagen.factories;

import io.github.piscescup.mc.fabric.datagen.tag.PCTagProvider;
import io.github.piscescup.mc.fabric.register.tag.lists.PCTagKeyContainerList;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.data.DataProvider;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * <h2>Description</h2>
 *
 * <h2>Usages</h2>
 *
 * @author REN YuanTong
 * @Date 2025-07-26
 * @since
 */
public class PCTagProviderFactory
    implements DataProviderFactory<PCTagKeyContainerList<?>>
{

    PCTagProviderFactory() {}

    private static <T> FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider> toTagFactory(@NotNull PCTagKeyContainerList<T> option) {

        return
            (output, registriesFuture) ->
                PCTagProvider.Builder.create(option.getRegistryKey())
                    .output(output)
                    .registriesFuture(registriesFuture)
                    .containerList(option)
                    .build();
    }

    @Override
    public FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider> apply(@NotNull PCTagKeyContainerList<?> option) {
        return toTagFactory(option);
    }

    @Override
    public List<FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider>> factories() {
        return PCTagKeyContainerList.Option.ALL_TAG_LIST.stream()
            .map(this::apply)
            .toList();
    }
}
