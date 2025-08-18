package io.github.piscescup.mc.fabric.register.villager.profession;

import com.google.common.collect.ImmutableSet;
import io.github.piscescup.mc.fabric.register.PCRegister;
import io.github.piscescup.mc.fabric.utils.CheckUtils;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.Predicate;

import static io.github.piscescup.mc.fabric.References.MOD_LOGGER;

/**
 * <h2>Description</h2>
 * A register util for {@link VillagerProfession} in <strong>Minecraft</strong>.
 * <h2>Usages</h2>
 * <p>
 *     You can use the method: {@link #createFor} to createFor a {@link PCVillagerProfessionRegister} with the given {@link Identifier}.
 * </p>
 *
 * <p>
 *     You must use the method: {@link #heldWorkstation} to set the held workstation.
 *     Otherwise, an exception will be thrown.
 * </p>
 * <pre>{@code
 * public static final VillagerProfession FIRE_WORK_VILLAGER =
 *     PCVillagerProfessionRegister.createFor(MOD_ID, "fire_worker")
 *         .heldWorkstation(ModVillagerPOIs.FIREWORK_VILLAGER_TEST_POI_1.key())
 *         .workSound(SoundEvents.ENTITY_FIREWORK_ROCKET_LAUNCH)
 *         .registerAndBuild()
 *         .get();
 * }</pre>
 * <p>
 *     And then register the class in the mod entry:
 * </p>
 * <pre>{@code
 * public class PiscesCupDevelopLib implements ModInitializer {
 * 	            @Override
 *    public void onInitialize() {
 * 		// This code runs as soon as Minecraft is in a mod-load-ready state.
 * 		// However, some things (like resources) may still be uninitialized.
 * 		// Proceed with mild caution.
 * 		MOD_LOGGER.info("Hello Fabric world!");
 * 		MOD_LOGGER.info("Hello, " + MOD_NAME);
 * 		ModItems.register();
 * 		ModBlocks.register();
 * 		ModItemGroups.register();
 * 		ModTags.register();
 * 		ModAdvancements.register();
 * 		KeyAction.registerTranslations();
 *
 * 		ModVillagerPOIs.register();
 * 		ModVillagerProfessions.register();
 * 		ModVillagers.register();
 * 		ModVillagerTrades.register();
 * 		MOD_LOGGER.info("Finish registering");
 *    }
 * }
 * }</pre>
 * @author REN YuanTong
 * @Date 2025-07-02
 * @since 1.1.0
 */
public final class PCVillagerProfessionRegister
    extends PCRegister<RegistryKey<VillagerProfession>, PCVillagerProfessionRegister, VillagerProfessionPostRegisterConfig>
    implements VillagerProfessionPreRegisterConfig.WorkStationConfig, VillagerProfessionPreRegisterConfig.OptionalConfig, VillagerProfessionPostRegisterConfig
{
    private Predicate<RegistryEntry<PointOfInterestType>> heldWorkstation;
    private Predicate<RegistryEntry<PointOfInterestType>> acquirableWorkstation;
    private ImmutableSet<Item> gatherableItems = ImmutableSet.of();
    private ImmutableSet<Block> secondaryJobSites = ImmutableSet.of();
    @Nullable
    private SoundEvent workSound;

    private PCVillagerProfessionRegister(Identifier id) {
        super(id);
    }

    @Contract("_ -> new")
    public static @NotNull VillagerProfessionPreRegisterConfig.WorkStationConfig createFor(String professionName) {
        return new PCVillagerProfessionRegister(Identifier.ofVanilla(professionName));
    }

    @Override
    public VillagerProfessionPostRegisterConfig registerAndBuild() {
        this.targetRegistered = RegistryKey.of(
            RegistryKeys.VILLAGER_PROFESSION,
            this.id
        );
        Registry.register(
            Registries.VILLAGER_PROFESSION,
            this.id,
            new VillagerProfession(
                Text.translatable("entity.minecraft.villager." + this.key.getValue().getPath()),
                this.heldWorkstation,
                this.acquirableWorkstation,
                this.gatherableItems,
                this.secondaryJobSites,
                this.workSound
            )
        );
        return this;
    }


    @Override
    public VillagerProfessionPreRegisterConfig.OptionalConfig heldWorkstation(
        @NotNull RegistryKey<PointOfInterestType> heldWorkstation
    ) {
        Objects.requireNonNull(heldWorkstation);
        this.heldWorkstation =
            entry -> entry.matchesKey(heldWorkstation);
        this.acquirableWorkstation = this.heldWorkstation;
        return this;
    }

    @Override
    public VillagerProfessionPreRegisterConfig.OptionalConfig acquirableWorkstation(
        @NotNull RegistryKey<PointOfInterestType> acquirableWorkstation
    ) {
        Objects.requireNonNull(acquirableWorkstation);
        this.acquirableWorkstation =
            entry -> entry.matchesKey(acquirableWorkstation);
        return this;
    }

    @Override
    public VillagerProfessionPreRegisterConfig.OptionalConfig gatherableItems(@NotNull ImmutableSet<Item> items) {
        CheckUtils.NullChecker.nonNullElements(items);
        this.gatherableItems = items;
        return this;
    }

    @Override
    public VillagerProfessionPreRegisterConfig.OptionalConfig secondaryJobSites(@NotNull ImmutableSet<Block> blocks) {
        CheckUtils.NullChecker.nonNullElements(blocks);
        this.secondaryJobSites = blocks;
        return this;
    }

    @Override
    public VillagerProfessionPreRegisterConfig.OptionalConfig workSound(@Nullable SoundEvent soundEvent) {
        if (CheckUtils.NullChecker.isNull(soundEvent)) {
            MOD_LOGGER.warn(
                "The work sound for the VillagerProfession {} is null.",
                this.id
            );
        }
        this.workSound = soundEvent;
        return this;
    }


}
