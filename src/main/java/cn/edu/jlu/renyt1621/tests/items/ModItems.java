package cn.edu.jlu.renyt1621.tests.items;

import cn.edu.jlu.renyt1621.reg.item.PCBlockItemRegister;
import cn.edu.jlu.renyt1621.reg.item.PCItemRegister;
import cn.edu.jlu.renyt1621.utils.constant.Language;
import net.minecraft.item.Item;
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
        .translate(Language.EN_US, "Test Item1")
        .translate(Language.ZH_CN, "测试物品1")
        .get();


    public static final Item BLOCK_ITEM = PCBlockItemRegister.create(BLOCK)
        .settings(
            new Item.Settings()
                .maxCount(16)
                .fireproof()
                .rarity(Rarity.COMMON)
        )
        .registerAndBuild()
        .get();


    public static void register() {}

}
