package cn.edu.jlu.renyt1621.deprecated.annotations.scanners;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

import java.lang.annotation.Annotation;

/**
 * @author REN YuanTong
 * @Description
 * @Date 2025-04-12
 * @Time 01:18
 */
@Deprecated
public interface DataGenScanner<A extends Annotation>
    extends Scanner<A>
{
    void apply(
        FabricDataGenerator generator,
        FabricDataGenerator.Pack pack,
        Class<?> reg
    );
}
