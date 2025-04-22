package cn.edu.jlu.renyt1621.register.tag;

import cn.edu.jlu.renyt1621.datagen.tag.container.PCBlockTag;
import cn.edu.jlu.renyt1621.datagen.tag.map.PCBlockTagKeyMap;
import cn.edu.jlu.renyt1621.register.PCRegister;
import cn.edu.jlu.renyt1621.utils.CheckUtils;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.List;

/**
 * <h2>Description</h2>
 *
 * <h2>Usages</h2>
 *
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

    public static PCBlockTagKeyRegister create(String path) {
        return new PCBlockTagKeyRegister(Identifier.of(path));
    }

    public static PCBlockTagKeyRegister create(Identifier identifier) {
        return new PCBlockTagKeyRegister(identifier);
    }

    public static PCBlockTagKeyRegister create(String namespace, String path) {
        return new PCBlockTagKeyRegister(Identifier.of(namespace, path));
    }

    public PCBlockTagKeyRegister add(Block block) {
        checkNotNull("add(Block)");
        CheckUtils.checkIsNullThenThrow(
            block, "Block cannot be null when using PCBlockTagKeyRegister"
        );
        this.pcBlockTag.addBlock(block);

        return this;
    }

    public PCBlockTagKeyRegister add(TagKey<Block> tag) {
        checkNotNull("add(TagKey<Block>)");
        CheckUtils.checkIsNullThenThrow(
            tag, "Tag cannot be null when using PCBlockTagKeyRegister"
        );
        this.pcBlockTag.addTag(tag);

        return this;
    }

    public PCBlockTagKeyRegister add(Block... blocks) {
        checkNotNull("add(Block...)");
        CheckUtils.checkAnyIsNullThenThrow(
            List.of(blocks), "Blocks cannot be null when using PCBlockTagKeyRegister.add(Block...)"
        );

        this.pcBlockTag.addBlocks(blocks);
        return this;
    }

    public PCBlockTagKeyRegister add(List<TagKey<Block>> tags) {
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

}
