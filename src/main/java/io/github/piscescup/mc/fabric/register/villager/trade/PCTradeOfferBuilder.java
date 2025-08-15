package io.github.piscescup.mc.fabric.register.villager.trade;

import io.github.piscescup.mc.fabric.register.villager.PCVillagerRegister;
import io.github.piscescup.mc.fabric.utils.CheckUtils;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <h2>Description</h2>
 * A register util for TradeOffer
 * <h2>Usages</h2>
 * <p>
 *     Use the static method: {@link #create()} to createFor a {@code Builder}.
 * </p>
 *
 * <p>
 *     Below are the different methods which set the offers for the different stages of the villager:
 * </p>
 * <ul>
 *     <li>{@link #novice(List)}: The novice stage. </li>
 *     <li>{@link #apprentice(List)}: The apprentice stage. </li>
 *     <li>{@link #journeyman(List)}: The journeyman stage. </li>
 *     <li>{@link #expert(List)}: The expert stage. </li>
 *     <li>{@link #master(List)}: The master stage. </li>
 * </ul>
 *
 * <p>
 *    Below is an example for a {@link TradeOffers.Factory} instance:
 * </p>
 * <blockquote><pre>
 * new TradeOffers.BuyItemFactory(Items.APPLE, 3, 12, 10, 2)
 * </pre></blockquote>
 * <p>
 *     Below is an example for a {@link PCTradeOfferBuilder} instance:
 * </p>
 * <blockquote><pre>
 * public static final PCTradeOfferBuilder FIREWORK_MAN_TRADES = PCTradeOfferBuilder.create()
 *         .novice(List.of(
 *             new TradeOffers.SellItemFactory(Items.GUNPOWDER, 3, 1, 12, 1),
 *             new TradeOffers.BuyItemFactory(Items.PAPER, 6, 12, 1, 2)
 *         ))
 *         .apprentice(List.of(
 *             new TradeOffers.SellItemFactory(Items.FIREWORK_ROCKET, 3, 3, 12, 10),
 *             new TradeOffers.BuyItemFactory(Items.BEDROCK, 1, 5, 1, 5)
 *         ))
 *         .journeyman(List.of(
 *             new TradeOffers.SellItemFactory(Items.FIREWORK_ROCKET, 3, 5, 12, 10)
 *         ))
 *         .expert(List.of())
 *         .master(List.of());
 * </pre></blockquote>
 *
 * @author REN YuanTong
 * @Date 2025-07-02
 * @since 1.1.0
 * @see TradeOffers.Factory
 */
public class PCTradeOfferBuilder
{
    public static final int VILLAGER_NOVICE_LEVEL = 1;
    public static final int VILLAGER_APPRENTICE_LEVEL = 2;
    public static final int VILLAGER_JOURNEYMAN_LEVEL = 3;
    public static final int VILLAGER_EXPERT_LEVEL = 4;
    public static final int VILLAGER_MASTER_LEVEL = 5;

    private final Map<Integer, List<TradeOffers.Factory>> offers = new ConcurrentHashMap<>();

    private PCTradeOfferBuilder() {
        for (int i = VILLAGER_NOVICE_LEVEL; i <= VILLAGER_MASTER_LEVEL; i++)
            offers.put(i, new ArrayList<>());
    }

    /**
     * Create a new {@code PCTradeOfferBuilder}.
     */
    public static PCTradeOfferBuilder create() {
        return new PCTradeOfferBuilder();
    }

    /**
     * Set the offers of the novice stage.
     * @param offers The offers of the novice stage.
     * @throws NullPointerException If the {@code offers} is null or contains null elements.
     */
    public PCTradeOfferBuilder novice(@NotNull List<TradeOffers.Factory> offers) {
        CheckUtils.NullChecker.nonNullElements(offers);
        this.offers.get(VILLAGER_NOVICE_LEVEL)
            .addAll(offers);
        return this;
    }

    /**
     * Set the offers of the apprentice stage.
     * @param offers The offers of the apprentice stage.
     * @throws NullPointerException If the {@code offers} is null or contains null elements.
     */
    public PCTradeOfferBuilder apprentice(@NotNull List<TradeOffers.Factory> offers) {
        CheckUtils.NullChecker.nonNullElements(offers);
        this.offers.get(VILLAGER_APPRENTICE_LEVEL)
            .addAll(offers);
        return this;
    }

    /**
     * Set the offers of the journeyman stage.
     * @param offers The offers of the journeyman stage.
     * @throws NullPointerException If the {@code offers} is null or contains null elements.
     */
    public PCTradeOfferBuilder journeyman(@NotNull List<TradeOffers.Factory> offers) {
        CheckUtils.NullChecker.nonNullElements(offers);
        this.offers.get(VILLAGER_JOURNEYMAN_LEVEL)
            .addAll(offers);
        return this;
    }

    /**
     * Set the offers of the expert stage.
     * @param offers The offers of the expert stage.
     * @throws NullPointerException If the {@code offers} is null or contains null elements.
     */
    public PCTradeOfferBuilder expert(@NotNull List<TradeOffers.Factory> offers) {
        CheckUtils.NullChecker.nonNullElements(offers);
        this.offers.get(VILLAGER_EXPERT_LEVEL)
            .addAll(offers);
        return this;
    }

    /**
     * Set the offers of the master stage.
     * @param offers The offers of the master stage.
     * @throws NullPointerException If the {@code offers} is null or contains null elements.
     */
    public PCTradeOfferBuilder master(@NotNull List<TradeOffers.Factory> offers) {
        CheckUtils.NullChecker.nonNullElements(offers);
        this.offers.get(VILLAGER_MASTER_LEVEL)
            .addAll(offers);
        return this;
    }

    /**
     * Apply the offers to the given villager profession.
     * @param profession The profession of the villager.
     * @see PCVillagerRegister
     * @throws NullPointerException If the {@code profession} is null.
     */
    public void applyToProfession(@NotNull VillagerProfession profession) {
        Objects.requireNonNull(profession, "profession");
        offers.forEach(
            (level, offers) -> TradeOfferHelper.registerVillagerOffers(
                profession,
                level,
                factories -> factories.addAll(offers)
            )
        );
    }


}
