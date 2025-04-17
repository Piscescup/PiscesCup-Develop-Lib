package cn.edu.jlu.renyt1621.datagen.factorys;

import cn.edu.jlu.renyt1621.datagen.models.PCModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.data.DataProvider;

import java.util.List;

/**
 * @author REN YuanTong
 * @Date 2025-04-17
 * @since 1.0.0
 */
public class PCModelProviderFactory {
    public static List<FabricDataGenerator.Pack.Factory<? extends DataProvider>> modelProvider() {
        return List.of(
            PCModelProvider::new
        );
    }

}
