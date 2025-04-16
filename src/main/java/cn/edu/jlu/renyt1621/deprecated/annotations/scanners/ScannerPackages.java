package cn.edu.jlu.renyt1621.deprecated.annotations.scanners;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author REN YuanTong
 * @Description
 * @Date 2025-04-12
 */
@Retention(RetentionPolicy.RUNTIME)
@Deprecated
public @interface ScannerPackages {

    String[] value();
}
