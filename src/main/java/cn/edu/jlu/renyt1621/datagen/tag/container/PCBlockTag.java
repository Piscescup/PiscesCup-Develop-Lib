package cn.edu.jlu.renyt1621.datagen.tag.container;

import cn.edu.jlu.renyt1621.utils.CheckUtils;
import net.minecraft.block.Block;
import net.minecraft.registry.tag.TagKey;

import java.util.ArrayList;
import java.util.Collections;
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


    public PCBlockTag(TagKey<Block> tag) {
        this.tag = tag;
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

    public boolean addBlock(Block block) {

        return blocks.add(block);
    }

    public void addBlocks(List<Block> blocks) {
        this.blocks.addAll(blocks);
    }

    public boolean addTag(TagKey<Block> tag) {

        return tags.add(tag);
    }


    public final void addTags(List<TagKey<Block>> tags) {
        this.tags.addAll(tags);
    }

    public boolean isEmpty() {
        return blocks.isEmpty() && tags.isEmpty();
    }

}
