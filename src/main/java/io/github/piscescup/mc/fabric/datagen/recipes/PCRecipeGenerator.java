package io.github.piscescup.mc.fabric.datagen.recipes;

import io.github.piscescup.mc.fabric.datagen.factories.option.DataGenFactoryOption;
import io.github.piscescup.mc.fabric.register.recipe.list.PCReversibleRecipeList;
import io.github.piscescup.mc.fabric.register.recipe.list.PCShapedRecipeList;
import io.github.piscescup.mc.fabric.register.recipe.list.PCShapelessRecipeList;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

/**
 * A data generator for recipes.
 * <p>
 * The method {@link #pcDevBuildInGenerate()} is used to generate the recipes in the mod {@code PCDevelopLib}.
 * <p>
 *     The method {@link #custom()} is used to generate the recipes defined by the subclass.
 *
 * <h2>Usage</h2>
 * <p>
 *     Below is a simple usage:
 * </p>
 * <pre>{@code
 * public class PiscesCupDevelopLibDataGenerator implements DataGeneratorEntrypoint {
 *    @Override
 *    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
 * 		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
 *
 * 		DataGeneratorFactoryBuilder.create()
 * 			.languages(Language.EN_US, Language.ZH_CN)
 * 			.tagKeys(
 * 				PCTagKeyContainerList.Option.ALL_TAG_LIST
 * 			)
 * 			.lootTable(PCLootTableProviderOption.BLOCK_LOOT_TABLE_PROVIDER)
 * 			.advancement()
 *             .recipes(PCRecipeGenerator::new)
 * 			.custom(PCModelProvider::new)
 * 			.buildAndAddToPack(pack);
 *
 *    }
 * }
 * }</pre>
 *
 * @author REN YuanTong
 * @Date 2025-08-10
 * @since 1.1.2
 */
public class PCRecipeGenerator
    extends RecipeGenerator
    implements DataGenFactoryOption
{
    private static final PCShapedRecipeList SHAPED_RECIPES = PCShapedRecipeList.instance();
    private static final PCShapelessRecipeList SHAPELESS_RECIPES = PCShapelessRecipeList.instance();
    private static final PCReversibleRecipeList COMPACTING_RECIPES = PCReversibleRecipeList.instance();

    public PCRecipeGenerator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
        super(registries, exporter);
    }

    @Override
    public void generate() {
        pcDevBuildInGenerate();
        custom();
    }

    protected void custom() {
        // TODO: Users customize their own recipes.
    }

    private void pcDevBuildInGenerate() {
        RegistryWrapper.Impl<Item> itemLookUp = this.registries.getOrThrow(RegistryKeys.ITEM);
        SHAPED_RECIPES.forEach(
            recipe -> recipe.build(
                itemLookUp,
                this.exporter
            )
        );

        SHAPELESS_RECIPES.forEach(
            recipe -> recipe.build(
                itemLookUp,
                this.exporter
            )
        );

        COMPACTING_RECIPES.forEach(
            recipe -> recipe.build(
                itemLookUp,
                this.exporter
            )
        );
    }

    @Override
    public String getDataGeneratorOptionFullName() {
        return DataGenFactoryOption.APPLY_FACTORY_OPTION.apply("Recipe");
    }
}
