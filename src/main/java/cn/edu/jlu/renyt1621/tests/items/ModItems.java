package cn.edu.jlu.renyt1621.tests.items;

import cn.edu.jlu.renyt1621.register.item.PCBlockItemRegister;
import cn.edu.jlu.renyt1621.register.item.PCItemRegister;
import cn.edu.jlu.renyt1621.datagen.recipes.craft.PCShapedRecipe;
import cn.edu.jlu.renyt1621.datagen.recipes.craft.PCShapelessRecipe;
import cn.edu.jlu.renyt1621.utils.constant.Language;
import net.minecraft.client.data.Models;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Rarity;


import static cn.edu.jlu.renyt1621.References.*;
import static cn.edu.jlu.renyt1621.tests.blocks.ModBlocks.*;

/**
 * @author REN YuanTong
 * @Date 2025-04-06
 */
public class ModItems {
    public static final Item ITEM1 = PCItemRegister.create(MOD_ID, "item1")
        .registerAndBuild()
        .shapedRecipe(PCShapedRecipe.Builder.create()
            .pattern("***")
            .pattern("***")
            .pattern(" # ")
            .definition('*', Items.ACACIA_PLANKS)
            .definition('#', Items.ACACIA_BUTTON)
            .category(RecipeCategory.BUILDING_BLOCKS)
            .criterion("has_item", Items.ACACIA_PLANKS)
            .count(4)
            .build()
        )
        .translate(Language.EN_US, "Test Item1")
        .translate(Language.ZH_CN, "测试物品1")
        .model(Models.GENERATED)
        .get();

    public static final Item ITEM2 = PCItemRegister.create(MOD_ID, "item2")
        .registerAndBuild()
        .shapelessRecipe(PCShapelessRecipe.Builder.create()
            .category(RecipeCategory.BUILDING_BLOCKS)
            .input(ItemTags.PLANKS)
            .input(ItemTags.BUTTONS)
            .input(Items.IRON_INGOT)
            .count(4)
            .criterion("has_planks", Items.IRON_INGOT)
            .build()
        )
        .translate(Language.EN_US, "Test Item2")
        .translate(Language.ZH_CN, "测试物品2")
        .model(Models.GENERATED)
        .get();


    public static final Item BLOCK_ITEM = PCBlockItemRegister.create(BLOCK)
        .settings(new Item.Settings()
            .maxCount(16)
            .fireproof()
            .rarity(Rarity.COMMON)
        )
        .registerAndBuild()
        .shapelessRecipe(PCShapelessRecipe.Builder.create()
            .category(RecipeCategory.BUILDING_BLOCKS)
            .input(ItemTags.PLANKS)
            .input(ItemTags.BUTTONS)
            .input(Items.IRON_INGOT)
            .count(4)
            .criterion("has_planks", Items.IRON_INGOT)
            .build()
        )
        .get();


    public static void register() {}

}
