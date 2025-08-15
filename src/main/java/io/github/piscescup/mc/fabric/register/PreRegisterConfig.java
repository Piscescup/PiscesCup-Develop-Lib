package io.github.piscescup.mc.fabric.register;

import io.github.piscescup.mc.fabric.register.block.BlockPreRegisterConfig;
import io.github.piscescup.mc.fabric.register.item.ItemPreRegisterConfig;
import io.github.piscescup.mc.fabric.register.itemgroup.ItemGroupPreRegisterConfig;
import io.github.piscescup.mc.fabric.register.loot.BlockLootTablePreRegisterConfig;
import io.github.piscescup.mc.fabric.register.loot.LootTablePreRegisterConfig;
import io.github.piscescup.mc.fabric.register.sound.SoundEventPreRegisterConfig;
import io.github.piscescup.mc.fabric.register.tag.TagKeyPreRegisterConfig;
import io.github.piscescup.mc.fabric.register.villager.VillagerPreRegisterConfig;
import io.github.piscescup.mc.fabric.register.poi.POIPreRegisterConfig;
import io.github.piscescup.mc.fabric.register.villager.profession.VillagerProfessionPreRegisterConfig;

/**
 * Represents the initial stage of a configuration process that requires a central registration step.
 * <p>
 * This interface is part of a two-stage builder pattern. It is designed to gather all necessary
 * settings *before* an object or component is formally registered. Once configured, the
 * {@link #registerAndBuild()} method is called to perform the registration and transition
 * to the second stage, represented by the {@link PostRegisterConfig} interface.
 * <p>
 * This pattern ensures a clean separation of concerns and guarantees that essential
 * initialization logic is executed before dependent configurations are applied. For example,
 * one might first configure a service's name and endpoint (pre-registration), then register
 * it with a central registry, which in turn returns a new configurer for setting up
 * caching and timeout policies (post-registration).
 *
 * <p>
 *     This interface is designed to be used in the subclass {@code Register} of {@link PCRegister},
 *     and the subclass {@code  Register} must implement this interface.
 * </p>
 * <p>
 *     Below is an example definition of a Register class:
 * </p>
 * <pre>{@code
 * public class PCItemRegister
 *     extends PCRegister<Item, PCItemRegister, ItemPostRegisterConfig<PCItemRegister>>
 *     implements ItemPreRegisterConfig<PCItemRegister>, ItemPostRegisterConfig<PCItemRegister>
 * }</pre>
 *
 * @param <C> The type of the post-registration configuration object that will be returned.
 *            This type must extend {@link PostRegisterConfig}.
 *
 * @see PCRegister
 * @see PostRegisterConfig
 * @see ItemPreRegisterConfig
 * @see BlockPreRegisterConfig
 * @see ItemGroupPreRegisterConfig
 * @see BlockLootTablePreRegisterConfig
 * @see LootTablePreRegisterConfig
 * @see SoundEventPreRegisterConfig
 * @see TagKeyPreRegisterConfig
 * @see POIPreRegisterConfig
 * @see VillagerProfessionPreRegisterConfig
 * @see VillagerPreRegisterConfig
 * @author REN YuanTong
 * @since 1.1.2
 * @Date 2025-07-31
 */
public interface PreRegisterConfig<C extends PostRegisterConfig<C, ?, ?>> {

    /**
     * Finalizes the pre-registration settings, performs the registration action, and
     * returns a new configurer for the subsequent configuration phase.
     * <p>
     * This method acts as the bridge between the initial setup and the post-registration setup.
     * It uses the properties configured on this instance to perform a core action (e.g.,
     * registering a component with a manager or a listener with an event bus).
     * Upon successful registration, it returns a new {@link PostRegisterConfig} instance,
     * unlocking further configuration options that were dependent on the registration.
     * <p>
     * This is typically a terminal operation for the {@code PreRegisterConfig} stage and should
     * only be called once.
     *
     * @return A new, non-null instance of the {@link PostRegisterConfig} for subsequent configuration.
     * @see PostRegisterConfig
     */
    C registerAndBuild();
}
