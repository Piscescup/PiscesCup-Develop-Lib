package io.github.piscescup.mc.fabric.register;

import io.github.piscescup.mc.fabric.datagen.lang.map.LangMap;
import io.github.piscescup.mc.fabric.register.block.BlockPostRegisterConfig;
import io.github.piscescup.mc.fabric.register.item.ItemPostRegisterConfig;
import io.github.piscescup.mc.fabric.register.itemgroup.ItemGroupPostRegisterConfig;
import io.github.piscescup.mc.fabric.register.loot.LootTablePostRegisterConfig;
import io.github.piscescup.mc.fabric.register.sound.SoundEventPostRegisterConfig;
import io.github.piscescup.mc.fabric.register.tag.TagKeyPostRegisterConfig;
import io.github.piscescup.mc.fabric.register.villager.VillagerPostRegisterConfig;
import io.github.piscescup.mc.fabric.register.poi.POIPostRegisterConfig;
import io.github.piscescup.mc.fabric.register.villager.profession.VillagerProfessionPostRegisterConfig;
import io.github.piscescup.mc.fabric.utils.constant.Language;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Represents the second and final stage of a configuration process, accessible
 * after a primary registration({@link PreRegisterConfig}) has occurred.
 * <p>
 * This interface is part of a two-stage builder pattern, following the completion of a
 * {@link PreRegisterConfig} stage. It provides methods for fine-tuning the configuration
 * of an object that has already been registered with a parent component.<br>
 * Common post-registration tasks include setting optional parameters, applying translations,
 * or adding the object to collections.
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
 * @param <C> The sub interface of the {@link PostRegisterConfig}.
 * @param <R> The type of the registrar or parent component that manages the registered object {@code T}.
 *       It is constrained to be a {@link PCRegister}, linking it to both the object and its configurator.
 * @param <T> The type of the target which the Register {@link R} registers.
 *
 * @see PreRegisterConfig
 * @see PCRegister
 * @see ItemPostRegisterConfig
 * @see BlockPostRegisterConfig
 * @see ItemGroupPostRegisterConfig
 * @see LootTablePostRegisterConfig
 * @see SoundEventPostRegisterConfig
 * @see TagKeyPostRegisterConfig
 * @see POIPostRegisterConfig
 * @see VillagerProfessionPostRegisterConfig
 * @see VillagerPostRegisterConfig
 *
 * @author REN YuanTong
 * @since 1.1.2
 */
public interface PostRegisterConfig<C extends PostRegisterConfig<C, T, R>, T, R extends PCRegister<T, R, C>> {
    public static final LangMap LANG_MAP = LangMap.instance();

    /**
     * Get the target which the Register {@link R} registers.
     * @return The target which the Register {@link R} registers.
     */
    T get();

    /**
     * Get the identifier of the target which the Register {@link R} registers.
     * @return The identifier of the target which the Register {@link R} registers.
     */
    Identifier id();

    /**
     * Get the {@link RegistryKey<T> RegistryKey<T>} of the target which the Register {@link R} registers.
     * @return The {@link RegistryKey<T> RegistryKey<T>} of the target which the Register {@link R} registers.
     */
    RegistryKey<T> key();

    /**
     * Get the non-null register instance of type {@link R}.
     * @return The non-null register instance of type {@link R}.
     */
    @SuppressWarnings("unchecked")
    default R getRegister() {
        return (R) this;
    }

    /**
     * Applies a language-specific translation or value to the configured object.
     * <p>
     * This method is typically used for internationalization (i18n), allowing properties like
     * display names or descriptions to be set for a specific {@link Language}.
     * <p>
     *     This method is based on the abstract method: {@link #get()}
     * </p>
     *
     * @see Language
     * @param lang  The language for which the translation is being provided.
     * @param value The translated string value to associate with the given language.
     * @return This configurer instance ({@code this}), for fluent chaining.
     */
    @SuppressWarnings("unchecked")
    default C translate(Language lang, String value) {
        // LangMap.instance().put(lang, get(), value);
        LANG_MAP.put(lang, get(), value);

        return (C) this;
    }

    /**
     * Adds the target which the Register {@link R} registers (retrieved via {@link #get()}) to the given list.
     * <
     * @param list The list to which the registered object should be added. Must not be null.
     * @return This configurer instance ({@code this}), to allow for method chaining.
     * @throws NullPointerException If the list is null.
     */
    @SuppressWarnings("unchecked")
    default C addToList(@NotNull List<T> list) {
        list.add(get());
        return (C) this;
    }
}
