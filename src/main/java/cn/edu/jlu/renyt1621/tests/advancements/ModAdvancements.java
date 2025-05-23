package cn.edu.jlu.renyt1621.tests.advancements;

import cn.edu.jlu.renyt1621.register.advancement.PCAdvancement;
import cn.edu.jlu.renyt1621.tests.items.ModItems;
import cn.edu.jlu.renyt1621.utils.constant.Language;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.data.advancement.AdvancementTabGenerator;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

import static cn.edu.jlu.renyt1621.References.MOD_ID;

/**
 * Test PCAdvancements.
 * @author REN YuanTong
 * @Date 2025-05-20
 */
public final class ModAdvancements {
    public static final List<AdvancementTabGenerator> TAB_LIST = new ArrayList<>();

    public static final PCAdvancement PC_TEST_ADVANCEMENT1 =
        PCAdvancement.of(Identifier.of(MOD_ID, "pc_test_advancement1"), "pc_test_advancement1")
            .icon(ModItems.PC_ITEM1)
            .background(null)
            .frame(AdvancementFrame.TASK)
            .announce(true, true, false)
            .titleTranslation(Language.EN_US, "PiscesCup Test Advancement 1")
            .titleTranslation(Language.ZH_CN, "PiscesCup 测试进度 1")
            .descriptionTranslation(Language.EN_US, "This is a test advancement 1.")
            .descriptionTranslation(Language.ZH_CN, "这是测试进度1。")
            .rewards(AdvancementRewards.Builder.experience(1000))
            .get();

    public static final PCAdvancement PC_ADVANCEMENT_2 =
        PCAdvancement.of(Identifier.of(MOD_ID, "pc_advancement2"), "pc_advancement2")
            .icon(Items.ACACIA_BUTTON)
            .background(null)
            .frame(AdvancementFrame.CHALLENGE)
            .announce(true, true, true)
            .titleTranslation(Language.EN_US, "PiscesCup Test Advancement 2")
            .titleTranslation(Language.ZH_CN, "PiscesCup 测试进度 2")
            .descriptionTranslation(Language.EN_US, "This is a test advancement 2.")
            .descriptionTranslation(Language.ZH_CN, "这是测试进度2。")
            .rewards(AdvancementRewards.Builder.experience(200000))
            .get();

}
