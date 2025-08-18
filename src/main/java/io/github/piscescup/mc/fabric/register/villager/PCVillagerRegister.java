package io.github.piscescup.mc.fabric.register.villager;

import io.github.piscescup.mc.fabric.register.PCRegister;
import io.github.piscescup.mc.fabric.register.villager.trade.PCTradeOfferBuilder;
import net.minecraft.registry.RegistryKey;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * <h2>Description</h2>
 *  A util for registering a villager.
 * <h2>Usage</h2>
 * <p>
 *     The static method: {@link #createFor createFor} create a {@code PCVillagerRegister}
 *     by the given {@link PointOfInterestType}.
 * </p>
 *
 * <p>
 *     Below is a simple usage:
 * </p>
 *
 * <pre>{@code
 * public class ModVillagers {
 *     public static final List<PCVillagerRegister> VILLAGERS = new ArrayList<>();
 *
 *     public static final VillagerProfession TEST_VILLAGER_1 =
 *         PCVillagerRegister.createFor(FIRE_WORK_VILLAGER)
 *             .trades(ModVillagerTrades.FIREWORK_MAN_TRADES)
 *             .registerAndBuild()
 *             .translate(Language.EN_US, "Firework Man")
 *             .translate(Language.ZH_CN, "火箭商")
 *             .get();
 *
 *     public static void register() {}
 * }
 * }</pre>
 * <p>
 *     And then register the class in the mod entry:
 * </p>
 *
 * <pre>{@code
 * public class PiscesCupDevelopLib implements ModInitializer {
 * 	    @Override
 *    public void onInitialize() {
 * 		// This code runs as soon as Minecraft is in a mod-load-ready state.
 * 		// However, some things (like resources) may still be uninitialized.
 * 		// Proceed with mild caution.
 *
 * 		MOD_LOGGER.info("Hello Fabric world!");
 * 		MOD_LOGGER.info("Hello, " + MOD_NAME);
 *
 * 		ModVillagerPOIs.register();
 * 		ModVillagerProfessions.register();
 * 		ModVillagers.register();
 * 		MOD_LOGGER.info("Finish registering");
 *    }
 * }
 * }</pre>
 * @author REN YuanTong
 * @Date 2025-07-02
 * @since 1.1.0
 */
public final class PCVillagerRegister
    extends PCRegister<VillagerProfession, PCVillagerRegister, VillagerPostRegisterConfig>
    implements VillagerPreRegisterConfig, VillagerPostRegisterConfig
{
    private PCTradeOfferBuilder trades;

    private PCVillagerRegister(RegistryKey<VillagerProfession> profession) {
        this.key = profession;
    }

    public static VillagerPreRegisterConfig createFor(@NotNull RegistryKey<VillagerProfession> profession) {
        Objects.requireNonNull(profession, "profession");
        return new PCVillagerRegister(profession);
    }

    @Override
    public VillagerPreRegisterConfig trades(@NotNull PCTradeOfferBuilder trades) {
        Objects.requireNonNull(trades);
        this.trades = trades;
        return this;
    }

    @Override
    public VillagerPostRegisterConfig registerAndBuild() {
        trades.applyToProfession(this.key);
        return this;
    }

}