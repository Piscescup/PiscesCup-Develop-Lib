package cn.edu.jlu.renyt1621.datagen.loot.map;


import net.minecraft.block.Block;

import java.util.HashMap;
import java.util.Map;

/**
 * <h1>Description</h1>
 *
 * <h1>Usages</h1>
 *
 * @author REN YuanTong
 * @Date 2025-04-17
 * @since
 */
public class PCSilkTouchDropMap {
    private static volatile PCSilkTouchDropMap INSTANCE;

    private PCSilkTouchDropMap() {}

    public static PCSilkTouchDropMap instance() {
        if (INSTANCE == null) {
            synchronized (PCSilkTouchDropMap.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PCSilkTouchDropMap();
                }
            }
        }
        return INSTANCE;
    }

    private final Map<Block, Block> silkTouchDropMap = new HashMap<>();

    public Block put(Block block, Block drop) {
        return silkTouchDropMap.put(block, drop);
    }

    public void putAll(Map<Block, Block> map) {
        silkTouchDropMap.putAll(map);
    }

    public Block get(Block block) {
        return silkTouchDropMap.get(block);
    }

    public boolean containsBlock(Block block) {
        return silkTouchDropMap.containsKey(block);
    }

    public int size() {
        return silkTouchDropMap.size();
    }

    public void clear() {
        silkTouchDropMap.clear();
    }
}
