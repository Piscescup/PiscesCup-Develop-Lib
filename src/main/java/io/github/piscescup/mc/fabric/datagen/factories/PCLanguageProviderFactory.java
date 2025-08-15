package io.github.piscescup.mc.fabric.datagen.factories;

import io.github.piscescup.mc.fabric.datagen.lang.PCLanguageProvider;
import io.github.piscescup.mc.fabric.datagen.lang.map.LangMap;
import io.github.piscescup.mc.fabric.utils.constant.Language;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.data.DataProvider;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author REN YuanTong
 * @Date 2025-04-15
 * @since 1.0.0
 */
public final class PCLanguageProviderFactory
    implements DataProviderFactory<Language>
{
    private static final LangMap LANG_MAP = LangMap.instance();
    // public static final PCLanguageProviderFactory INSTANCE = new PCLanguageProviderFactory();

    PCLanguageProviderFactory() {}

    private static @NotNull FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider> getLanguageProvider(
        Language lang
    ) {
        Map<Object, String> langMap = LANG_MAP.get(lang);

        return (output, lookUp) ->
                PCLanguageProvider.Builder.create()
                    .dataOutput(output)
                    .lang(lang)
                    .langMap(langMap)
                    .name("PC Language Provider: " + lang.name())
                    .registryLookup(lookUp)
                    .build();
    }

    /**
     * <p>
     *     Provide a list of {@code RegistryDependentFactory} of language provider for the given language.</br>
     *     You should use this method in The DataGenerator class provided by the <strong>Fabric</strong>.</br>
     * </p>
     *
     * <p>
     *     Below is a simple usage:
     * </p>
     * <blockquote><pre>
     * public class PiscesCupDevelopLibDataGenerator
     *   implements DataGeneratorEntrypoint
     * {
     *      &#64;Override
     *      public void onInitializeDataGenerator(FabricDataGenerator generator) {
     *          FabricDataGenerator.Pack pack = generator.createPack();
     *
     *          PCLanguageProviderFactory.languageProvider(Language.AF_ZA)
     * 		        .forEach(pack::addProvider);
     *      }
     * }
     * </pre></blockquote>
     *
     *
     * @param lang The language
     * @return A list of {@code RegistryDependentFactory} of language provider for the given language
     */
    @Contract("_ -> new")
    @Deprecated
    public static @NotNull @Unmodifiable List<FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider>> languageProvider(
        Language lang
    ) {
        return List.of(
            getLanguageProvider(lang)
        );
    }

    /**
     * <p>
     *     Provide a list of {@code RegistryDependentFactory} of language provider for the given languages.</br>
     *     You should use this method in the DataGenerator class provided by the <strong>Fabric</strong>.</br>
     * </p>
     *
     * <p>Below is a simple usage:</p>
     * <blockquote><pre>
     * public class PiscesCupDevelopLibDataGenerator
     *     implements DataGeneratorEntrypoint
     * {
     *     &#64;Override
     *     public void onInitializeDataGenerator(FabricDataGenerator generator) {
     * 		   FabricDataGenerator.Pack pack = generator.createPack();
     *
     * 		   PCLanguageProviderFactory.languageProvider(
     * 		       Language.EN_US, Language.ZH_CN
     * 		   )
     * 			   .forEach(pack::addProvider);
     *     }
     * }
     * </pre></blockquote>
     * @param langs The languages.
     * @return A list of {@code RegistryDependentFactory} of language provider for the given language
     */
    @Deprecated
    public static List<FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider>> languagesProvider(
        Language... langs
    ) {

        return Arrays.stream(langs)
            .map(PCLanguageProviderFactory::getLanguageProvider)
            .toList();
    }

    /**
     * <p>
     *     Provide a list of {@code RegistryDependentFactory} of language provider for the given language {@code list}.</br>
     *     You should use this method in the DataGenerator class provided by the <strong>Fabric</strong>.</br>
     * </p>
     *
     * <p>Below is a simple usage:</p>
     * <blockquote><pre>
     * public class PiscesCupDevelopLibDataGenerator
     *     implements DataGeneratorEntrypoint
     * {
     *     &#64;Override
     *     public void onInitializeDataGenerator(FabricDataGenerator generator) {
     * 		   FabricDataGenerator.Pack pack = generator.createPack();
     *
     * 		   PCLanguageProviderFactory.languageProvider(
     * 		       List.of(Language.EN_US, Language.ZH_CN)
     * 		   )
     * 			   .forEach(pack::addProvider);
     *     }
     * }
     * </pre></blockquote>
     * @param langList The languages.
     * @return A list of {@code RegistryDependentFactory} of language provider for the given language
     */
    @Deprecated
    public static List<FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider>> languagesProvider(
        @NotNull List<Language> langList
    ) {
        return langList.stream()
            .map(PCLanguageProviderFactory::getLanguageProvider)
            .toList();
    }

    /**
     * <p>
     *     Provide a list of {@code RegistryDependentFactory} of language provider for all languages.</br>
     *     You should use this method in the DataGenerator class provided by the <strong>Fabric</strong>.</br>
     * </p>
     *
     * <p>Below is a simple usage:</p>
     * <blockquote><pre>
     * public class PiscesCupDevelopLibDataGenerator
     *     implements DataGeneratorEntrypoint
     * {
     *     &#64;Override
     *     public void onInitializeDataGenerator(FabricDataGenerator generator) {
     * 		   FabricDataGenerator.Pack pack = generator.createPack();
     *
     * 		   PCLanguageProviderFactory.allLanguagesProvider()
     * 			.forEach(pack::addProvider);
     *     }
     * }
     * </pre></blockquote>
     * @return A list of {@code RegistryDependentFactory} of language provider for all language
     */
    @Deprecated
    public static @Unmodifiable List<FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider>> allLanguagesProvider() {
        return Arrays.stream(Language.values())
            .map(PCLanguageProviderFactory::getLanguageProvider)
            .toList();
    }

    @Override
    public FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider> apply(@NotNull Language option) {
        return getLanguageProvider(option);
    }

    @Override
    public List<FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider>> factories() {
        return applyAll(Language.values());
    }
}
