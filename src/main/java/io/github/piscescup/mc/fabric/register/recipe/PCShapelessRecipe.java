package io.github.piscescup.mc.fabric.register.recipe;

import io.github.piscescup.mc.fabric.register.recipe.list.PCShapelessRecipeList;
import io.github.piscescup.mc.fabric.utils.CheckUtils;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.tag.TagKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <h2>Description</h2>
 * <p>
 *     A shaped recipe class.
 * </p>
 *
 * <h2>Usages</h2>
 * <p>
 * Use the static method {@link #createFor} to create a shapeless recipe.
 * </p>
 *
 * <p>
 *     Below is an example of how to use the builder:
 * </p>
 *
 * <blockquote><pre>
 * public static final PCShapelessRecipe PC_ITEM2_RECIPE =
 *     PCShapelessRecipe.createFor(RecipeCategory.BUILDING_BLOCKS, ModItems.PC_ITEM2, 4)
 *         .input(ItemTags.PLANKS)
 *         .input(ItemTags.BUTTONS)
 *         .input(Items.BEDROCK)
 *         .criterionFromItem("has_iron_ingot", Items.IRON_INGOT)
 *         .register();
 * </pre></blockquote>
 *
 *
 * @author REN YuanTong
 * @Date 2025-04-18
 * @since 1.0.0
 */
public final class PCShapelessRecipe
    extends PCRecipe<PCShapelessRecipe>
    implements Craftable<PCShapelessRecipe>
{
    private static final PCShapelessRecipeList SHAPELESS_RECIPES = PCShapelessRecipeList.instance();

    private final List<Ingredient> ingredients;
    private final List<TagKey<Item>> tagItem;

    @Nullable
    private String group;

    private PCShapelessRecipe(ItemConvertible targetItem, RecipeCategory category, int count) {
        super(category, targetItem, count);
        this.ingredients = new ArrayList<>();
        this.tagItem = new ArrayList<>();
    }

    public static PCShapelessRecipe createFor(RecipeCategory category, ItemConvertible targetItem, int count) {
        return new PCShapelessRecipe(targetItem, category, count);
    }

    public PCShapelessRecipe input(@NotNull ItemConvertible item) {
        CheckUtils.NullChecker.nonNull(item);
        ingredients.add(Ingredient.ofItem(item));
        return this;
    }


    public PCShapelessRecipe input(@NotNull Ingredient ingredient) {
        CheckUtils.NullChecker.nonNull(ingredient);
        ingredients.add(ingredient);
        return this;
    }

    public PCShapelessRecipe input(@NotNull TagKey<Item> tag) {
        CheckUtils.NullChecker.nonNull(tag);
        tagItem.add(tag);
        return this;
    }

    public PCShapelessRecipe group(@Nullable String group) {
        this.group = group;
        return this;
    }

    @Override
    public ItemConvertible getTargetItem() {
        return target;
    }


    @Override
    public RecipeCategory getCategory() {
        return category;
    }

    @Override
    public int getCount() {
        return count;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<TagKey<Item>> getTagKey() {
        return tagItem;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PCShapelessRecipe that = (PCShapelessRecipe) o;
        return Objects.equals(ingredients, that.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ingredients);
    }

    @Override
    public PCShapelessRecipe register() {
        SHAPELESS_RECIPES.add(this);
        return this;
    }

    @Override
    public void build(RegistryEntryLookup<Item> registryLookup, RecipeExporter recipeExporter) {
        ShapelessRecipeJsonBuilder shapelessBuilder = ShapelessRecipeJsonBuilder.create(registryLookup, category, target, count)
            .group(group)
            .criterion(this.criterionName, criterionCreator.apply(registryLookup));

        this.ingredients.forEach(shapelessBuilder::input);
        this.tagItem.forEach(shapelessBuilder::input);

        shapelessBuilder.offerTo(recipeExporter);
    }
}
