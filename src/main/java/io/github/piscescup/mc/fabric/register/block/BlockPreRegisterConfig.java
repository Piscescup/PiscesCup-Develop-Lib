package io.github.piscescup.mc.fabric.register.block;

import io.github.piscescup.mc.fabric.register.PreRegisterConfig;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

/**
 * A {@link PreRegisterConfig} for {@link Block}
 * @author REN YuanTong
 * @since 1.1.2
 */
public interface BlockPreRegisterConfig
    extends PreRegisterConfig<BlockPostRegisterConfig>
{
    /**
     * Set the {@link AbstractBlock.Settings} of the block.
     * @param settings The {@link AbstractBlock.Settings} of the block.
     * @throws NullPointerException If {@code settings} is null.
     */
    BlockPreRegisterConfig settings(@NotNull AbstractBlock.Settings settings);

    /**
     * Set the factory of the block. The factory is a {@link Function} that takes a {@link AbstractBlock.Settings} as input and returns a {@link Block}.
     * @param factory The factory of the block.
     * @throws NullPointerException If {@code factory} is null.
     */
    BlockPreRegisterConfig factory(@NotNull Function<AbstractBlock.Settings, Block> factory);
}
