package cn.edu.jlu.renyt1621.datagen.tag;

import cn.edu.jlu.renyt1621.datagen.tag.container.PCItemTag;
import cn.edu.jlu.renyt1621.datagen.tag.map.PCItemTagKeyMap;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * <h2>Description</h2>
 *
 * <h2>Usages</h2>
 *
 * @author REN YuanTong
 * @Date 2025-04-22
 * @since 1.0.0
 */
public class PCItemTagProvider
    extends FabricTagProvider<Item>
{
    private static final Map<TagKey<Item>, PCItemTag> ITEM_TAG_MAP = PCItemTagKeyMap.instance().getItemTagMap();


    public PCItemTagProvider(
        FabricDataOutput output,
        CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture
    ) {
        super(output, RegistryKeys.ITEM, registriesFuture);
    }


    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        ITEM_TAG_MAP.forEach(
            (tagKey, itemTag) -> {
                List<Item> items = itemTag.getItems();
                List<TagKey<Item>> tags = itemTag.getTags();

                FabricTagProvider<Item>.FabricTagBuilder tagBuilder = getOrCreateTagBuilder(tagKey);
                items.forEach(tagBuilder::add);
                tags.forEach(tagBuilder::addOptionalTag);
            }
        );

    }
}
