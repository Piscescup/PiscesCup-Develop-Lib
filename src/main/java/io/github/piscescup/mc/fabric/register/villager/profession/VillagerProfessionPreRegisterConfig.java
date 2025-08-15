package io.github.piscescup.mc.fabric.register.villager.profession;

import com.google.common.collect.ImmutableSet;
import io.github.piscescup.mc.fabric.register.PreRegisterConfig;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.SoundEvent;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A {@link PreRegisterConfig} for {@link VillagerProfession}.
 * @author REN YuanTong
 * @Date 2025-07-31
 * @since 1.1.2
 */
public interface VillagerProfessionPreRegisterConfig
{
    /**
     * Set the workstation of the villager.
     */
    interface WorkStationConfig {
        /**
         * Set the workstation of the villager.
         * @param heldWorkstation The workstation of the villager.
         * @return An optional config for the villager, such as acquirable workstation, gatherable items, secondary job sites and work sound.
         * @throws NullPointerException If the {@code heldWorkstation} is null.
         */
        OptionalConfig heldWorkstation(
            @NotNull RegistryKey<PointOfInterestType> heldWorkstation
        );
    }

    /**
     * Set the optional config of the villager: acquirable workstation, gatherable items, secondary job sites and work sound.
     */
    interface OptionalConfig
        extends PreRegisterConfig<VillagerProfessionPostRegisterConfig>
    {
        /**
         * Set the acquirable workstation of the villager.
         * @param acquirableWorkstation The acquirable workstation of the villager.
         * @throws NullPointerException If the {@code acquirableWorkstation} is null.
         */
        OptionalConfig acquirableWorkstation(
            @NotNull RegistryKey<PointOfInterestType> acquirableWorkstation
        );

        /**
         * Set the gatherable items of the villager.
         * @param items The gatherable items of the villager.
         * @throws NullPointerException If the {@code items} is null.
         */
        OptionalConfig gatherableItems(@NotNull ImmutableSet<Item> items);

        /**
         * Set the secondary job sites of the villager.
         * @param blocks The secondary job sites of the villager.
         * @throws NullPointerException If the {@code blocks} is null.
         */
        OptionalConfig secondaryJobSites(@NotNull ImmutableSet<Block> blocks);

        /**
         * Set the work sound of the villager.
         * @param soundEvent The work sound of the villager, may be {@code null}.
         */
        OptionalConfig workSound(@Nullable SoundEvent soundEvent);
    }

}
