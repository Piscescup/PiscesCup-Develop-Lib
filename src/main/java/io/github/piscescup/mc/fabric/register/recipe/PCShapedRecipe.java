package io.github.piscescup.mc.fabric.register.recipe;

import io.github.piscescup.mc.fabric.register.recipe.list.PCShapedRecipeList;
import io.github.piscescup.mc.fabric.utils.CheckUtils;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.tag.TagKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <h2>Description</h2>
 * <p>
 *     A shaped recipe class.
 * </p>
 *
 * <h2>Usages</h2>
 * <p>
 * Use the static method {@link #createFor} to create a shaped recipe.
 * </p>
 *
 * <p>
 *     Below is an example of how to use the builder:
 * </p>
 *
 * <blockquote><pre>
 * public static final PCShapedRecipe PC_ITEM1_RECIPE =
 *     PCShapedRecipe.createFor(RecipeCategory.BUILDING_BLOCKS, ModItems.PC_ITEM1, 4)
 *         .pattern("***")
 *         .pattern("***")
 *         .pattern(" # ")
 *         .definition('*', Items.BEDROCK)
 *         .definition('#', Items.ACACIA_BUTTON)
 *         .criterionFromItem(Items.BEDROCK)
 *         .register();
 * </pre></blockquote>
 *
 *
 * @author REN YuanTong
 * @Date 2025-04-18
 * @since 1.0.0
 */
public final class PCShapedRecipe
    extends PCRecipe<PCShapedRecipe>
    implements Craftable<PCShapedRecipe>
{
    private static final PCShapedRecipeList SHAPED_RECIPES = PCShapedRecipeList.instance();

    private final List<String> patterns;
    private final Map<Character, Ingredient> definitions;
    private final Map<Character, TagKey<Item>> tagDefinitions;


    @Nullable
    private String group;


    private PCShapedRecipe(RecipeCategory category, ItemConvertible target, int count) {
        super(category, target, count);
        this.patterns = new ArrayList<>();
        this.definitions = new ConcurrentHashMap<>();
        this.tagDefinitions = new ConcurrentHashMap<>();
    }


    public static PCShapedRecipe createFor(RecipeCategory category, Item target, int count) {
        return new PCShapedRecipe(category, target, count);
    }

    public PCShapedRecipe pattern(@NotNull String pattern) {
        CheckUtils.NullChecker.nonNull(pattern, "pattern");
        this.patterns.add(pattern);
        return this;
    }

    public PCShapedRecipe patterns(@NotNull List<String> patterns) {
        CheckUtils.NullChecker.nonNullElements(patterns, "patterns");
        this.patterns.addAll(patterns);
        return this;
    }

    public PCShapedRecipe patterns(@NotNull String... patterns) {
        CheckUtils.NullChecker.nonNullElements(patterns, "patterns");
        this.patterns.addAll(Arrays.asList(patterns));
        return this;
    }

    public PCShapedRecipe definition(@NotNull Character key, @NotNull Ingredient ingredient) {
        CheckUtils.NullChecker.nonNull(key, "Definition key");
        CheckUtils.NullChecker.nonNull(ingredient, "Definition ingredient");
        this.definitions.put(key, ingredient);
        return this;
    }


    public PCShapedRecipe definition(@NotNull Character key, @NotNull ItemConvertible item) {
        CheckUtils.NullChecker.nonNull(key, "Definition key");
        CheckUtils.NullChecker.nonNull(item, "Definition item");
        this.definitions.put(key, Ingredient.ofItem(item));
        return this;
    }

    public PCShapedRecipe definition(@NotNull Character key, @NotNull TagKey<Item> tag) {
        CheckUtils.NullChecker.nonNull(key, "Definition key");
        CheckUtils.NullChecker.nonNull(tag, "Definition tag");
        this.tagDefinitions.put(key, tag);
        return this;
    }

    public PCShapedRecipe group(@Nullable String group) {
        CheckUtils.NullChecker.nonNull(group, "group");
        this.group = group;
        return this;
    }

    /**
     * Get the category of the recipe.
     * @return The category of the recipe.
     */
    @Override
    public RecipeCategory getCategory() {
        return category;
    }

    @Override
    public ItemConvertible getTargetItem() {
        return target;
    }

    /**
     * Get the patterns of the recipe.
     * @return The patterns of the recipe.
     */
    public List<String> getPattern() {
        return patterns;
    }

    /**
     * Get the count of the target item.
     * @return The count of the target item.
     */
    @Override
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


    @Override
    public boolean equals(Object other) {
        if (other == null || getClass() != other.getClass()) return false;
        PCShapedRecipe that = (PCShapedRecipe) other;
        return Objects.equals(patterns, that.patterns) &&
            Objects.equals(definitions, that.definitions) &&
            Objects.equals(tagDefinitions, that.tagDefinitions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patterns, definitions, tagDefinitions);
    }

    @Override
    public PCShapedRecipe register() {
        SHAPED_RECIPES.add(this);
        return this;
    }

    @Override
    public void build(RegistryEntryLookup<Item> registryLookup, RecipeExporter recipeExporter) {
        ShapedRecipeJsonBuilder shapedBuilder = ShapedRecipeJsonBuilder.create(registryLookup, this.category, this.target, this.count)
            .group(this.group)
            .criterion(this.criterionName, this.criterionCreator.apply(registryLookup));

        this.patterns.forEach(shapedBuilder::pattern);
        this.definitions.forEach(shapedBuilder::input);
        this.tagDefinitions.forEach(shapedBuilder::input);

        shapedBuilder.offerTo(recipeExporter);
    }
}
