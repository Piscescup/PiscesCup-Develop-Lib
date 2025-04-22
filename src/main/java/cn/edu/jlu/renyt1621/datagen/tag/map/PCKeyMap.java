package cn.edu.jlu.renyt1621.datagen.tag.map;

import cn.edu.jlu.renyt1621.register.tag.PCBlockTagKeyRegister;
import cn.edu.jlu.renyt1621.register.tag.PCItemTagKeyRegister;
import com.jcraft.jorbis.Block;
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
 * @since 1.0.0
 */
public class PCKeyMap {
    private static volatile PCKeyMap instance;

    private final Map<TagKey<Item>, PCItemTagKeyRegister> itemTagKeyMap = new HashMap<>();
    private final Map<TagKey<Block>, PCBlockTagKeyRegister> blockTagMap = new HashMap<>();

    private PCKeyMap() {}

    public static PCKeyMap instance() {
        if (instance == null) {
            synchronized (PCKeyMap.class) {
                if (instance == null) {
                    instance = new PCKeyMap();
                }
            }
        }
        return instance;
    }

    public Map<TagKey<Item>, PCItemTagKeyRegister> getItemTagKeyMap() {
        return itemTagKeyMap;
    }

    public Map<TagKey<Block>, PCBlockTagKeyRegister> getBlockTagMap() {
        return blockTagMap;
    }

    public PCItemTagKeyRegister putItemTag(TagKey<Item> tagKey, PCItemTagKeyRegister register) {
        return itemTagKeyMap.put(tagKey, register);
    }

    public PCBlockTagKeyRegister putBlockItem(TagKey<Block> tagKey, PCBlockTagKeyRegister register) {
        return blockTagMap.put(tagKey, register);
    }

    public void putAllItemTags(Map<TagKey<Item>, PCItemTagKeyRegister> map) {
        itemTagKeyMap.putAll(map);
    }

    public void putAllBlockTags(Map<TagKey<Block>, PCBlockTagKeyRegister> map) {
        blockTagMap.putAll(map);
    }


}
