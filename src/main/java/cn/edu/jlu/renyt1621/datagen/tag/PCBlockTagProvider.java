package cn.edu.jlu.renyt1621.datagen.tag;

import cn.edu.jlu.renyt1621.datagen.tag.container.PCBlockTag;
import cn.edu.jlu.renyt1621.datagen.tag.map.PCBlockTagKeyMap;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * <h2>Description</h2>
 *
 * <h2>Usages</h2>
 *
 * @author REN YuanTong
 * @Date 2025-04-22
 * @since 1.0.0
 */
public class PCBlockTagProvider
    extends FabricTagProvider<Block>
{
    private static final Map<TagKey<Block>, PCBlockTag> BLOCK_TAG_MAP = PCBlockTagKeyMap.instance().getItemTagMap();

    public PCBlockTagProvider(
        FabricDataOutput output,
        CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture
    ) {
        super(output, RegistryKeys.BLOCK, registriesFuture);
    }


    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {

        BLOCK_TAG_MAP.forEach(
            (tagKey, blockTag) -> {
                List<Block> blocks = blockTag.getBlocks();
                List<TagKey<Block>> tags = blockTag.getTags();
                FabricTagProvider<Block>.FabricTagBuilder tagBuilder = getOrCreateTagBuilder(tagKey);

                blocks.forEach(tagBuilder::add);
                tags.forEach(tagBuilder::addOptionalTag);
            }
        );
    }
}
