package cn.edu.jlu.renyt1621.tests.tags;

import cn.edu.jlu.renyt1621.register.tag.PCBlockTagKeyRegister;
import cn.edu.jlu.renyt1621.register.tag.PCItemTagKeyRegister;
import cn.edu.jlu.renyt1621.tests.blocks.ModBlocks;
import cn.edu.jlu.renyt1621.tests.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;

import static cn.edu.jlu.renyt1621.References.MOD_ID;

/**
 * Test tags.
 *
 * @author REN YuanTong
 * @Date 2025-04-22
 */
public class ModTags {
    public static final TagKey<Item> TAG_KEY_1 = PCItemTagKeyRegister.create(MOD_ID, "tag/tag1")
        .addItem(ModItems.ITEM1)
        .addItem(ModItems.ITEM2)
        .addTag(ItemTags.PLANKS)
        .registerAndBuild()
        .get();

    public static final TagKey<Block> BLOCK_TAG = PCBlockTagKeyRegister.create(MOD_ID, "tag/block_tag")
        .addBlock(ModBlocks.BLOCK)
        .addTag(BlockTags.LOGS)
        .registerAndBuild()
        .get();

    public static final TagKey<Block> VANILLA_NEED_IRON_TOOLS_TAG =
        PCBlockTagKeyRegister.createForVanilla(BlockTags.NEEDS_IRON_TOOL)
            .addBlock(ModBlocks.BLOCK)
            .registerAndBuild()
            .get();

    public static final TagKey<Block> VANILLA_PICKAXE_TAG =
        PCBlockTagKeyRegister.createForVanilla(BlockTags.PICKAXE_MINEABLE)
            .addBlock(ModBlocks.BLOCK)
            .registerAndBuild()
            .get();



    public static void register() {}
}
