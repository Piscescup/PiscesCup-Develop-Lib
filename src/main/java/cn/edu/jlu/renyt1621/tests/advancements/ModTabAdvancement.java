package cn.edu.jlu.renyt1621.tests.advancements;

import cn.edu.jlu.renyt1621.datagen.advancements.PCAdvancementTabGenerator;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;

import java.util.Map;
import java.util.function.Consumer;

/**
 * Test Advancement Tab
 *
 * @author REN YuanTong
 * @Date 2025-05-22
 */
public final class ModTabAdvancement
    extends PCAdvancementTabGenerator
{
    public ModTabAdvancement() {}

    @Override
    public void accept(RegistryWrapper.WrapperLookup registries, Consumer<AdvancementEntry> exporter) {
        AdvancementEntry ROOT = ModAdvancements.PC_TEST_ADVANCEMENT1
            .applyParentAndCriterion(
                null,
                Map.of("test1", InventoryChangedCriterion.Conditions.items(Items.DIAMOND_BLOCK)),
                exporter
            );
    }
}
