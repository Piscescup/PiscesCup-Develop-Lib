package cn.edu.jlu.renyt1621.datagen.recipes.craft;

import cn.edu.jlu.renyt1621.utils.CheckUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.TagKey;

import javax.annotation.Nullable;
import java.util.*;

/**
 * <h2>Description</h2>
 * <p>
 *     A shaped recipe class.
 * </p>
 *
 * <h2>Usages</h2>
 * <p>
 * Use the static class {@link PCShapelessRecipe.Builder} to create a shapeless recipe.
 * </p>
 *
 * <p>
 *     Below is an example of how to use the builder:
 * </p>
 *
 * <blockquote><pre>
 * public static final PCShapelessRecipe PC_SHAPELESS_RECIPE =
 *     PCShapelessRecipe.Builder.create()
 *          .category(RecipeCategory.BUILDING_BLOCKS)
 *          .input(ItemTags.PLANKS)
 *          .input(ItemTags.BUTTONS)
 *          .input(Items.IRON_INGOT)
 *          .count(4)
 *          .criterion("has_iron_ingot", Items.IRON_INGOT)
 *          .build()
 * </pre></blockquote>
 *
 *
 * @author REN YuanTong
 * @Date 2025-04-18
 * @since 1.0.0
 */
public class PCShapelessRecipe
    implements Craftable
{
    @Nullable
    private ItemConvertible targetItem;

    private final RecipeCategory category;
    private final List<Ingredient> ingredients = new ArrayList<>();
    private final List<TagKey<Item>> tagItem = new ArrayList<>();
    private final int count;

    private final Map<String, Item> criteria = new HashMap<>();

    private PCShapelessRecipe(Builder builder) {
        this.category = builder.category;
        this.ingredients.addAll(builder.ingredients);
        this.tagItem.addAll(builder.tagItem);
        this.criteria.putAll(builder.criteria);
        this.count = builder.count;
    }

    public RecipeCategory getCategory() {
        return category;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<TagKey<Item>> getTagKey() {
        return tagItem;
    }

    public Map<String, Item> getCriteria() {
        return criteria;
    }

    public int getCount() {
        return count;
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

    public static class Builder {
        @Nullable
        private ItemConvertible targetItem;
        private RecipeCategory category;
        private List<Ingredient> ingredients = new ArrayList<>();
        private List<TagKey<Item>> tagItem = new ArrayList<>();
        private int count;

        private final Map<String, Item> criteria = new HashMap<>();

        private Builder() {}

        /**
         * Create a PCShapelessRecipe.Builder
         */
        public static Builder create() {
            return new Builder();
        }

        /**
         * Create a PCShapelessRecipe.Builder for the given {@link ItemConvertible}
         * @param targetItem The item to create a PCShapelessRecipe for.
         */
        public static Builder createFor(ItemConvertible targetItem) {
            CheckUtils.checkIsNullThenThrow(
                targetItem, "Target Item cannot be null when creating PCShapelessRecipe.Builder"
            );
            Builder builder = new Builder();
            builder.targetItem = targetItem;
            return builder;
        }

        /**
         * Set the recipe category.
         * @param category The category of the recipe.
         */
        public Builder category(RecipeCategory category) {
            CheckUtils.checkIsNullThenThrow(
                category, "Recipe Category cannot be null when using PCShapelessRecipe.Builder"
            );

            this.category = category;
            return this;
        }

        /**
         * Add an ingredient to the recipe.
         * @param ingredient The ingredient to add.
         */
        public Builder input(Ingredient ingredient) {
            CheckUtils.checkIsNullThenThrow(
                ingredient, "Ingredient cannot be null when using PCShapelessRecipe.Builder"
            );
            this.ingredients.add(ingredient);
            return this;
        }

        /**
         * Add multiple ingredients to the recipe.
         * @param ingredients The ingredients to add.
         */
        public Builder input(Ingredient... ingredients) {
            List<Ingredient> ingredientList = Arrays.asList(ingredients);
            CheckUtils.checkAnyIsNullThenThrow(
                ingredientList, "Ingredients cannot be null when using PCShapelessRecipe.Builder"
            );

            this.ingredients.addAll(ingredientList);

            return this;
        }

        /**
         * Add an item to the recipe.
         * @param item The item to add.
         */
        public Builder input(ItemConvertible item) {
            CheckUtils.checkIsNullThenThrow(
                item, "Item cannot be null when using PCShapelessRecipe.Builder"
            );

            this.ingredients.add(Ingredient.ofItem(item));
            return this;
        }

        /**
         * Add multiple items to the recipe.
         * @param items The items to add.
         */
        public Builder input(ItemConvertible... items) {
            List<Ingredient> itemList = Arrays.stream(items)
                .map(Ingredient::ofItem)
                .toList();
            CheckUtils.checkAnyIsNullThenThrow(
                Collections.singletonList(itemList), "Items cannot be null when using PCShapelessRecipe.Builder"
            );

            this.ingredients.addAll(itemList);
            return this;
        }

        /**
         * Add a list of items to the recipe.
         * @param items The list of items to add.
         */
        public Builder input(List<ItemConvertible> items) {
            CheckUtils.checkAnyIsNullThenThrow(
                Collections.singletonList(items), "Items cannot be null when using PCShapelessRecipe.Builder"
            );

            items.stream()
                .map(Ingredient::ofItem)
                .forEach(this.ingredients::add);

            return this;
        }

        /**
         * Add A class of items to the recipe.
         * @param tag The class of the item.
         */
        public Builder input(TagKey<Item> tag) {
            CheckUtils.checkIsNullThenThrow(
                tag, "Tag cannot be null when using PCShapelessRecipe.Builder"
            );
            this.tagItem.add(tag);
            return this;
        }

        /**
         * The count of the target item
         * @param count The count of the target item.
         */
        public Builder count(int count) {
            this.count = count;
            return this;
        }

        /**
         * Add a criterion to the recipe.
         * @param criterionName The name of the criterion.
         * @param item The item which the criterion needs.
         */
        public Builder criterion(String criterionName, Item item) {
            CheckUtils.checkIsNullThenThrow(
                criterionName, "Criterion Name cannot be null when using PCShapelessRecipe.Builder"
            );

            CheckUtils.checkIsNullThenThrow(
                item, "Criterion cannot be null when using PCShapelessRecipe.Builder"
            );
            this.criteria.put(criterionName, item);
            return this;
        }

        /**
         * Build the PCShapelessRecipe.
         */
        public PCShapelessRecipe build() {
            if (this.criteria.isEmpty())
                throw new IllegalArgumentException(
                    "Must add some criterion before building the PCShapedRecipe. Use the method criterion() to add them."
                );

            return new PCShapelessRecipe(this);
        }

    }
}
