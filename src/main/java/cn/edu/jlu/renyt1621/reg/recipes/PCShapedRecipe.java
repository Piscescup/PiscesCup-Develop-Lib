package cn.edu.jlu.renyt1621.reg.recipes;

import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.TagKey;

import java.util.*;

/**
 * <h1>Description</h1>
 *
 * <h1>Usages</h1>
 *
 * @author REN YuanTong
 * @Date 2025-04-18
 * @since 1.0.0
 */
public class PCShapedRecipe {
    private final RecipeCategory category;

    private final List<String> pattern;
    private final Map<Character, Ingredient> definitions;
    private final Map<Character, TagKey<Item>> tagDefinitions;
    private final int count;

    private Map<String, Item> criteria = new HashMap<>();


    private PCShapedRecipe(Builder builder) {
        this.category = builder.category;
        this.pattern = builder.pattern;
        this.count = builder.count;
        this.definitions = Map.copyOf(builder.definitions);
        this.tagDefinitions = Map.copyOf(builder.tagDefinitions);
        this.criteria = builder.criteria;
    }

    public RecipeCategory getCategory() {
        return category;
    }

    public List<String> getPattern() {
        return pattern;
    }

    public int getCount() {
        return count;
    }

    public Map<Character, Ingredient> getDefinitions() {
        return definitions;
    }

    public Map<Character, TagKey<Item>> getTagDefinitions() {
        return tagDefinitions;
    }

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

        public static Builder create() {
            return new Builder();
        }

        public Builder category(RecipeCategory category) {
            if (category == null) throw new IllegalArgumentException("Recipe Category cannot be null when using PCShapedRecipe.Builder");
            this.category = category;
            return this;
        }

        public Builder patterns(List<String> pattern) {
            if (pattern == null) throw new IllegalArgumentException("Patterns cannot be null when using PCShapedRecipe.Builder");
            this.pattern = pattern;
            return this;
        }

        public Builder pattern(String pattern) {
            if (pattern == null) throw new IllegalArgumentException("Pattern cannot be null when using PCShapedRecipe.Builder");
            this.pattern.add(pattern);
            return this;
        }

        public Builder definition(Character symbol, Ingredient ingredient) {
            if (symbol == null) throw new IllegalArgumentException("Symbol cannot be null when using PCShapedRecipe.Builder");
            if (ingredient == null) throw new IllegalArgumentException("Ingredient cannot be null when using PCShapedRecipe.Builder");
            this.definitions.put(symbol, ingredient);
            return this;
        }

        public Builder definition(Character symbol, TagKey<Item> tag) {
            if (symbol == null) throw new IllegalArgumentException("Symbol cannot be null when using PCShapedRecipe.Builder");
            if (tag == null) throw new IllegalArgumentException("Tag cannot be null");
            this.tagDefinitions.put(symbol, tag);
            return this;
        }

        public Builder definition(Character symbol, ItemConvertible item) {
            if (symbol == null) throw new IllegalArgumentException("Symbol cannot be null when using PCShapedRecipe.Builder");
            return definition(symbol, Ingredient.ofItem(item));
        }

        public Builder criterion(String criterionName, Item item) {
            if (criterionName == null) throw new IllegalArgumentException("Criterion Name cannot be null when using PCShapedRecipe.Builder");
            if (item == null) throw new IllegalArgumentException("Criterion cannot be null when using PCShapedRecipe.Builder");

            this.criteria.put(criterionName, item);

            return this;
        }

        public Builder count(int count) {
            this.count = count;
            return this;
        }

        public PCShapedRecipe build() {
            return new PCShapedRecipe(this);
        }
    }
}
