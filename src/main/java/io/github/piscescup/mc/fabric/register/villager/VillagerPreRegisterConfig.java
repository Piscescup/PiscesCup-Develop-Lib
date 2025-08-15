package io.github.piscescup.mc.fabric.register.villager;

import io.github.piscescup.mc.fabric.register.PreRegisterConfig;
import io.github.piscescup.mc.fabric.register.villager.trade.PCTradeOfferBuilder;

/**
 * <h2>Description</h2>
 *
 * <h2>Usages</h2>
 *
 * @author REN YuanTong
 * @Date 2025-07-31
 * @since
 */
public interface VillagerPreRegisterConfig
    extends PreRegisterConfig<VillagerPostRegisterConfig>
{

    VillagerPreRegisterConfig trades(PCTradeOfferBuilder trades);
}
