package cn.edu.jlu.renyt1621.datagen.advancements;

import net.minecraft.data.advancement.AdvancementTabGenerator;
import net.minecraft.registry.RegistryWrapper;

import java.util.List;
import java.util.function.Consumer;

/**
 * <h2>Description</h2>
 * <p>
 *     This is a mark abstract class. <br>
 *     The subclass of this class must implement the method: {@link #accept} to generate advancements.
 * </p>
 * <h2>Usages</h2>
 * <blockquote><pre>
 * public final class ModTabAdvancement
 *     extends PCAdvancementTabGenerator
 * {
 *     public ModTabAdvancement() {}
 *
 *     &#64;Override
 *     public void accept(
 *         RegistryWrapper.WrapperLookup registries,
 *         Consumer<AdvancementEntry> exporter
 *      ) {
 *         AdvancementEntry ROOT = ModAdvancements.PC_TEST_ADVANCEMENT1
 *             .applyParentAndCriterion(
 *                 null,
 *                 Map.of(
 *                     "test1",
 *                     InventoryChangedCriterion.Conditions.items(Items.DIAMOND_BLOCK)
 *                 ),
 *                 exporter
 *             );
 *     }
 * }
 * </pre></blockquote>
 * @author REN YuanTong
 * @Date 2025-05-21
 * @since 1.1.0
 */ 
public abstract class PCAdvancementTabGenerator
    implements AdvancementTabGenerator
{

}
