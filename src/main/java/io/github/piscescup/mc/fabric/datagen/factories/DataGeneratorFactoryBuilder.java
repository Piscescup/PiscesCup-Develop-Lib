package io.github.piscescup.mc.fabric.datagen.factories;

import io.github.piscescup.mc.fabric.datagen.factories.option.DataGenFactoryOption;
import io.github.piscescup.mc.fabric.datagen.recipes.PCRecipeGenerator;
import io.github.piscescup.mc.fabric.datagen.recipes.PCRecipeProvider;
import io.github.piscescup.mc.fabric.register.tag.lists.PCTagKeyContainerList;
import io.github.piscescup.mc.fabric.utils.constant.Language;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

/**
 * A builder for data generator factories.
 * <h2>Usages</h2>
 * <pre>{@code
 * public class PiscesCupDevelopLibDataGenerator
 *     implements DataGeneratorEntrypoint
 * {
 *     @Override
 *     public void onInitializeDataGenerator(
 *         FabricDataGenerator fabricDataGenerator
 *     ) {
 *         FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
 *
 *         DataGeneratorFactoryBuilder.create()
 *             .languages(Language.EN_US, Language.ZH_CN)
 *             .tagKeys(
 *                 PCTagKeyContainerList.Option.ALL_TAG_LIST
 *             )
 *             .lootTable(PCLootTableProviderOption.BLOCK_LOOT_TABLE_PROVIDER)
 *             .advancement()
 *             .custom(PCModelProvider::new)
 *             .buildAndAddToPack(pack);
 *    }
 * }
 * }</pre>
 *
 * @author REN YuanTong
 * @Date 2025-07-26
 * @since 1.1.2
 */
public class DataGeneratorFactoryBuilder {
    private final List<Language> languages = new ArrayList<>();
    private final List<PCTagKeyContainerList<?>> tagKeyContainerLists = new ArrayList<>();
    private final List<FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider>>
        customDependentFactories = new ArrayList<>();

    private final List<FabricDataGenerator.Pack.Factory<DataProvider>> customFactories = new ArrayList<>();

    private static <T extends DataGenFactoryOption, P extends DataProvider> void addFactories(
        List<T> options,
        DataProviderFactory<T> factory,
        List<FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider>> result
    ) {
        result.addAll(factory.applyAll(options));
    }
    private DataGeneratorFactoryBuilder() {}

    /**
     * Create a new DatagenFactory builder.
     */
    public static DataGeneratorFactoryBuilder create() {
        return new DataGeneratorFactoryBuilder();
    }

    /**
     * Create a language provider factory for the given language.
     * @param lang The language.
     * @see Language
     */
    public DataGeneratorFactoryBuilder language(Language lang) {
        languages.add(lang);
        return this;
    }

    /**
     * Create language provider factories for the given languages.
     * @param langs The languages.
     * @see Language
     */
    public DataGeneratorFactoryBuilder languages(Language... langs) {
        languages.addAll(Arrays.asList(langs));
        return this;
    }

    /**
     * Create language provider factories for the given languages list.
     * @param langs The list of the languages.
     * @see Language
     */
    public DataGeneratorFactoryBuilder languages(List<Language> langs) {
        languages.addAll(langs);
        return this;
    }

    /**
     * Create a tag key provider factory for the given tag key container list.
     * @param tagKeyContainerList The tag key container list.
     * @see PCTagKeyContainerList.Option
     * @see PCTagKeyContainerList.Option#toDatagenOption(RegistryKey)
     * @see PCTagKeyContainerList.Option#toDatagenOptionAndAddToAllTagList(RegistryKey)
     * @throws NullPointerException if the tag key container list is or contains {@code null}.
     */
    public DataGeneratorFactoryBuilder tagKey(PCTagKeyContainerList<?> tagKeyContainerList) {
        tagKeyContainerLists.add(tagKeyContainerList);
        return this;
    }


    /**
     * Create tag key provider factories for the given tag key container lists.
     * @param tagKeyContainerLists The tag key container lists.
     * @see PCTagKeyContainerList.Option
     * @see PCTagKeyContainerList.Option#toDatagenOption(RegistryKey)
     * @see PCTagKeyContainerList.Option#toDatagenOptionAndAddToAllTagList(RegistryKey)
     * @throws NullPointerException if the tag key container list is or contains {@code null}.
     */
    public DataGeneratorFactoryBuilder tagKeys(PCTagKeyContainerList<?>... tagKeyContainerLists) {
        this.tagKeyContainerLists.addAll(Arrays.asList(tagKeyContainerLists));
        return this;
    }

    /**
     * Create tag key provider factories for the given tag key container lists.
     * @param tagKeyContainerLists The list of the tag key container lists.
     * @see PCTagKeyContainerList.Option
     * @see PCTagKeyContainerList.Option#toDatagenOption(RegistryKey)
     * @see PCTagKeyContainerList.Option#toDatagenOptionAndAddToAllTagList(RegistryKey)
     * @throws NullPointerException if the tag key container list is or contains {@code null}.
     */
    public DataGeneratorFactoryBuilder tagKeys(List<PCTagKeyContainerList<?>> tagKeyContainerLists) {
        this.tagKeyContainerLists.addAll(tagKeyContainerLists);
        return this;
    }

    /**
     * Create a loot table provider factory for the given loot table provider factory.
     * @param lootTableFactory The loot table provider factory.
     */
    public DataGeneratorFactoryBuilder lootTable(FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider> lootTableFactory) {
        this.customDependentFactories.add(lootTableFactory);
        return this;
    }

    /**
     * Create the factories for the advancement.
     */
    public DataGeneratorFactoryBuilder advancement() {
        List<FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider>> advancementFactories =
            new PCAdvancementProviderFactory().factories();
        this.customDependentFactories.addAll(advancementFactories);
        return this;
    }

    public <G extends PCRecipeGenerator> DataGeneratorFactoryBuilder recipes(
        BiFunction<RegistryWrapper.WrapperLookup, RecipeExporter, G> recipeFactory
    ) {
        this.customDependentFactories.add(
            (output, lookUp) -> new PCRecipeProvider(output, lookUp, recipeFactory)
        );
        return this;
    }

    /**
     * Add a custom factory.
     * @param factory The custom factory to be added.
     * @see FabricDataGenerator.Pack.RegistryDependentFactory
     */
    public DataGeneratorFactoryBuilder custom(FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider> factory) {
        this.customDependentFactories.add(factory);
        return this;
    }

    /**
     * Add a custom factory.
     * @param factory The custom factory to be added.
     * @see FabricDataGenerator.Pack.Factory
     */
    public DataGeneratorFactoryBuilder custom(FabricDataGenerator.Pack.Factory<DataProvider> factory) {
        this.customFactories.add(factory);
        return this;
    }

    /**
     * Build and add the factories to the given pack.
     * @param pack The pack to add the factories to.
     * @see FabricDataGenerator.Pack
     */
    public void buildAndAddToPack(FabricDataGenerator.Pack pack) {
        new PCLanguageProviderFactory()
            .applyAll(this.languages)
                .forEach(pack::addProvider);

        new PCTagProviderFactory()
            .applyAll(this.tagKeyContainerLists)
                .forEach(pack::addProvider);

        this.customDependentFactories
                .forEach(pack::addProvider);

        this.customFactories
            .forEach(pack::addProvider);
    }

}
