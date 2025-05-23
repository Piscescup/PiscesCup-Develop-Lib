package cn.edu.jlu.renyt1621.deprecated.annotations.lang;

import cn.edu.jlu.renyt1621.deprecated.annotations.TargetType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author REN YuanTong
 * @Date 2025-04-09
 */
@TargetType(Object.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Deprecated
public @interface Langs {
    LangTranslation[] value();
}
