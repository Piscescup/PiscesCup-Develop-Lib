package io.github.piscescup.mc.fabric.register.poi;

import io.github.piscescup.mc.fabric.register.PreRegisterConfig;
import net.minecraft.block.Block;

import java.util.List;

/**
 * <h2>Description</h2>
 *
 * <h2>Usages</h2>
 *
 * @author REN YuanTong
 * @Date 2025-07-31
 * @since 1.1.2
 */
public interface POIPreRegisterConfig {
    interface POIWorkBlockConfig {
        POIOptionConfig workBlock(Block block);

        POIOptionConfig workBlocks(List<Block> blocks);

        POIOptionConfig workBlocks(Block... blocks);
    }

    interface POIOptionConfig extends PreRegisterConfig<POIPostRegisterConfig>, POIWorkBlockConfig {
        POIOptionConfig ticketCount(int ticketCount);

        POIOptionConfig searchDistance(int searchDistance);
    }
}
