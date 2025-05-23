package cn.edu.jlu.renyt1621.datagen.recipes;

import cn.edu.jlu.renyt1621.datagen.recipes.maps.PCShapedRecipeMap;
import cn.edu.jlu.renyt1621.datagen.recipes.maps.PCShapelessRecipeMap;
import cn.edu.jlu.renyt1621.datagen.recipes.craft.PCShapedRecipe;
import cn.edu.jlu.renyt1621.datagen.recipes.craft.PCShapelessRecipe;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.data.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * <h1>Description</h1>
 *
 * <h1>Usages</h1>
 *
 * @author REN YuanTong
 * @Date 2025-04-18
 * @since
 */
public class PCRecipeProvider
    extends FabricRecipeProvider
{
    private final Map<PCShapedRecipe, ItemConvertible> shapedRecipesMap = PCShapedRecipeMap.instance().getShapedRecipes();
    private final Map<PCShapelessRecipe, ItemConvertible> shapelessRecipesMap = PCShapelessRecipeMap.instance().getShapelessRecipeItemMap();

    public PCRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup, recipeExporter) {
            @Override
            public void generate() {
                shapedRecipesMap.forEach(
                    (shapedRecipe, item) -> {
                        RecipeCategory category = shapedRecipe.getCategory();
                        Map<Character, Ingredient> definitions = shapedRecipe.getDefinitions();
                        Map<Character, TagKey<Item>> tagDefinitions = shapedRecipe.getTagDefinitions();
                        List<String> patterns = shapedRecipe.getPattern();
                        int count = shapedRecipe.getCount();

                        Map<String, Item> criteria = shapedRecipe.getCriteria();

                        ShapedRecipeJsonBuilder shapedRecipeJsonBuilder = createShaped(category, item, count);

                        patterns.forEach(shapedRecipeJsonBuilder::pattern);
                        definitions.forEach(shapedRecipeJsonBuilder::input);
                        tagDefinitions.forEach(shapedRecipeJsonBuilder::input);
                        criteria.forEach(
                            (criterionName, criterionItem) -> shapedRecipeJsonBuilder.criterion(criterionName, conditionsFromItem(criterionItem))
                        );

                        shapedRecipeJsonBuilder.offerTo(exporter);
                    }
                );

                shapelessRecipesMap.forEach(
                    (shapelessRecipe, item) -> {
                        RecipeCategory category = shapelessRecipe.getCategory();
                        List<Ingredient> ingredients = shapelessRecipe.getIngredients();
                        List<TagKey<Item>> tagKey = shapelessRecipe.getTagKey();
                        int count = shapelessRecipe.getCount();
                        Map<String, Item> criteria = shapelessRecipe.getCriteria();
                        ShapelessRecipeJsonBuilder shapeless = createShapeless(category, item, count);

                        ingredients.forEach(shapeless::input);
                        tagKey.forEach(shapeless::input);
                        criteria.forEach(
                            (criterionName, criterionItem) -> shapeless.criterion(criterionName, conditionsFromItem(criterionItem))
                        );

                        shapeless.offerTo(exporter);
                    }
                );


            }
        };
    }

    @Override
    public String getName() {
        return "PCRecipeProvider";
    }
}
