package io.github.piscescup.mc.fabric.register.villager.trade;

import net.minecraft.village.TradeOffers;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * <h2>Description</h2>
 *
 * <h2>Usages</h2>
 *
 * @author REN YuanTong
 * @Date 2025-08-03
 * @since 1.1.2
 */
public interface VillageTradeOfferConfig {
    VillageTradeOfferConfig addTradeOffer(@NotNull TradeOffers.Factory offer);

    VillageTradeOfferConfig addTradeOffers(@NotNull TradeOffers.Factory... offers);

    VillageTradeOfferConfig addTradeOffers(@NotNull List<TradeOffers.Factory> offers);
}
