package io.github.piscescup.mc.fabric.utils;

import java.util.*;
import java.util.function.Supplier;

/**
 * A final utility class for performing various checks.
 * <p>
 * This class contains nested static classes for specific types of checks,
 * such as {@link NullChecker} and {@link ArrayChecker}.
 * It cannot be instantiated.
 */
public final class CheckUtils {
    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private CheckUtils() {}

    /**
     * Lazily creates a supplier for a formatted string message.
     * This is used to avoid string formatting costs unless an exception is actually thrown.
     *
     * @param message the message format string.
     * @param values  the arguments referenced by the format specifiers in the format string.
     * @return a {@link Supplier} that provides the formatted message.
     */
    private static Supplier<String> toSupplier(final String message, final Object... values) {
        return () -> getMassage(message, values);
    }

    /**
     * Formats a message string with the given values.
     * If the values array is empty, the original message is returned.
     *
     * @param message the message format string.
     * @param values  the arguments to be formatted.
     * @return the formatted string, or the original message if no values are provided.
     */
    private static String getMassage(String message, Object... values) {
        return ArrayChecker.isEmpty(values) ? message : String.format(message, values);
    }

    /**
     * A collection of utility methods for null-related checks.
     * <p>
     * This class provides methods to check for null objects, arrays, or elements within collections,
     * either by returning a boolean or by throwing an exception.
     */
    public static final
    class NullChecker {
        private static final String DEFAULT_VALID_INDEX_ITERABLE_EX_MESSAGE =
            "The null index is at: %d.";

        private static final String DEFAULT_VALID_OBJECT_EX_MESSAGE =
            "The object tested is null.";

        /**
         * Private constructor to prevent instantiation of this utility class.
         */
        private NullChecker() {}

        /**
         * Checks if the given object is {@code null}.
         *
         * @param <T> the type of the object.
         * @param o   the object to check.
         * @return {@code true} if the object is {@code null}, {@code false} otherwise.
         */
        public static <T> boolean isNull(final T o) {
            return o == null;
        }

        /**
         * Checks if the given object is not {@code null}.
         *
         * @param <T> the type of the object.
         * @param o   the object to check.
         * @return {@code true} if the object is not {@code null}, {@code false} otherwise.
         */
        public static <T> boolean notNull(final T o) {
            return o != null;
        }

        public static void nonNull(Object... values) {
            for (Object value : values) {
                if (value == null) {
                    throw new NullPointerException(DEFAULT_VALID_OBJECT_EX_MESSAGE);
                }
            }
        }

        /**
         * Checks that the specified object reference is not {@code null}. This
         * method is designed primarily for doing parameter validation in methods
         * and constructors. It uses a default error message.
         *
         * @param <T> the type of the object.
         * @param o   the object to check for nullity.
         * @return {@code o} if not {@code null}.
         * @throws NullPointerException if {@code o} is {@code null}.
         */
        public static <T> T nonNull(final T o) {
            return nonNull(o, DEFAULT_VALID_OBJECT_EX_MESSAGE);
        }

        /**
         * Checks that the specified object reference is not {@code null} and throws a
         * customized {@link NullPointerException} if it is. This method is
         * designed primarily for doing parameter validation in methods and
         * constructors.
         *
         * @param <T>     the type of the object.
         * @param o       the object to check for nullity.
         * @param message the detail message to be used in the event that a {@code
         *                NullPointerException} is thrown. Can be a format string.
         * @param values  the arguments referenced by the format specifiers in the message string.
         * @return {@code o} if not {@code null}.
         * @throws NullPointerException if {@code o} is {@code null}.
         */
        public static <T> T nonNull(final T o, String message, Object... values) {
            return Objects.requireNonNull(o, toSupplier(message, values));
        }

        /**
         * Checks that the specified iterable is not {@code null} and that none of its elements
         * are {@code null}. If any element is null, an {@link IllegalArgumentException} is thrown
         * with a default message indicating the index of the null element.
         *
         * @param <T>      the type of the iterable.
         * @param iterable the iterable to check.
         * @return the checked iterable if it and all its elements are not {@code null}.
         * @throws NullPointerException     if the {@code iterable} is {@code null}.
         * @throws IllegalArgumentException if any element in the {@code iterable} is {@code null}.
         */
        public static <T extends Iterable<?>> T nonNullElements(final T iterable) {
            return nonNullElements(iterable, DEFAULT_VALID_INDEX_ITERABLE_EX_MESSAGE);
        }

