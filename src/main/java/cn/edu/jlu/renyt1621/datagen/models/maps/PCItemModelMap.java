package cn.edu.jlu.renyt1621.datagen.models.maps;

import net.minecraft.item.Item;

/**
 * @author REN YuanTong
 * @Description
 * @Date 2025-04-16
 * @Time 16:20
 */
public class PCItemModelMap
    extends PCModelMap<Item>
{
    private static volatile PCItemModelMap INSTANCE;

    private PCItemModelMap() {}

    public static PCItemModelMap instance() {
        if ( INSTANCE == null ) {
            synchronized (PCItemModelMap.class) {
                if (INSTANCE == null)
                    INSTANCE = new PCItemModelMap();
            }
        }
        return INSTANCE;
    }
}
