package cn.edu.jlu.renyt1621.datagen.lang;

import cn.edu.jlu.renyt1621.utils.constant.Language;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.data.DataProvider;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author REN YuanTong
 * @Description
 * @Date 2025-04-15
 * @Time 13:00
 */
public class PCLanguageProviderFactory {
    private static final PCDLLanguageProvider.LangMap LANG_MAP = PCDLLanguageProvider.LangMap.instance();

    private static FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider> getLanguageProvider(
        Language lang
    ) {
        Map<Object, String> langMap = LANG_MAP.get(lang);

        return (output, lookUp) ->
                PCDLLanguageProvider.Builder.create()
                    .dataOutput(output)
                    .lang(lang)
                    .langMap(langMap)
                    .name("Language" + lang.name())
                    .registryLookup(lookUp)
                    .build();
    }

    /**
     * <p>
     *     Provider a list of {@code RegistryDependentFactory} of language provider for the given language.</br>
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
    public static List<FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider>> languageProvider(
        Language lang
    ) {
        return List.of(
            getLanguageProvider(lang)
        );
    }

    /**
     * <p>
     *     Provider a list of {@code RegistryDependentFactory} of language provider for the given languages.</br>
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
    public static List<FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider>> languagesProvider(
        Language... langs
    ) {

        return Arrays.stream(langs)
            .map(PCLanguageProviderFactory::getLanguageProvider)
            .toList();
    }

    /**
     * <p>
     *     Provider a list of {@code RegistryDependentFactory} of language provider for the given language {@code list}.</br>
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
    public static List<FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider>> languagesProvider(
        List<Language> langList
    ) {
        return langList.stream()
            .map(PCLanguageProviderFactory::getLanguageProvider)
            .toList();
    }

    /**
     * <p>
     *     Provider a list of {@code RegistryDependentFactory} of language provider for all languages.</br>
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
    public static List<FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider>> allLanguagesProvider() {
        return Arrays.stream(Language.values())
            .map(PCLanguageProviderFactory::getLanguageProvider)
            .toList();
    }
}
