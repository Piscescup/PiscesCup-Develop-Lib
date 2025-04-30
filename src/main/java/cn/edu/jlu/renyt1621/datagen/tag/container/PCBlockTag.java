package cn.edu.jlu.renyt1621.datagen.tag.container;

import net.minecraft.block.Block;
import net.minecraft.registry.tag.TagKey;

import java.util.ArrayList;
import java.util.List;

/**
 * <h2>Description</h2>
 *
 * <h2>Usages</h2>
 *
 * @author REN YuanTong
 * @Date 2025-04-22
 * @since 1.0.0
 */
public class PCBlockTag {
    private TagKey<Block> tag;

    private final List<Block> blocks = new ArrayList<>();
    private final List<TagKey<Block>> tags = new ArrayList<>();

    private PCBlockTag(TagKey<Block> tag) {
        this.tag = tag;
    }

    public static PCBlockTag create(TagKey<Block> tag) {
        return new PCBlockTag(tag);
    }

    public TagKey<Block> getTag() {
        return tag;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public List<TagKey<Block>> getTags() {
        return tags;
    }

    /**
     * Adds the given block to this tag.
     * @param block The block to be added.
     */
    public boolean addBlock(Block block) {

        return blocks.add(block);
    }

    /**
     * Adds the given blocks to this tag.
     * @param blocks The blocks to be added.
     */
    public boolean addBlocks(List<Block> blocks) {
        return this.blocks.addAll(blocks);
    }

    /**
     * Adds the given tag to this tag.
     * @param tag The tag to be added.
     */
    public boolean addTag(TagKey<Block> tag) {
        return tags.add(tag);
    }

    /**
     * Adds the given tags to this tag.
     * @param tags The tags to be added.
     */
    public boolean addTags(List<TagKey<Block>> tags) {
        return this.tags.addAll(tags);
    }

    /**
     * Returns {@code true} if this list contains no elements.
     *
     * @return {@code true} if this list contains no elements
     */
    public boolean isEmpty() {
        return blocks.isEmpty() && tags.isEmpty();
    }

}
