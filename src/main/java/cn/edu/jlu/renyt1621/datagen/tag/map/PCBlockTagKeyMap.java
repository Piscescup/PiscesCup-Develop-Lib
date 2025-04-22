package cn.edu.jlu.renyt1621.datagen.tag.map;

import cn.edu.jlu.renyt1621.datagen.tag.container.PCBlockTag;
import net.minecraft.block.Block;
import net.minecraft.registry.tag.TagKey;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h2>Description</h2>
 *
 * <h2>Usages</h2>
 *
 * @author REN YuanTong
 * @Date 2025-04-22
 * @since 1.0.0
 */
public class PCBlockTagKeyMap {
    private static volatile PCBlockTagKeyMap INSTANCE;

    private final Map<TagKey<Block>, PCBlockTag> itemTagMap = new HashMap<>();

    private PCBlockTagKeyMap() {}

    public static PCBlockTagKeyMap instance() {
        if (INSTANCE == null) {
            synchronized (PCItemTagKeyMap.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PCBlockTagKeyMap();
                }
            }
        }
        return INSTANCE;
    }


    public Map<TagKey<Block>, PCBlockTag> getItemTagMap() {
        return itemTagMap;
    }


    public PCBlockTag putBlockTags(TagKey<Block> tag, PCBlockTag blockTag) {
        return this.itemTagMap.put(tag, blockTag);
    }

}
