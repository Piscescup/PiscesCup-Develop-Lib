package cn.edu.jlu.renyt1621.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * <h2>Description</h2>
 *
 * <h2>Usages</h2>
 *
 * @author REN YuanTong
 * @Date 2025-04-22
 * @since 1.0.0
 */
public final class CheckUtils {
    private CheckUtils() {}

    public static boolean checkIsNull(Object o) {
        return o == null;
    }

    public static boolean checkAnyIsNull(List<Object> list) {
        return list == null || list.stream()
            .anyMatch(Objects::isNull);
    }

    public static boolean checkAnyIsNull(Object... objects) {
        return objects == null || Arrays.stream(objects)
            .anyMatch(Objects::isNull);
    }

    public static boolean checkNotNull(Object o) {
        return o != null;
    }

    public static boolean checkAllNotNull(List<Object> o) {
        return o != null && o.stream().allMatch(Objects::nonNull);
    }

    public static void checkIsNullThenThrow(Object o, String throwMessage) {
        if (checkIsNull(o)) {
            throw new IllegalArgumentException(throwMessage);
        }
    }

    public static void checkAnyIsNullThenThrow(List<Object> list, String throwMessage) {
        if (checkAnyIsNull(list)) {
            throw new IllegalArgumentException(throwMessage);
        }
    }
}
