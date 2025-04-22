package cn.edu.jlu.renyt1621.datagen.factories;

import cn.edu.jlu.renyt1621.datagen.tag.PCBlockTagProvider;
import cn.edu.jlu.renyt1621.datagen.tag.PCItemTagProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.data.DataProvider;

import java.util.List;

/**
 * <h2>Description</h2>
 *
 * <h2>Usages</h2>
 *
 * @author REN YuanTong
 * @Date 2025-04-22
 * @since 1.0.0
 */
public enum PCTagProviderFactory {
    ITEM_TAG_PROVIDER(
        List.of(PCItemTagProvider::new)
    ),
    BLOCK_TAG_PROVIDER(
        List.of(PCBlockTagProvider::new)
    ),

    ;

    private List<FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider>> factories;

    PCTagProviderFactory(List<FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider>> factories) {
        this.factories = factories;
    }

    public List<FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider>> getFactories() {
        return factories;
    }

}
