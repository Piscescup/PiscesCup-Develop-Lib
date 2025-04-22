package cn.edu.jlu.renyt1621.datagen.factories;

import cn.edu.jlu.renyt1621.datagen.models.PCModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.data.DataProvider;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

/**
 * @author REN YuanTong
 * @Date 2025-04-17
 * @since 1.0.0
 */
public final class PCModelProviderFactory {
    private PCModelProviderFactory() {}

    /**
     * <p>
     * Provide a list of {@code FabricDataGenerator.Pack.RegistryDependentFactory} for model provider.
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
     * 		  PCModelProviderFactory.modelProvider()
     * 			.forEach(pack::addProvider);
     *
     *    }
     * }
     * </pre></blockquote>
     *
     *
     * @return A list of {@code RegistryDependentFactory} for recipe provider
     */
    @Contract(value = " -> new", pure = true)
    public static @NotNull @Unmodifiable List<FabricDataGenerator.Pack.Factory<? extends DataProvider>> modelProvider() {
        return List.of(
            PCModelProvider::new
        );
    }

}
