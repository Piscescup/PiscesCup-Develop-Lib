package io.github.piscescup.mc.fabric.register.villager.trade;

/**
 * <h2>Description</h2>
 *
 * <h2>Usages</h2>
 *
 * @author REN YuanTong
 * @Date 2025-08-03
 * @since
 */
public interface VillagerTradeLevelConfig {
    int VILLAGER_NOVICE_LEVEL = 1;
    int VILLAGER_APPRENTICE_LEVEL = 2;
    int VILLAGER_JOURNEYMAN_LEVEL = 3;
    int VILLAGER_EXPERT_LEVEL = 4;
    int VILLAGER_MASTER_LEVEL = 5;


    VillageTradeOfferConfig tradeLevel(int level);

    static boolean isValidTradeLevel(int level) {
        return level >= VILLAGER_NOVICE_LEVEL && level <= VILLAGER_MASTER_LEVEL;
    }
}