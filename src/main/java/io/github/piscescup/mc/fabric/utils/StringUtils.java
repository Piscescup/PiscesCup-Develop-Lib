package io.github.piscescup.mc.fabric.utils;

/**
 * <h2>Description</h2>
 *
 * <h2>Usages</h2>
 *
 * @author REN YuanTong
 * @Date 2025-07-13
 * @since 1.1.2
 */
public final class StringUtils {
    private StringUtils() {}

    public static final String EMPTY_STRING = "";
    public static final String SPACE_STRING = " ";

    public static boolean isNull(final String string) {
        return string == null;
    }

    public static boolean isNotNull(final String string) {
        return string != null;
    }

    public static boolean isEmpty(final String string) {
        return string == null || string.isEmpty();
    }

    public static boolean isNotEmpty(final String string) {
        return !isEmpty(string);
    }

    public static boolean isBlank(final String string) {
        int length = string.length();
        if (length == 0) return true;
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(string.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(final String string) {
        return !isBlank(string);
    }

    public static String trim(final String string) {
        return string == null ? null : string.trim();
    }

    public static String trimOrDefault(final String string, final String defaultValue) {
        return string == null ? defaultValue : string.trim();
    }

    public static String trimOrEmpty(final String string) {
        return trimOrDefault(string, EMPTY_STRING);
    }

    public static int length(final String string) {
        return string == null ? 0 : string.length();
    }


}
