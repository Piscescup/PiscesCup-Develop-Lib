package cn.edu.jlu.renyt1621.deprecated.annotations.scanners;

import cn.edu.jlu.renyt1621.deprecated.annotations.TargetType;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author REN YuanTong
 * @Description
 * @Date 2025-04-12
 */
public interface Scanner<A extends Annotation> {
    /**
     * Process the annotations of the target
     * @param target The target to be processed
     * @param classRegistered The class being registered
     * @param <T> The type of the field, which will be wrapped
     */
    <T> void process(Target<T> target);

    /**
     * Get the class of the annotation being processed
     * @return The class of the annotation being processed
     */
    Class<A> annotationClassProcessed();

    /**
     * The name of the class, which implements this interface
     * @return The name of the class, which implements this interface
     */
    String scannerName();

    @SuppressWarnings("unchecked")
    default <O> Target<O> wrap(Field field) {
        field.setAccessible(true);

        try {
            Object o = field.get(null);



            if ( o == null || !isAnnotated(field) ) return null;
            for (Class<?> tClass : annotationClassProcessed().getAnnotation(TargetType.class).value()) {
                if (tClass == Object.class)
                    return (Target<O>) new Target<>(field, o);
                else if (tClass.isInstance(o))
                    return (Target<O>) new Target<>(field, tClass.cast(o));
            }
            return null;
        } catch (IllegalAccessException e) {
            return null;
        }

    }


    default boolean isAnnotated(Field field) {
        return field.isAnnotationPresent(annotationClassProcessed());
    }

    default String[] supportPackages() {
        Class<?> scannerClass = this.getClass();

        ScannerPackages scPckAnno = scannerClass.getAnnotation(ScannerPackages.class);
        return (scPckAnno != null) ? scPckAnno.value() : new String[]{this.getClass().getPackage().getName()};
    }


    record Target<E>(Field field, E element) {
        @Override
        public String toString() {
            return "Target{" +
                "field=" + field +
                ", element=" + element +
                '}';
        }
    }
}
