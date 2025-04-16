package cn.edu.jlu.renyt1621.tests.blocks;

import cn.edu.jlu.renyt1621.reg.block.PCBlockRegister;
import cn.edu.jlu.renyt1621.utils.constant.Language;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.util.DyeColor;

import static cn.edu.jlu.renyt1621.References.MOD_ID;

/**
 * @author REN YuanTong
 * @Description
 * @Date 2025-04-14
 * @Time 15:04
 */
public class ModBlocks {
    public static final Block BLOCK = PCBlockRegister.create(MOD_ID, "block")
        .settings(
            AbstractBlock.Settings.create()
                .burnable()
                .mapColor(DyeColor.BROWN)
        )
        .registerAndBuild()
        .translate(Language.EN_US, "Test Block1")
        .translate(Language.ZH_CN, "测试方块1")
        .get();

    public static void register() {}
}
