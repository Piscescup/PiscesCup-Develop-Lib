package cn.edu.jlu.renyt1621.datagen.models;

import cn.edu.jlu.renyt1621.datagen.models.maps.PCBlockModelMap;
import cn.edu.jlu.renyt1621.datagen.models.maps.PCItemModelMap;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import org.jetbrains.annotations.NotNull;

/**
 * @author REN YuanTong
 * @Date 2025-04-14
 * @Time 09:03
 * @since 1.0.0
 */
public class PCModelProvider
    extends FabricModelProvider
{
    private static final PCItemModelMap ITEM_MODEL_MAP = PCItemModelMap.instance();
    private static final PCBlockModelMap BLOCK_MODEL_MAP = PCBlockModelMap.instance();

    public PCModelProvider(FabricDataOutput output) {
        super(output);
    }

    /**
     * @param blockStateModelGenerator
     */
    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        BLOCK_MODEL_MAP.getSimpleCubeAllBlocks()
            .forEach(blockStateModelGenerator::registerSimpleCubeAll);

    }

    /**
     * @param itemModelGenerator
     */
    @Override
    public void generateItemModels(@NotNull ItemModelGenerator itemModelGenerator) {
        ITEM_MODEL_MAP.get()
            .forEach(itemModelGenerator::register);
    }
}
