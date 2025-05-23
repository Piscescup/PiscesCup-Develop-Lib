package cn.edu.jlu.renyt1621.tests.blocks;

import cn.edu.jlu.renyt1621.register.block.PCBlockRegister;
import cn.edu.jlu.renyt1621.utils.constant.Language;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Items;
import net.minecraft.util.DyeColor;

import static cn.edu.jlu.renyt1621.References.MOD_ID;

/**
 * Test blocks
 *
 * @author REN YuanTong
 * @Date 2025-04-14
 */
public class ModBlocks {
    public static final Block PC_BLOCK = PCBlockRegister.create(MOD_ID, "block1")
        .settings(AbstractBlock.Settings.create()
            .burnable()
            .mapColor(DyeColor.BROWN)
            .hardness(1.0f)
            .requiresTool()
        )
        .registerAndBuild()
        .translate(Language.EN_US, "Test Block1")
        .translate(Language.ZH_CN, "测试方块1")
        .simpleCubeAll()
        .drop(Items.DIAMOND, 18)
        .get();


    public static void register() {}
}
