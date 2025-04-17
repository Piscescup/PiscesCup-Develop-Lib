package cn.edu.jlu.renyt1621.datagen.models;

import cn.edu.jlu.renyt1621.datagen.models.maps.PCItemModelMap;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;

/**
 * @author REN YuanTong
 * @Date 2025-04-14
 * @Time 09:03
 * @since 1.0.0
 */
public class PCModelProvider
    extends FabricModelProvider
{

    public PCModelProvider(FabricDataOutput output) {
        super(output);
    }

    /**
     * @param blockStateModelGenerator
     */
    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        // blockStateModelGenerator.re
    }

    /**
     * @param itemModelGenerator
     */
    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        PCItemModelMap.instance().get()
            .forEach(itemModelGenerator::register);
    }
}
