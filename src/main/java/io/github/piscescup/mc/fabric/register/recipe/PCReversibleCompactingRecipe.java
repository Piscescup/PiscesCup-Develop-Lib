package io.github.piscescup.mc.fabric.register.recipe;

import io.github.piscescup.mc.fabric.register.recipe.list.PCReversibleRecipeList;
import net.minecraft.block.Block;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.data.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * <h2>Description</h2>
 * A reversible compacting recipe builder.
 * <h2>Usages</h2>
 * <pre>{@code
 * public static final PCReversibleCompactingRecipe BEDROCK_RECIPE =
 *     PCReversibleCompactingRecipe.createFor(
 *         RecipeCategory.BUILDING_BLOCKS,
 *         PCReversibleCompactingRecipe.ReversibleFactory.REVERSIBLE_3x3,
 *         Items.BEDROCK,
 *         ModItems.PC_BLOCK_ITEM,
 *         "pc_bedrock_reverse",
 *         "pc_bedrock_compacting"
 *     )
 *         .register();
 * }</pre>
 * @author REN YuanTong
 * @Date 2025-08-09
 * @since 1.1.2
 */
public final class PCReversibleCompactingRecipe
    extends PCRecipe<PCReversibleCompactingRecipe>
    implements Craftable<PCReversibleCompactingRecipe>
{
    private static final PCReversibleRecipeList COMPACTING_RECIPES = PCReversibleRecipeList.instance();

    private final ItemConvertible baseItem;

    private final List<String> patterns;
    private final Map<Character, Ingredient> definitions;

    private final String compactingId;
    @Nullable
    private final String compactingGroup;
    private final String reverseId;
    @Nullable
    private final String reverseGroup;


    private PCReversibleCompactingRecipe(
        RecipeCategory category, ReversibleFactory type,
        ItemConvertible baseItem, ItemConvertible compactItem,
        String reverseId, @Nullable String reverseGroup,
        String compactingId, @Nullable String compactingGroup
    ) {
        super(category, compactItem, type.count());
        this.baseItem = baseItem;
        this.patterns = type.patterns();
        this.definitions = Map.of(
            ReversibleFactory.RECIPE_REVERSIBLE_SYMBOL, Ingredient.ofItems(baseItem)
        );
        this.reverseId = reverseId;
        this.reverseGroup = reverseGroup;
        this.compactingId = compactingId;
        this.compactingGroup = compactingGroup;
    }

    public static PCReversibleCompactingRecipe createFor(
        RecipeCategory category, ReversibleFactory type,
        ItemConvertible baseItem, ItemConvertible compactItem,
        String reverseId, @Nullable String reverseGroup,
        String compactingId, @Nullable String compactingGroup
    ) {
        return new PCReversibleCompactingRecipe(
            category, type,
            baseItem, compactItem,
            reverseId, reverseGroup,
            compactingId, compactingGroup
        );
    }

    public static PCReversibleCompactingRecipe createFor(
        RecipeCategory category, ReversibleFactory type,
        ItemConvertible baseItem, ItemConvertible compactItem,
        String reverseId, String compactingId
    ) {
        return new PCReversibleCompactingRecipe(
            category, type,
            baseItem, compactItem,
            reverseId, null,
            compactingId, null
        );
    }


    public ItemConvertible getBaseItem() {
        return baseItem;
    }

    public List<String> getPatterns() {
        return patterns;
    }

    public Map<Character, Ingredient> getDefinitions() {
        return definitions;
    }

    public String getCompactingId() {
        return compactingId;
    }

    public @Nullable String getCompactingGroup() {
        return compactingGroup;
    }

    public String getReverseId() {
        return reverseId;
    }

    public @Nullable String getReverseGroup() {
        return reverseGroup;
    }


    @Override
    public ItemConvertible getTargetItem() {
        return this.target;
    }

    @Override
    public RecipeCategory getCategory() {
        return this.category;
    }

    @Override
    public int getCount() {
        return this.count;
    }

    @Override
    public PCReversibleCompactingRecipe register() {
        COMPACTING_RECIPES.add(this);
        return this;
    }

    @Override
    public PCReversibleCompactingRecipe criterionWhenEnteringFluid(@NotNull String criterionName, @NotNull Block fluid) {
        return this;
    }

    @Override
    public PCReversibleCompactingRecipe criterionWhenEnteringFluid(@NotNull Block fluid) {
        return this;
    }

    @Override
    public PCReversibleCompactingRecipe criterionFromItem(@NotNull String criterionName, @NotNull ItemConvertible item) {
        return this;
    }

    @Override
    public PCReversibleCompactingRecipe criterionFromItem(@NotNull ItemConvertible item) {
        return this;
    }

    @Override
    public PCReversibleCompactingRecipe criterionFromTag(@NotNull String criterionName, @NotNull TagKey<Item> tag) {
        return this;
    }

    @Override
    public void build(RegistryEntryLookup<Item> registryLookup, RecipeExporter recipeExporter) {
        ShapedRecipeJsonBuilder compactBuilder = ShapedRecipeJsonBuilder.create(registryLookup, this.category, this.target)
            .group(this.compactingGroup)
            .criterion(
                RecipeGenerator.hasItem(this.baseItem),
                RecipeGenerator.conditionsFromPredicates(
                    ItemPredicate.Builder.create().items(registryLookup, this.baseItem)
                )
            );
        this.patterns.forEach(compactBuilder::pattern);
        this.definitions.forEach(compactBuilder::input);
        compactBuilder.offerTo(recipeExporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(this.compactingId)));

        ShapelessRecipeJsonBuilder.create(registryLookup, this.category, this.baseItem, this.count)
            .group(this.reverseGroup)
            .input(this.target)
            .criterion(
                RecipeGenerator.hasItem(this.target),
                RecipeGenerator.conditionsFromPredicates(
                    ItemPredicate.Builder.create().items(registryLookup, this.target)
                )
            )
            .offerTo(recipeExporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(this.reverseId)));
    }

    public enum ReversibleFactory {
        REVERSIBLE_2x2(
            generatePatterns(2, 2)
        ),
        REVERSIBLE_3x3(
            generatePatterns(3, 3)
        ),
        REVERSIBLE_3x2(
            generatePatterns(3, 2)
        ),
        REVERSIBLE_2x3(
            generatePatterns(2, 3)
        )
        ;
        public static final Character RECIPE_REVERSIBLE_SYMBOL = '#';
        private final List<String> patterns;

        ReversibleFactory(List<String> patterns) {
            this.patterns = patterns;
        }

        public List<String> patterns() {
            return patterns;
        }

        public int count() {
            return patterns.stream()
                .mapToInt(String::length)
                .sum();
        }

        private static List<String> generatePatterns(int rows, int cols) {
            String symbolLine = "#".repeat(cols);
            List<String> patterns = new ArrayList<>();
            for (int i = 0; i < rows; i++) {
                patterns.add(symbolLine);
            }
            return Collections.unmodifiableList(patterns);
        }

        private static Character getSymbol() {
            return '#';
        }

    }
}
