package cn.edu.jlu.renyt1621.deprecated.annotations.scanners.datagen.lang;

import cn.edu.jlu.renyt1621.deprecated.annotations.lang.Langs;
import cn.edu.jlu.renyt1621.deprecated.annotations.scanners.DataGenScanner;
import cn.edu.jlu.renyt1621.deprecated.annotations.scanners.ScannerPackages;
import cn.edu.jlu.renyt1621.datagen.lang.PCDLLanguageProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.data.DataProvider;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @author REN YuanTong
 * @Description
 * @Date 2025-04-12
 */
@ScannerPackages({
    "cn.edu.jlu.renyt1621.tests"
})
@Deprecated
public class LangScanner
    implements DataGenScanner<Langs>
{
    private final PCDLLanguageProvider.LangMap LANGS_MAP = PCDLLanguageProvider.LangMap.instance();


    /**
     * @param generator The data generator
     * @param pack The data generator pack
     * @param reg The class being registered.
     */
    @Override
    public void apply(FabricDataGenerator generator, FabricDataGenerator.Pack pack, Class<?> reg) {
        LANGS_MAP.get()
            .forEach(
                (lang, langMap) -> pack.addProvider(
                    (FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider>)
                        (output, lookUp) ->
                            PCDLLanguageProvider.Builder.create()
                                .dataOutput(output)
                                .lang(lang)
                                .langMap(langMap)
                                .name(scannerName())
                                .registryLookup(lookUp)
                                .build()
                )
            );
    }


    /**
     * Process the annotations of the target
     * @param target The target to be processed
     * @param classRegistered The class being registered.
     * @param <T> The type of the field, which will be wrapped
     */
    @Override
    public <T> void process(Target<T> target) {
        Field field = target.field();
        field.getDeclaringClass().getPackageName();
        boolean canAccess = field.canAccess(null);
        field.setAccessible(true);
        Langs[] langs = field.getAnnotationsByType(Langs.class);
        try {
            Object o = field.get(null);

            for (Langs lang : langs) {
                Arrays.stream(lang.value())
                    .forEach(
                        langTranslation -> {
                            LANGS_MAP
                                .put(
                                    langTranslation.lang(),
                                    o,
                                    langTranslation.value()
                                );
                        }
                    );
            }


        } catch (IllegalAccessException e) {

        } finally {
            field.setAccessible(canAccess);
        }
    }

    /**
     * Get the class of the annotation being processed
     * @return The class of the annotation being processed, always {@link Langs}
     */
    @Override
    public Class<Langs> annotationClassProcessed() {
        return Langs.class;
    }

    /**
     * The name of the class, which implements this interface
     * @return The name of the class, which implements this interface. Always return {@code "Language Scanner"}
     */
    @Override
    public String scannerName() {
        return "Language Scanner";
    }


    /**
     * Check if the field is annotated with {@link Langs}
     * @param field The field to be checked
     * @return {@code true}, if the field is annotated with {@link Langs}. otherwise {@code false}
     */
    public boolean isAnnotated(Field field) {
        return field.getAnnotationsByType(Langs.class).length > 0;
    }
}
