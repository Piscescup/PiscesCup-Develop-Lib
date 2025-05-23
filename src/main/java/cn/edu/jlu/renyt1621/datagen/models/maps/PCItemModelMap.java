package cn.edu.jlu.renyt1621.datagen.models.maps;

import net.minecraft.item.Item;

/**
 * @author REN YuanTong
 * @Date 2025-04-16
 * @since 1.0.0
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
