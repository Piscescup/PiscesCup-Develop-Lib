package io.github.piscescup.mc.fabric.datagen.factories.option;

import java.util.function.UnaryOperator;

/**
 * <h2>Description</h2>
 *
 * <h2>Usages</h2>
 *
 * @author REN YuanTong
 * @Date 2025-07-25
 * @since 1.1.2
 */
public interface DataGenFactoryOption {
    public static UnaryOperator<String> APPLY_FACTORY_OPTION =
        simpleName -> "Data Generator Option: " + simpleName;

    String getDataGeneratorOptionFullName();
}
