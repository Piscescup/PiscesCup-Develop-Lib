package cn.edu.jlu.renyt1621.deprecated.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author REN YuanTong
 * @Description
 * @Date 2025-04-09
 * @Time 11:55
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Deprecated
public @interface TargetType {
    Class<?>[] value();
}
