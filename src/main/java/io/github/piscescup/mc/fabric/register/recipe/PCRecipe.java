package io.github.piscescup.mc.fabric.register.recipe;

import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.tag.TagKey;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

/**
 * A base class for recipes.
 *
 * @author REN YuanTong
 * @Date 2025-08-13
 * @since 1.1.2
 */
public abstract sealed class PCRecipe<T extends PCRecipe<T>>
    permits PCShapedRecipe, PCShapelessRecipe, PCReversibleCompactingRecipe
{

    protected final RecipeCategory category;
    protected final ItemConvertible target;
    protected final int count;
    protected String criterionName;
    protected Function<RegistryEntryLookup<Item>, AdvancementCriterion<?>> criterionCreator;

    protected PCRecipe(RecipeCategory category, ItemConvertible target, int count) {
        this.category = category;
        this.target = target;
        this.count = count;
    }

    @SuppressWarnings("unchecked")
    public T criterionWhenEnteringFluid(@NotNull String criterionName, @NotNull Block fluid) {
        this.criterionName = criterionName;
        this.criterionCreator =
            itemLookup -> Craftable.ConditionFactory.requireEnteringFluid(fluid);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T criterionWhenEnteringFluid(@NotNull Block fluid) {
        this.criterionName = enterFluidName(fluid);
        this.criterionCreator =
            itemLookup -> Craftable.ConditionFactory.requireEnteringFluid(fluid);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T criterionFromItem(@NotNull String criterionName, @NotNull ItemConvertible item) {
        this.criterionName = criterionName;
        this.criterionCreator =
            itemLookUp -> Craftable.ConditionFactory.conditionsFromItem(itemLookUp, item);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T criterionFromItem(@NotNull ItemConvertible item) {
        this.criterionName = hasItem(item);
        this.criterionCreator =
            itemLookUp -> Craftable.ConditionFactory.conditionsFromItem(itemLookUp, item);
        return (T) this;
    }

    public T criterionFromTag(@NotNull String criterionName, @NotNull TagKey<Item> tag) {
        this.criterionName = criterionName;
        this.criterionCreator =
            itemLookUp -> Craftable.ConditionFactory.conditionsFromTag(itemLookUp, tag);
        return (T) this;
    }

    protected String enterFluidName(Block fluid) {
        return "in_" + Registries.ITEM.getId(fluid.asItem()).getPath();
    }

    protected String hasItem(ItemConvertible item) {
        return Registries.ITEM.getId(item.asItem()).getPath();
    }

}
