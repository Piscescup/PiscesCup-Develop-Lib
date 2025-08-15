package io.github.piscescup.mc.fabric.datagen.models.maps;

import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.List;

/**
 * @author REN YuanTong
 * @Date 2025-04-16
 * @since 1.0.0
 */
public class PCBlockModelMap
    extends PCModelMap<Block>
{
    private static volatile PCBlockModelMap INSTANCE;

    private final List<Block> SIMPLE_CUBE_ALL_BLOCKS = new ArrayList<>();

    private PCBlockModelMap() {
    }

    public static PCBlockModelMap instance() {
        if (INSTANCE == null) {
            synchronized (PCBlockModelMap.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PCBlockModelMap();
                }
            }
        }
        return INSTANCE;
    }

    public List<Block> getSimpleCubeAllBlocks() {
        return SIMPLE_CUBE_ALL_BLOCKS;
    }

    public boolean addToCubeAll(Block block) {
        return this.SIMPLE_CUBE_ALL_BLOCKS.add(block);
    }
}
