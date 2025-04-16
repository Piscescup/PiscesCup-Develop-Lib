package cn.edu.jlu.renyt1621.datagen.models.maps;

import net.minecraft.block.Block;

/**
 * @author REN YuanTong
 * @Description
 * @Date 2025-04-16
 * @Time 16:23
 */
public class PCBlockModelMap
    extends PCModelMap<Block>
{
    private static volatile PCBlockModelMap INSTANCE;

    private PCBlockModelMap() {
    }

    public static PCBlockModelMap instance() {
        if (INSTANCE == null) {
            synchronized (PCBlockModelMap.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PCBlockModelMap();
                }
            }
        }
        return INSTANCE;
    }
}
