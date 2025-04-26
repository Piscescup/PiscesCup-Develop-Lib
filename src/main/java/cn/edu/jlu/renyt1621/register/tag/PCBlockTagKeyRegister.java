package cn.edu.jlu.renyt1621.register.tag;

import cn.edu.jlu.renyt1621.datagen.tag.container.PCBlockTag;
import cn.edu.jlu.renyt1621.datagen.tag.map.PCBlockTagKeyMap;
import cn.edu.jlu.renyt1621.register.PCRegister;
import cn.edu.jlu.renyt1621.utils.CheckUtils;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <h2>Description</h2>
 * A util for registering block tag keys.
 *
 * <h2>Usages</h2>
 * Below is a simple usage:
 * <blockquote><pre>
 *
 * </pre></blockquote>
 * @author REN YuanTong
 * @Date 2025-04-21
 * @since 1.0.0
 */
public class PCBlockTagKeyRegister
    extends PCRegister<TagKey<Block>, PCBlockTagKeyRegister>
{
    private final PCBlockTag pcBlockTag;

    private PCBlockTagKeyRegister(Identifier id) {
        super(id);
        this.t = TagKey.of(RegistryKeys.BLOCK, this.id);
        this.pcBlockTag = new PCBlockTag(this.t);
    }

    public static PCBlockTagKeyRegister createForVanilla(TagKey<Block> vanillaBlockTagKey) {
        return new PCBlockTagKeyRegister(vanillaBlockTagKey.id());
    }

    public static PCBlockTagKeyRegister create(String path) {
        return new PCBlockTagKeyRegister(Identifier.of(path));
    }

    public static PCBlockTagKeyRegister create(Identifier identifier) {
        return new PCBlockTagKeyRegister(identifier);
    }

    public static PCBlockTagKeyRegister create(String namespace, String path) {
        return new PCBlockTagKeyRegister(Identifier.of(namespace, path));
    }

    public PCBlockTagKeyRegister addBlock(Block block) {
        checkNotNull("add(Block)");
        CheckUtils.checkIsNullThenThrow(
            block, "Block cannot be null when using PCBlockTagKeyRegister"
        );
        this.pcBlockTag.addBlock(block);

        return this;
    }

    public PCBlockTagKeyRegister addTag(TagKey<Block> tag) {
        checkNotNull("add(TagKey<Block>)");
        CheckUtils.checkIsNullThenThrow(
            tag, "Tag cannot be null when using PCBlockTagKeyRegister"
        );
        this.pcBlockTag.addTag(tag);

        return this;
    }

    @SuppressWarnings("unchecked")
    public PCBlockTagKeyRegister addBlocks(List<Block> blocks) {
        checkNotNull("add(List<Block>)");
        CheckUtils.checkAnyIsNullThenThrow(
            Collections.singletonList(blocks), "Blocks cannot be null when using PCBlockTagKeyRegister.add(Block...)"
        );

        this.pcBlockTag.addBlocks(blocks);
        return this;
    }

    public PCBlockTagKeyRegister addTags(List<TagKey<Block>> tags) {
        checkNotNull("add(List<TagKey<Block>>)");
        CheckUtils.checkAnyIsNullThenThrow(
            List.of(tags), "Tags cannot be null when using PCBlockTagKeyRegister.add(TagKey...)"
        );

        this.pcBlockTag.addTags(tags);
        return this;
    }

    @Override
    public PCBlockTagKeyRegister registerAndBuild() {
        if ( this.pcBlockTag.isEmpty() )
            throw new IllegalArgumentException(
                "`Block` or `Tag` cannot be empty when using PCBlockTagKeyRegister."
            );

        PCBlockTagKeyMap.instance().putBlockTags(this.t, this.pcBlockTag);

        return this;
    }


    @Override
    protected PCBlockTagKeyRegister self() {
        return this;
    }

    public static final PCBlockTagKeyRegister VANILLA_NEED_IRON_TOOLS_TAG =
        PCBlockTagKeyRegister.createForVanilla(BlockTags.NEEDS_IRON_TOOL);

    public static final PCBlockTagKeyRegister VANILLA_NEED_DIAMOND_TOOLS_TAG =
        PCBlockTagKeyRegister.createForVanilla(BlockTags.NEEDS_DIAMOND_TOOL);

    public static final PCBlockTagKeyRegister VANILLA_NEED_STONE_TOOLS_TAG =
        PCBlockTagKeyRegister.createForVanilla(BlockTags.NEEDS_STONE_TOOL);


    public static final PCBlockTagKeyRegister VANILLA_PICKAXE_TAG =
        PCBlockTagKeyRegister.createForVanilla(BlockTags.PICKAXE_MINEABLE);

    public static final PCBlockTagKeyRegister VANILLA_SHOVEL_TAG =
        PCBlockTagKeyRegister.createForVanilla(BlockTags.SHOVEL_MINEABLE);

    public static final PCBlockTagKeyRegister VANILLA_AXE_TAG =
        PCBlockTagKeyRegister.createForVanilla(BlockTags.AXE_MINEABLE);

    public static final PCBlockTagKeyRegister VANILLA_HOE_TAG =
        PCBlockTagKeyRegister.createForVanilla(BlockTags.HOE_MINEABLE);

}
