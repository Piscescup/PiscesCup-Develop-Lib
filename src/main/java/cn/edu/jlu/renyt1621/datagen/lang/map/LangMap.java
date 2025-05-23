package cn.edu.jlu.renyt1621.datagen.lang.map;

import cn.edu.jlu.renyt1621.utils.constant.Language;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * <h2>Description</h2>
 * A {@code Map} from the items in Minecraft to its translation.
 * <h2>Usages</h2>
 * The static method {@link #instance()} can get the instance of this class.
 *
 * <blockquote><pre>
 * public static final LangMap LANG_MAP = LangMap.instance();
 *
 * LANG_MAP.put(Language.EN_US, "itemGroups.piscescup_develop_lib.item_group1", "Item Group 1")
 * LANG_MAP.put(Language.EN_US, ModItems.PC_BLOCK1, "PC Block 1")
 * </pre></blockquote>
 * @author REN YuanTong
 * @Date 2025-05-04
 * @since 1.0.0
 */
public final class LangMap {
    private final EnumMap<Language, Map<Object, String>> LANG_MAP = new EnumMap<>(Language.class);

    private LangMap() {
        for (Language lang : Language.values()) {
            LANG_MAP.put(lang, new HashMap<>());
        }
    }

    private static volatile LangMap INSTANCE;

    public static LangMap instance() {
        if (INSTANCE == null) {
            synchronized (LangMap.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LangMap();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Add a translation for the provided thing.
     * @param lang The language.
     * @param thing The thing to be translated.
     * @param value The translation.
     */
    public void put(Language lang, Object thing, String value) {
        LANG_MAP.get(lang).put(thing, value);
    }

    /**
     * Add a translation for the provided thing.
     * @param lang The language.
     * @param langMap The {@code Map} from the thing to be translated to the translation.
     */
    public void put(Language lang, Map<Object, String> langMap) {
        LANG_MAP.get(lang).putAll(langMap);
    }

    /**
     * Add a translation from the given translation key.
     * @param lang The language.
     * @param key The translation key.
     * @param value The translation.
     */
    public void put(Language lang, String key, String value) {
        LANG_MAP.get(lang).put(key, value);
    }

    /**
     * Get the translation {@code Map} for the given {@code Language}.
     * @param lang The language.
     * @return The {@code Map} of the given Language.
     */
    public Map<Object, String> get(Language lang) {
        return LANG_MAP.getOrDefault(lang, new HashMap<>());
    }

    /**
     * Get the {@code LangMap} instance.
     * @return The instance for the {@code LangMap}.
     */
    public Map<Language, Map<Object, String>> get() {
        return LANG_MAP;
    }
}