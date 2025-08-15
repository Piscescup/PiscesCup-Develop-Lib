package io.github.piscescup.mc.fabric.utils;

/**
 * <h2>Description</h2>
 *
 * <h2>Usages</h2>
 *
 * @author REN YuanTong
 * @Date 2025-07-16
 * @since 1.1.3
 */
public final class ClassUtils {
    private ClassUtils() {}

    @SuppressWarnings("unchecked")
    public static <T> Class<T> getClass(final T object) {
        return object == null ? null : (Class<T>) object.getClass();
    }

    @SuppressWarnings("unchecked")
    public static <T> Class<T> getComponentType(Class<T[]> clazz) {
        return clazz == null ? null : (Class<T>) clazz.getComponentType();
    }
}
