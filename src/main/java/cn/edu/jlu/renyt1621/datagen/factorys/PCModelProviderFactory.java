package cn.edu.jlu.renyt1621.datagen.factorys;

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

    @Contract(value = " -> new", pure = true)
    public static @NotNull @Unmodifiable List<FabricDataGenerator.Pack.Factory<? extends DataProvider>> modelProvider() {
        return List.of(
            PCModelProvider::new
        );
    }

}
