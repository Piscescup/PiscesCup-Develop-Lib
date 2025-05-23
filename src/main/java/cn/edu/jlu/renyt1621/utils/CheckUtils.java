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

    public static <T> boolean checkIsNull(T o) {
        return o == null;
    }

    public static <T> boolean checkAnyIsNull(List<T> list) {
        return list == null || list.stream()
            .anyMatch(Objects::isNull);
    }

    @SafeVarargs
    public static <T> boolean checkAnyIsNull(T... objects) {
        return objects == null || Arrays.stream(objects)
            .anyMatch(Objects::isNull);
    }

    public static <T> boolean checkNotNull(T o) {
        return o != null;
    }

    public static <T> boolean checkAllNotNull(List<T> o) {
        return o != null && o.stream().allMatch(Objects::nonNull);
    }

    public static <T> void checkIsNullThenThrow(T o, String throwMessage) {
        if (checkIsNull(o)) {
            throw new IllegalArgumentException(throwMessage);
        }
    }

    public static <T> void checkAnyIsNullThenThrow(List<T> list, String throwMessage) {
        if (checkAnyIsNull(list)) {
            throw new IllegalArgumentException(throwMessage);
        }
    }
}
