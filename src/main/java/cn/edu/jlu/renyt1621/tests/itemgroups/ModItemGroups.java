package cn.edu.jlu.renyt1621.tests.itemgroups;

import cn.edu.jlu.renyt1621.register.itemgroup.PCItemGroupRegister;
import cn.edu.jlu.renyt1621.tests.blocks.ModBlocks;
import cn.edu.jlu.renyt1621.tests.items.ModItems;
import cn.edu.jlu.renyt1621.utils.constant.Language;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import static cn.edu.jlu.renyt1621.References.*;

/**
 * @author REN YuanTong
 * @Description
 * @Date 2025-04-14
 * @Time 15:06
 */
public class ModItemGroups {
    public static final ItemGroup ITEM_GROUP1 = PCItemGroupRegister.create(MOD_ID, "item_group1")
        .itemGroupBuilder(
            ItemGroup.create(ItemGroup.Row.BOTTOM, 7)
                .icon(() -> new ItemStack(Items.DIAMOND))
                .entries(
                    (text, entries) -> {
                        entries.add(ModItems.ITEM1);
                        entries.add(ModBlocks.BLOCK);
                    }
                )
        )
        .registerAndBuild()
        .translate(Language.EN_US, "Test ItemGroup1")
        .translate(Language.ZH_CN, "测试物品组1")
        .get();

    public static  void register() {}
}
