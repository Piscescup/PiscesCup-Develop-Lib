package io.github.piscescup.mc.fabric.register.recipe;

import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.advancement.criterion.EnterBlockCriterion;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.block.Block;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.tag.TagKey;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * <h1>Description</h1>
 *
 * <h1>Usages</h1>
 *
 * @author REN YuanTong
 * @Date 2025-04-18
 * @since 1.0.0
 */
public interface Craftable<T> {
    ItemConvertible getTargetItem();

    RecipeCategory getCategory();

    int getCount();

    T register();

    void build(RegistryEntryLookup<Item> registryLookup, RecipeExporter recipeExporter);

    interface ConditionFactory {

        public static AdvancementCriterion<EnterBlockCriterion.Conditions> requireEnteringFluid(Block block) {
            return Criteria.ENTER_BLOCK.create(
                new EnterBlockCriterion.Conditions(
                    Optional.empty(),
                    Optional.of(block.getRegistryEntry()),
                    Optional.empty()
                )
            );
        }

        public static AdvancementCriterion<InventoryChangedCriterion.Conditions> conditionsFromItem(RegistryEntryLookup<Item> itemLookup, NumberRange.IntRange count, ItemConvertible item) {
            return conditionsFromPredicates(
                ItemPredicate.Builder.create()
                    .items(itemLookup, item)
                    .count(count)
            );
        }

        public static AdvancementCriterion<InventoryChangedCriterion.Conditions> conditionsFromItem(RegistryEntryLookup<Item> itemLookup,ItemConvertible item) {
            return conditionsFromPredicates(
                ItemPredicate.Builder.create()
                    .items(itemLookup, item)
            );
        }

        public static AdvancementCriterion<InventoryChangedCriterion.Conditions> conditionsFromTag(RegistryEntryLookup<Item> itemLookup,TagKey<Item> tag) {
            return conditionsFromPredicates(
                ItemPredicate.Builder.create()
                    .tag(itemLookup, tag)
            );
        }

        public static AdvancementCriterion<InventoryChangedCriterion.Conditions> conditionsFromPredicates(ItemPredicate.Builder... predicates) {
            return conditionsFromItemPredicates(
                (ItemPredicate[]) Arrays.stream(predicates)
                    .map(ItemPredicate.Builder::build)
                    .toArray(ItemPredicate[]::new)
            );
        }

        public static AdvancementCriterion<InventoryChangedCriterion.Conditions> conditionsFromItemPredicates(ItemPredicate... predicates) {
            return Criteria.INVENTORY_CHANGED
                .create(
                    new InventoryChangedCriterion.Conditions(
                        Optional.empty(),
                        InventoryChangedCriterion.Conditions.Slots.ANY,
                        List.of(predicates)
                    )
                );
        }

    }
}
