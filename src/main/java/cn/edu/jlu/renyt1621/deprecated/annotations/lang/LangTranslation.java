package cn.edu.jlu.renyt1621.deprecated.annotations.lang;

import cn.edu.jlu.renyt1621.utils.constant.Language;

/**
 * @author REN YuanTong
 * @Description
 * @Date 2025-04-09
 */
@Deprecated
public @interface LangTranslation {
    Language lang() default Language.EN_US;

    String value();

    String key() default "$";
}
