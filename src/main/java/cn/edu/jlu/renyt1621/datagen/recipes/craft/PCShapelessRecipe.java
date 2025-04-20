package cn.edu.jlu.renyt1621.datagen.recipes.craft;

import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.TagKey;

import java.util.*;

/**
 * @author REN YuanTong
 * @Date 2025-04-18
 * @since 1.0.0
 */
public class PCShapelessRecipe {
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
        private RecipeCategory category;
        private List<Ingredient> ingredients = new ArrayList<>();
        private List<TagKey<Item>> tagItem = new ArrayList<>();
        private int count;

        private final Map<String, Item> criteria = new HashMap<>();

        private Builder() {}

        public static Builder create() {
            return new Builder();
        }

        public Builder category(RecipeCategory category) {
            if (category == null) throw new IllegalArgumentException("Recipe Category cannot be null when using PCShapelessRecipe.Builder");
            this.category = category;
            return this;
        }

        public Builder input(Ingredient ingredient) {
            if (ingredient == null) throw new IllegalArgumentException("Ingredient cannot be null when using PCShapelessRecipe.Builder");
            this.ingredients.add(ingredient);
            return this;
        }

        public Builder input(Ingredient... ingredients) {
            if (ingredients == null)
                throw new IllegalArgumentException("Ingredients cannot be null when using PCShapelessRecipe.Builder");

            boolean anyIsNull = Arrays.stream(ingredients)
                .anyMatch(Objects::isNull);
            if (anyIsNull)
                throw new IllegalArgumentException("Ingredient cannot contain null when using PCShapelessRecipe.Builder");

            this.ingredients.addAll(Arrays.asList(ingredients));

            return this;
        }

        public Builder input(ItemConvertible item) {
            if (item == null) throw new IllegalArgumentException("Ingredient cannot be null when using PCShapelessRecipe.Builder");
            this.ingredients.add(Ingredient.ofItem(item));
            return this;
        }

        public Builder input(ItemConvertible... items) {
            boolean anyIsNull = Arrays.stream(items)
                .anyMatch(Objects::isNull);
            if (anyIsNull) throw new IllegalArgumentException("Ingredient cannot be null when using PCShapelessRecipe.Builder");
            Arrays.stream(items)
                .map(Ingredient::ofItem)
                .forEach(this.ingredients::add);

            return this;
        }

        public Builder input(List<ItemConvertible> items) {
            if (items == null) throw new IllegalArgumentException("Ingredients cannot be null when using PCShapelessRecipe.Builder");
            boolean anyIsNull = items.stream()
                .anyMatch(Objects::isNull);
            if (anyIsNull) throw new IllegalArgumentException("Ingredient cannot be null when using PCShapelessRecipe.Builder");

            items.stream()
                .map(Ingredient::ofItem)
                .forEach(this.ingredients::add);

            return this;
        }

        public Builder input(TagKey<Item> tag) {
            if (tag == null) throw new IllegalArgumentException("Tag cannot be null when using PCShapelessRecipe.Builder");
            this.tagItem.add(tag);
            return this;
        }

        public Builder count(int count) {
            this.count = count;
            return this;
        }

        public Builder criterion(String criterionName, Item item) {
            if (criterionName == null) throw new IllegalArgumentException("Criterion name cannot be null when using PCShapelessRecipe.Builder");
            if (item == null) throw new IllegalArgumentException("Item cannot be null when using PCShapelessRecipe.Builder");
            this.criteria.put(criterionName, item);
            return this;
        }

        public PCShapelessRecipe build() {
            if (this.criteria.isEmpty())
                throw new IllegalArgumentException(
                    "Must add some criterion before building the PCShapedRecipe. Use the method criterion() to add them."
                );

            return new PCShapelessRecipe(this);
        }

    }
}
