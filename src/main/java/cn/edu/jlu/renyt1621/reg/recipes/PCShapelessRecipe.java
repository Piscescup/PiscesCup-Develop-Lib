package cn.edu.jlu.renyt1621.reg.recipes;

import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.book.RecipeCategory;

import java.util.List;
import java.util.Objects;

/**
 * <h1>Description</h1>
 *
 * <h1>Usages</h1>
 *
 * @author REN YuanTong
 * @Date 2025-04-18
 * @since 1.0.0
 */
public class PCShapelessRecipe {
    private final RecipeCategory category;
    private final List<ItemConvertible> ingredients;
    private final int count;

    private PCShapelessRecipe(Builder builder) {
        this.category = builder.category;
        this.ingredients = builder.ingredients;
        this.count = builder.count;
    }

    public RecipeCategory getCategory() {
        return category;
    }

    public List<ItemConvertible> getIngredients() {
        return ingredients;
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
        private List<ItemConvertible> ingredients;
        private int count;

        private Builder() {}

        public static Builder create() {
            return new Builder();
        }

        public Builder category(RecipeCategory category) {
            this.category = category;
            return this;
        }

        public Builder input(List<ItemConvertible> ingredients) {
            this.ingredients = ingredients;
            return this;
        }


        public Builder count(int count) {
            this.count = count;
            return this;
        }

        public PCShapelessRecipe build() {
            return new PCShapelessRecipe(this);
        }

    }
}
