package cn.edu.jlu.renyt1621.datagen.loot.map;


import cn.edu.jlu.renyt1621.datagen.loot.drop.PCBlockDrop;
import net.minecraft.block.Block;
import net.minecraft.item.ItemConvertible;

import java.util.HashMap;
import java.util.Map;

/**
 * <h1>Description</h1>
 *
 * <h1>Usages</h1>
 *
 * @author REN YuanTong
 * @Date 2025-04-17
 * @since 1.0.0
 */
public class PCBlockDropMap {
    private static volatile PCBlockDropMap INSTANCE;
    private PCBlockDropMap() {}

    public static PCBlockDropMap getInstance() {
        if (INSTANCE == null) {
            synchronized (PCBlockDropMap.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PCBlockDropMap();
                }
            }
        }
        return INSTANCE;
    }


    private final Map<Block, PCBlockDrop> blockDropMap = new HashMap<>();

    public int size() {
        return blockDropMap.size();
    }

    public boolean isEmpty() {
        return blockDropMap.isEmpty();
    }

    public boolean containsBlock(Block block) {
        return blockDropMap.containsKey(block);
    }


    public Map<Block, PCBlockDrop> get() {
        return blockDropMap;
    }

    public PCBlockDrop getDrop(Block block) {
        return blockDropMap.get(block);
    }

    public PCBlockDrop putDrop(Block block, PCBlockDrop drop) {
        return blockDropMap.put(block, drop);
    }

    public void clear() {
        blockDropMap.clear();
    }




}
