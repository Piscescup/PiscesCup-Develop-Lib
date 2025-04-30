package cn.edu.jlu.renyt1621.datagen.recipes.craft;

import cn.edu.jlu.renyt1621.utils.CheckUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.TagKey;

import java.util.*;

/**
 * <h2>Description</h2>
 * <p>
 *     A shaped recipe class.
 * </p>
 *
 * <h2>Usages</h2>
 * <p>
 * Use the static class {@link Builder} to create a shaped recipe.
 * </p>
 *
 * <p>
 *     Below is an example of how to use the builder:
 * </p>
 *
 * <blockquote><pre>
 *     final PCShapedRecipe IRON_LEGGING_RECIPE = PCShapedRecipe.Builder.create()
 *         .category(RecipeCategory.MISC)
 *         .pattern("***")
 *         .pattern("* *")
 *         .pattern("* *")
 *         .definition('*', Items.IRON_INGOT)
 *         .criterion("has_iron_ingot", Items.IRON_INGOT)
 *         .build();
 * </pre></blockquote>
 *
 *
 * @author REN YuanTong
 * @Date 2025-04-18
 * @since 1.0.0
 */
public class PCShapedRecipe
    implements Craftable
{
    private final RecipeCategory category;

    private final List<String> pattern;
    private final Map<Character, Ingredient> definitions;
    private final Map<Character, TagKey<Item>> tagDefinitions;
    private final int count;

    private final Map<String, Item> criteria = new HashMap<>();


    private PCShapedRecipe(Builder builder) {
        this.category = builder.category;
        this.pattern = builder.pattern;
        this.count = builder.count;
        this.definitions = Map.copyOf(builder.definitions);
        this.tagDefinitions = Map.copyOf(builder.tagDefinitions);
        this.criteria.putAll(builder.criteria);

    }

    /**
     * Get the category of the recipe.
     * @return The category of the recipe.
     */
    public RecipeCategory getCategory() {
        return category;
    }

    /**
     * Get the patterns of the recipe.
     * @return The patterns of the recipe.
     */
    public List<String> getPattern() {
        return pattern;
    }

    /**
     * Get the count of the target item.
     * @return The count of the target item.
     */
    public int getCount() {
        return count;
    }

    /**
     * Get the definitions of the recipe.
     * @return The definitions of the recipe.
     */
    public Map<Character, Ingredient> getDefinitions() {
        return definitions;
    }

    /**
     * Get the tag definitions of the recipe.
     * @return The tag definitions of the recipe.
     */
    public Map<Character, TagKey<Item>> getTagDefinitions() {
        return tagDefinitions;
    }

    /**
     * Get the criterion of the recipe.<br>
     * The criterion is a {@code Map<String, Item>}
     * @return The criterion of the recipe.
     */
    public Map<String, Item> getCriteria() {
        return criteria;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PCShapedRecipe that = (PCShapedRecipe) o;
        return Objects.equals(pattern, that.pattern) &&
            Objects.equals(definitions, that.definitions) &&
            Objects.equals(tagDefinitions, that.tagDefinitions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pattern, definitions, tagDefinitions);
    }

    public static class Builder {
        private RecipeCategory category;
        private List<String> pattern = new ArrayList<>();
        private final Map<Character, Ingredient> definitions = new HashMap<>();
        private final Map<Character, TagKey<Item>> tagDefinitions = new HashMap<>();

        private Map<String, Item> criteria = new HashMap<>();

        private int count;

        private Builder() {}

        /**
         * Create a new builder.
         * @return A new builder.
         */
        public static Builder create() {
            return new Builder();
        }

        /**
         * Set the category of the recipe.
         * @param category The category of the recipe.
         * @return The PCShapedRecipe.Builder
         */
        public Builder category(RecipeCategory category) {
            CheckUtils.checkIsNullThenThrow(
                category, "Category cannot be null when using PCShapedRecipe.Builder"
            );

            this.category = category;
            return this;
        }

        /**
         * Add the patterns to the recipe patterns.
         * @param patterns The patterns of the recipe.
         * @return The PCShapedRecipe.Builder
         */
        public Builder patterns(List<String> patterns) {
            CheckUtils.checkAnyIsNullThenThrow(
                patterns, "Patterns cannot be null when using PCShapedRecipe.Builder"
            );

            this.pattern.addAll(patterns);
            return this;
        }

        /**
         * Add the given pattern to recipe patterns.
         * @param pattern The pattern of the recipe.
         * @return The PCShapedRecipe.Builder
         */
        public Builder pattern(String pattern) {
            CheckUtils.checkIsNullThenThrow(
                pattern, "Pattern cannot be null when using PCShapedRecipe.Builder"
            );

            this.pattern.add(pattern);
            return this;
        }

        /**
         * Define the {@code Ingredient} in the symbol in the patterns
         * @param symbol The character in the patterns
         * @param ingredient The ingredient represented by the symbol
         * @return The PCShapedRecipe.Builder
         */
        public Builder definition(Character symbol, Ingredient ingredient) {
            CheckUtils.checkIsNullThenThrow(
                symbol, "Symbol cannot be null when using PCShapedRecipe.Builder"
            );
            CheckUtils.checkIsNullThenThrow(
                ingredient, "Ingredient cannot be null when using PCShapedRecipe.Builder"
            );
            this.definitions.put(symbol, ingredient);
            return this;
        }

        /**
         * Define the tag represented the symbol in patterns
         * @param symbol The character in the patterns
         * @param tag The tag represented the symbol
         * @return The PCShapedRecipe.Builder
         */
        public Builder definition(Character symbol, TagKey<Item> tag) {
            CheckUtils.checkIsNullThenThrow(
                symbol, "Symbol cannot be null when using PCShapedRecipe.Builder"
            );
            CheckUtils.checkIsNullThenThrow(
                tag, "Tag cannot be null when using PCShapedRecipe.Builder"
            );
            this.tagDefinitions.put(symbol, tag);
            return this;
        }

        /**
         * Define the item represented the symbol in patterns
         * @param symbol The character in the patterns
         * @param item The item represented the symbol
         * @return The PCShapedRecipe.Builder
         */
        public Builder definition(Character symbol, ItemConvertible item) {
            CheckUtils.checkIsNullThenThrow(
                symbol, "Symbol cannot be null when using PCShapedRecipe.Builder"
            );
            CheckUtils.checkIsNullThenThrow(
                item, "Item cannot be null when using PCShapedRecipe.Builder"
            );
            return definition(symbol, Ingredient.ofItem(item));
        }

        /**
         * Add the criterion to the recipe.
         * @param criterionName The name of the criterion
         * @param item The item for building a {@code AdvancementCriterion}
         * @return The PCShapedRecipe.Builder
         */
        public Builder criterion(String criterionName, Item item) {
            CheckUtils.checkIsNullThenThrow(
                criterionName, "Criterion Name cannot be null when using PCShapedRecipe.Builder"
            );
            CheckUtils.checkIsNullThenThrow(
                item, "Criterion cannot be null when using PCShapedRecipe.Builder"
            );
            this.criteria.put(criterionName, item);

            return this;
        }

        /**
         * Set the count of the target item.
         * @param count The count of the target item.
         * @return The PCShapedRecipe.Builder
         */
        public Builder count(int count) {
            this.count = count;
            return this;
        }

        /**
         * Generate the PCShapedRecipe.
         * @return The PCShapedRecipe.
         */
        public PCShapedRecipe build() {
            if (this.criteria.isEmpty())
                throw new IllegalArgumentException(
                    "Must add some criterion before building the PCShapedRecipe. Use the method criterion() to add them."
                );

            return new PCShapedRecipe(this);
        }
    }
}
