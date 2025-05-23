package cn.edu.jlu.renyt1621.register.tag;

import cn.edu.jlu.renyt1621.datagen.tag.container.PCBlockTag;
import cn.edu.jlu.renyt1621.datagen.tag.map.PCBlockTagKeyMap;
import cn.edu.jlu.renyt1621.register.PCRegister;
import cn.edu.jlu.renyt1621.utils.CheckUtils;
import cn.edu.jlu.renyt1621.utils.constant.Language;
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
 * <p>
 * You can use the method {@link #create(String namespace, String path)}, {@link #create(String path)} or {@link #create(Identifier identifier)}
 * to create a new {@code PCBlockTagKeyRegister}. <br>
 * You can also use the method {@link #createForVanilla(TagKey)} to create a new {@code PCBlockTagKeyRegister} for a vanilla tag key.
 * </p>
 *
 * <p>
 * Below is a simple usage:
 * </p>
 * <blockquote><pre>
 * public static final TagKey<Block> BLOCK_TAG =
 *     PCBlockTagKeyRegister.create(MOD_ID, "tag/block_tag")
 *         .addBlock(ModBlocks.BLOCK)
 *         .addTag(BlockTags.LOGS)
 *         .registerAndBuild()
 *         .get();
 *
 * public static final TagKey<Block> VANILLA_NEED_IRON_TOOLS_TAG =
 *     PCBlockTagKeyRegister.createForVanilla(BlockTags.NEEDS_IRON_TOOL)
 *         .addBlock(ModBlocks.BLOCK)
 *         .registerAndBuild()
 *         .get();
 * </pre></blockquote>
 *
 * The class also provides some pre-defined PCBlockTagKeyRegister for vanilla:
 * <ul >
 *     <li>{@link #VANILLA_NEED_IRON_TOOLS_TAG}</li>
 *     <li>{@link #VANILLA_NEED_DIAMOND_TOOLS_TAG}</li>
 *     <li>{@link #VANILLA_NEED_STONE_TOOLS_TAG}</li>
 *     <li>{@link #VANILLA_PICKAXE_TAG}</li>
 *     <li>{@link #VANILLA_SHOVEL_TAG}</li>
 *     <li>{@link #VANILLA_AXE_TAG}</li>
 *     <li>{@link #VANILLA_HOE_TAG}</li>
 * </ul>
 * @see PCBlockTag
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
        this.pcBlockTag = PCBlockTag.create(this.t);
    }

    /**
     * Create a new PCBlockTagKeyRegister for a vanilla block tag key.
     * @param vanillaBlockTagKey The vanilla block tag key.
     */
    public static PCBlockTagKeyRegister createForVanilla(TagKey<Block> vanillaBlockTagKey) {
        return new PCBlockTagKeyRegister(vanillaBlockTagKey.id());
    }

    /**
     * Create a new PCBlockTagKeyRegister for the given {@code path}.
     * @param path The path of the block tag key.
     */
    public static PCBlockTagKeyRegister create(String path) {
        return new PCBlockTagKeyRegister(Identifier.of(path));
    }

    /**
     * Create a new PCBlockTagKeyRegister for the given {@code identifier}.
     * @param identifier The identifier of the block tag key.
     */
    public static PCBlockTagKeyRegister create(Identifier identifier) {
        return new PCBlockTagKeyRegister(identifier);
    }

    /**
     * Create a new PCBlockTagKeyRegister for the given {@code namespace} and {@code path}.
     * @param namespace The namespace of the block tag key.
     * @param path The path of the block tag key.
     */
    public static PCBlockTagKeyRegister create(String namespace, String path) {
        return new PCBlockTagKeyRegister(Identifier.of(namespace, path));
    }

    /**
     * Add a block to the block tag key.
     * @param block The block to add.
     */
    public PCBlockTagKeyRegister addBlock(Block block) {
        checkNotNull("add(Block)");
        CheckUtils.checkIsNullThenThrow(
            block, "Block cannot be null when using PCBlockTagKeyRegister"
        );
        this.pcBlockTag.addBlock(block);

        return this;
    }

    /**
     * Add a tag to the block tag key.
     * @param tag The tag to add.
     */
    public PCBlockTagKeyRegister addTag(TagKey<Block> tag) {
        checkNotNull("add(TagKey<Block>)");
        CheckUtils.checkIsNullThenThrow(
            tag, "Tag cannot be null when using PCBlockTagKeyRegister"
        );
        this.pcBlockTag.addTag(tag);

        return this;
    }

    /**
     * Add blocks to the block tag key.
     * @param blocks The blocks to add.
     */
    public PCBlockTagKeyRegister addBlocks(List<Block> blocks) {
        checkNotNull("add(List<Block>)");
        CheckUtils.checkAnyIsNullThenThrow(
            blocks, "Blocks cannot be null when using PCBlockTagKeyRegister.add(Block...)"
        );

        this.pcBlockTag.addBlocks(blocks);
        return this;
    }

    /**
     * Add tags to the block tag key.
     * @param tags The tags to add.
     */
    public PCBlockTagKeyRegister addTags(List<TagKey<Block>> tags) {
        checkNotNull("add(List<TagKey<Block>>)");
        CheckUtils.checkAnyIsNullThenThrow(
            tags, "Tags cannot be null when using PCBlockTagKeyRegister.add(TagKey...)"
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
    public PCBlockTagKeyRegister translate(Language lang, String value) {
        throw new UnsupportedOperationException(
            "You shouldn't try to translate 'Block Tag Key': %s"
                .formatted(this.t.id())
        );
    }

    @Override
    protected PCBlockTagKeyRegister self() {
        return this;
    }

    /**
     * A PCBlockTagKeyRegister for a tag {@code NEEDS_IRON_TOOL} in vanilla.
     */
    public static final PCBlockTagKeyRegister VANILLA_NEED_IRON_TOOLS_TAG =
        PCBlockTagKeyRegister.createForVanilla(BlockTags.NEEDS_IRON_TOOL);

    /**
     * A PCBlockTagKeyRegister for a tag {@code NEEDS_DIAMOND_TOOL} in vanilla.
     */
    public static final PCBlockTagKeyRegister VANILLA_NEED_DIAMOND_TOOLS_TAG =
        PCBlockTagKeyRegister.createForVanilla(BlockTags.NEEDS_DIAMOND_TOOL);

    /**
     * A PCBlockTagKeyRegister for a tag {@code NEEDS_STONE_TOOL} in vanilla.
     */
    public static final PCBlockTagKeyRegister VANILLA_NEED_STONE_TOOLS_TAG =
        PCBlockTagKeyRegister.createForVanilla(BlockTags.NEEDS_STONE_TOOL);

    /**
     * A PCBlockTagKeyRegister for a tag {@code PICKAXE_MINEABLE} in vanilla.
     */
    public static final PCBlockTagKeyRegister VANILLA_PICKAXE_TAG =
        PCBlockTagKeyRegister.createForVanilla(BlockTags.PICKAXE_MINEABLE);

    /**
     * A PCBlockTagKeyRegister for a tag {@code SHOVEL_MINEABLE} in vanilla.
     */
    public static final PCBlockTagKeyRegister VANILLA_SHOVEL_TAG =
        PCBlockTagKeyRegister.createForVanilla(BlockTags.SHOVEL_MINEABLE);

    /**
     * A PCBlockTagKeyRegister for a tag {@code AXE_MINEABLE} in vanilla.
     */
    public static final PCBlockTagKeyRegister VANILLA_AXE_TAG =
        PCBlockTagKeyRegister.createForVanilla(BlockTags.AXE_MINEABLE);

    /**
     * A PCBlockTagKeyRegister for a tag {@code HOE_MINEABLE} in vanilla.
     */
    public static final PCBlockTagKeyRegister VANILLA_HOE_TAG =
        PCBlockTagKeyRegister.createForVanilla(BlockTags.HOE_MINEABLE);

}
