package cn.edu.jlu.renyt1621.datagen.models;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;

/**
 * @author REN YuanTong
 * @Description
 * @Date 2025-04-14
 * @Time 09:03
 */
public class PCDLModelProvider
    extends FabricModelProvider
{

    public PCDLModelProvider(FabricDataOutput output) {
        super(output);
    }

    /**
     * @param blockStateModelGenerator
     */
    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        // blockStateModelGenerator.
    }

    /**
     * @param itemModelGenerator
     */
    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        // itemModelGenerator.upload(
        //     ModItem.ITEM1,
        //
        // )
    }
}
