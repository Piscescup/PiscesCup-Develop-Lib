package io.github.piscescup.mc.fabric.register.tag.lists;

import io.github.piscescup.mc.fabric.datagen.factories.option.DataGenFactoryOption;
import io.github.piscescup.mc.fabric.datagen.factories.PCTagProviderFactory;
import io.github.piscescup.mc.fabric.datagen.tag.PCTagProvider;
import io.github.piscescup.mc.fabric.register.tag.PCTagKeyRegister;
import io.github.piscescup.mc.fabric.register.tag.container.PCTagKeyContainer;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.poi.PointOfInterestType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <h2>Description</h2>
 * A {@code List} contains {@link PCTagKeyContainer}
 *
 * <h2>Usages</h2>
 *
 * @author REN YuanTong
 * @Date 2025-07-25
 * @since 1.1.2
 * @see PCTagKeyContainer
 * @see PCTagKeyRegister
 * @see PCTagProvider
 * @see PCTagProviderFactory
 */
public class PCTagKeyContainerList<T>
    implements TagKeyContainerList<PCTagKeyContainer<T>>, DataGenFactoryOption
{
    private static final Map<RegistryKey<?>, PCTagKeyContainerList<?>> CONTAINER_LIST_MAP = new ConcurrentHashMap<>();

    private final List<PCTagKeyContainer<T>> containerList = new ArrayList<>();
    private final RegistryKey<Registry<T>> registryKey;

    private PCTagKeyContainerList(RegistryKey<Registry<T>> registryKey) {
        this.registryKey = registryKey;
    }

    @SuppressWarnings("unchecked")
    public static <T> PCTagKeyContainerList<T> getFor(RegistryKey<? extends Registry<T>> registryKey) {
        Objects.requireNonNull(registryKey, "RegistryKey cannot be null");

        return (PCTagKeyContainerList<T>) CONTAINER_LIST_MAP.computeIfAbsent(
            registryKey,
            key -> new PCTagKeyContainerList<>((RegistryKey<Registry<T>>) key)
        );
    }

    public RegistryKey<Registry<T>> getRegistryKey() {
        return registryKey;
    }

    @Override
    public List<PCTagKeyContainer<T>> getContainerList() {
        return containerList;
    }

    @Override
    public boolean addContainer(@NotNull PCTagKeyContainer<T> container) {
        Objects.requireNonNull(container);
        return this.containerList.add(container);
    }

    @Override
    public String getDataGeneratorOptionFullName() {
        return DataGenFactoryOption.APPLY_FACTORY_OPTION.apply(registryKey.toString());
    }

    public static
    class Option{
        public static final PCTagKeyContainerList<Item> ITEM_LIST = getFor(RegistryKeys.ITEM);

        public static final PCTagKeyContainerList<Block> BLOCK_LIST = getFor(RegistryKeys.BLOCK);

        public static final PCTagKeyContainerList<ItemGroup> POI_LIST = getFor(RegistryKeys.ITEM_GROUP);

        public static final PCTagKeyContainerList<PointOfInterestType> BLOCK_ENTITY_LIST = getFor(RegistryKeys.POINT_OF_INTEREST_TYPE);

        public static final PCTagKeyContainerList<EntityType<?>> ENTITY_LIST = getFor(RegistryKeys.ENTITY_TYPE);

        public static final List<PCTagKeyContainerList<?>> ALL_TAG_LIST = new ArrayList<>();

        static {
            ALL_TAG_LIST.add(ITEM_LIST);
            ALL_TAG_LIST.add(BLOCK_LIST);
            ALL_TAG_LIST.add(POI_LIST);
            ALL_TAG_LIST.add(BLOCK_ENTITY_LIST);
            ALL_TAG_LIST.add(ENTITY_LIST);
        }

        public static <T> PCTagKeyContainerList<T> toDatagenOption(RegistryKey<Registry<T>> registryKey) {
            return getFor(registryKey);
        }

        public static <T> PCTagKeyContainerList<T> toDatagenOptionAndAddToAllTagList(RegistryKey<Registry<T>> registryKey) {
            PCTagKeyContainerList<T> tagList = getFor(registryKey);
            ALL_TAG_LIST.add(tagList);
            return tagList;
        }
    }

}
