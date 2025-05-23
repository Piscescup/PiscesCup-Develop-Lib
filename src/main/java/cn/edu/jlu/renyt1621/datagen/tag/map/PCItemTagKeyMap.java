package cn.edu.jlu.renyt1621.datagen.tag.map;

import cn.edu.jlu.renyt1621.datagen.tag.container.PCItemTag;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;

import java.util.HashMap;
import java.util.Map;

/**
 * <h2>Description</h2>
 *
 * <h2>Usages</h2>
 *
 * @author REN YuanTong
 * @Date 2025-04-22
 * @since
 */
public class PCItemTagKeyMap {
    private static volatile PCItemTagKeyMap INSTANCE;

    private final Map<TagKey<Item>, PCItemTag> itemTagMap = new HashMap<>();

    private PCItemTagKeyMap() {}

    public static PCItemTagKeyMap instance() {
        if (INSTANCE == null) {
            synchronized (PCItemTagKeyMap.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PCItemTagKeyMap();
                }
            }
        }
        return INSTANCE;
    }


    public Map<TagKey<Item>, PCItemTag> getItemTagMap() {
        return itemTagMap;
    }


    public PCItemTag putItemTag(TagKey<Item> tag, PCItemTag itemTag) {
        return this.itemTagMap.put(tag, itemTag);
    }

}
