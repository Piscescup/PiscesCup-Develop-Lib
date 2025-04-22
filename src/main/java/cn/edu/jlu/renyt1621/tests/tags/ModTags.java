package cn.edu.jlu.renyt1621.tests.tags;

import cn.edu.jlu.renyt1621.register.tag.PCItemTagKeyRegister;
import cn.edu.jlu.renyt1621.tests.items.ModItems;
import net.minecraft.item.Item;
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
        .add(ModItems.ITEM1)
        .add(ModItems.ITEM2)
        .registerAndBuild()
        .get();



    public static void register() {}
}
