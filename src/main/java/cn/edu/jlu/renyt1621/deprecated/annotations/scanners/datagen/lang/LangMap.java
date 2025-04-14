package cn.edu.jlu.renyt1621.deprecated.annotations.scanners.datagen.lang;

import cn.edu.jlu.renyt1621.utils.constant.Language;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * @author REN YuanTong
 * @Description
 * @Date 2025-04-12
 * @Time 11:19
 */
public final class LangMap {
    private final EnumMap<Language, Map<Object, String>> LANG_MAP = new EnumMap<>(Language.class);

    private LangMap() {
        if (INSTANCE != null)
            throw new IllegalStateException("LangMap is a singleton");

        for (Language lang : Language.values()) {
            LANG_MAP.put(lang, new HashMap<>());
        }
    }

    private static final LangMap INSTANCE = new LangMap();

    public static LangMap instance() {
        return INSTANCE;
    }

    public void put(Language lang, Object thing, String value) {
          LANG_MAP.get(lang).put(thing, value);
    }

    public void put(Language lang, Map<Object, String> langMap) {
        LANG_MAP.get(lang).putAll(langMap);
    }


    public void put(Language lang, String key, String value) {
            LANG_MAP.get(lang).put(key, value);
    }

    public Map<Object, String> get(Language lang) {
        return LANG_MAP.getOrDefault(lang, new HashMap<>());
    }

    public Map<Language, Map<Object, String>> get() {
        return LANG_MAP;
    }
}