        /**
         * Checks that the specified iterable is not {@code null} and that none of its elements
         * are {@code null}. If any element is null, a customized {@link IllegalArgumentException} is thrown.
         * The index of the first null element found is appended to the provided values for message formatting.
         *
         * @param <T>      the type of the iterable.
         * @param iterable the iterable to check.
         * @param message  the detail message to be used in the event that an {@code
         *                 IllegalArgumentException} is thrown. Can be a format string. The index of the
         *                 null element will be added as a formatting argument.
         * @param values   the arguments referenced by the format specifiers in the message string.
         * @return the checked iterable if it and all its elements are not {@code null}.
         * @throws NullPointerException     if the {@code iterable} is {@code null}.
         * @throws IllegalArgumentException if any element in the {@code iterable} is {@code null}.
         */
        public static <T extends Iterable<?>> T nonNullElements(final T iterable, String message, Object... values) {
            Objects.requireNonNull(iterable, "iterable");
            final Iterator<?> iterator = iterable.iterator();
            int i = 0;
            for (; iterator.hasNext(); i++) {
                if (iterator.next() == null) {
                    Object[] newValues = ArrayUtils.add(values, i);
                    throw new IllegalArgumentException(getMassage(message, newValues));
                }
            }
            return iterable;
        }

        /**
         * Checks that the specified array is not {@code null} and that none of its elements
         * are {@code null}. If any element is null, an {@link IllegalArgumentException} is thrown
         * with a default message indicating the index of the null element.
         *
         * @param <T>    the component type of the array.
         * @param values the array to check.
         * @return the checked array if it and all its elements are not {@code null}.
         * @throws NullPointerException     if the {@code array} is {@code null}.
         * @throws IllegalArgumentException if any element in the {@code array} is {@code null}.
         */
        public static <T> T[] nonNullElements(T[] values) {
            return nonNullElements(values, DEFAULT_VALID_INDEX_ITERABLE_EX_MESSAGE);
        }

        /**
         * Checks that the specified array is not {@code null} and that none of its elements
         * are {@code null}. If any element is null, a customized {@link IllegalArgumentException} is thrown.
         * The index of the first null element found is appended to the provided values for message formatting.
         *
         * @param <T>     the component type of the array.
         * @param array   the array to check.
         * @param message the detail message to be used in the event that an {@code
         *                IllegalArgumentException} is thrown. Can be a format string. The index of the
         *                null element will be added as a formatting argument.
         * @param values  the arguments referenced by the format specifiers in the message string.
         * @return the checked array if it and all its elements are not {@code null}.
         * @throws NullPointerException     if the {@code array} is {@code null}.
         * @throws IllegalArgumentException if any element in the {@code array} is {@code null}.
         */
        public static <T> T[] nonNullElements(final T[] array, String message, Object... values) {
            Objects.requireNonNull(array, "array");
            for (int i = 0; i < array.length; i++) {
                if (array[i] == null) {
                    Object[] newValues = ArrayUtils.add(values, i);
                    throw new IllegalArgumentException(getMassage(message, newValues));
                }
            }
            return array;
        }

        /**
         * Checks if all elements in the given array are not {@code null}.
         * The array itself must not be {@code null}.
         *
         * @param <T>   the component type of the array.
         * @param array the array to check, must not be {@code null}.
         * @return {@code true} if all elements are not {@code null}, {@code false} otherwise.
         * @throws NullPointerException if the {@code array} is {@code null}.
         */
        public static <T> boolean allNonNull(T[] array) {
            Objects.requireNonNull(array, "array");
            for (T item : array) {
                if (item == null) {
                    return false;
                }
            }
            return true;
        }


        /**
         * Checks if all elements in the given iterable are not {@code null}.
         * The iterable itself must not be {@code null}.
         *
         * @param <T>      the type of the iterable.
         * @param iterable the iterable to check, must not be {@code null}.
         * @return {@code true} if all elements are not {@code null}, {@code false} otherwise.
         * @throws NullPointerException if the {@code iterable} is {@code null}.
         */
        public static <T extends Iterable<?>> boolean allNonNull(T iterable) {
            Objects.requireNonNull(iterable, "iterable");
            Iterator<?> iterator = iterable.iterator();
            while (iterator.hasNext()) {
                if (iterator.next() == null)
                    return false;
            }
            return true;
        }

        /**
         * Checks if any element in the given array is {@code null}.
         * The array itself must not be {@code null}. This is the inverse of {@link #allNonNull(Object[])}.
         *
         * @param <T>   the component type of the array.
         * @param array the array to check, must not be {@code null}.
         * @return {@code true} if at least one element is {@code null}, {@code false} otherwise.
         * @throws NullPointerException if the {@code array} is {@code null}.
         */
        public static <T> boolean anyIsNull(T[] array) {
            Objects.requireNonNull(array, "array");
            for (T element : array) {
                if (element == null)
                    return true;
            }
            return false;
        }

        /**
         * Checks if any element in the given iterable is {@code null}.
         * The iterable itself must not be {@code null}. This is the inverse of {@link #allNonNull(Iterable)}.
         *
         * @param <T>      the type of the iterable.
         * @param iterable the iterable to check, must not be {@code null}.
         * @return {@code true} if at least one element is {@code null}, {@code false} otherwise.
         * @throws NullPointerException if the {@code iterable} is {@code null}.
         */
        public static <T extends Iterable<?>> boolean anyIsNull(T iterable) {
            Objects.requireNonNull(iterable, "iterable");
            Iterator<?> iterator = iterable.iterator();
            while (iterator.hasNext()) {
                if (iterator.next() == null)
                    return true;
            }
            return false;
        }

    }


    /**
     * A collection of utility methods for array-related checks.
     */
    public static final
    class ArrayChecker {
        /**
         * Private constructor to prevent instantiation of this utility class.
         */
        private ArrayChecker() {}

        /**
         * Checks if an array is {@code null} or empty.
         *
         * @param array the array to check.
         * @return {@code true} if the array is {@code null} or its length is 0, {@code false} otherwise.
         */
        public static boolean isEmpty(final Object[] array) {
            return ArrayUtils.isEmpty(array);
        }
    }

    /**
     * @deprecated Use {@link NullChecker#isNull} instead.
     */
    @Deprecated
    public static <T> boolean checkIsNull(T o) {
        return o == null;
    }

    /**
     * @deprecated Use {@link NullChecker#anyIsNull} instead.
     */
    @Deprecated
    public static <T> boolean checkAnyIsNull(List<T> list) {
        return list == null || list.stream()
            .anyMatch(Objects::isNull);
    }

    /**
     * @deprecated Use {@link NullChecker#anyIsNull} instead.
     */
    @SafeVarargs
    @Deprecated
    public static <T> boolean checkAnyIsNull(T... objects) {
        return objects == null || Arrays.stream(objects)
            .anyMatch(Objects::isNull);
    }

    /**
     * @deprecated Use {@link NullChecker#notNull} instead.
     */
    @Deprecated
    public static <T> boolean checkNotNull(T o) {
        return o != null;
    }

    /**
     * @deprecated Use {@link NullChecker#allNonNull} instead.
     */
    @Deprecated
    public static <T> boolean checkAllNotNull(List<T> o) {
        return o != null && o.stream().allMatch(Objects::nonNull);
    }

    /**
     * @deprecated Use {@link NullChecker#nonNull} instead.
     */
    @Deprecated
    public static <T> void checkIsNullThenThrow(T o, String throwMessage) {
        if (checkIsNull(o)) {
            throw new IllegalArgumentException(throwMessage);
        }
    }

    /**
     * @deprecated Use {@link NullChecker#nonNullElements} instead.
     */
    @Deprecated
    public static <T> void checkAnyIsNullThenThrow(List<T> list, String throwMessage) {
        if (checkAnyIsNull(list)) {
            throw new IllegalArgumentException(throwMessage);
        }
    }
}
