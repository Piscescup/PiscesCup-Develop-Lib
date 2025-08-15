package io.github.piscescup.mc.fabric.register.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;


/**
 * <h2>Description</h2>
 * A util for registering {@code SoundEvent} in <b>Minecraft</b>.
 *
 * <h2>Usages</h2>
 * <p>
 *     This util provides some static method to create {@link SoundEvent} Register by the given parameters,
 *     such as {@link Identifier}, {@code fullPath} and {@code namespace:path}.
 * </p>
 * <p>
 *     This util also provides some static method to create {@link SoundEvent} Register which belongs to special repository.<br>
 *     Such as {@link #createItemSoundEvent}, {@link #createBlockSoundEvent}, {@link #createEntitySoundEvent} and so on.<br>
 * </p>
 * <p>
 *     Below are some examples:
 * </p>
 * <pre>{@code
 * public static final SoundEvent TEST1 = PCSoundEventRegister.createItemSoundEvent(MOD_ID, "pc_pickup")
 *     .registerAndBuild()
 *     .get();
 * }</pre>
 * <p>
 *     There is also a way to create a custom repository: use the overload method
 *     {@link #createSoundEvent(String, SoundEventRepositoryElement) createSoundEvent(..., SoundEventRepositoryElement)}.<br>
 *     The {@link SoundEventRepositoryElement} is a functional interface whose functional method is
 *     {@link SoundEventRepositoryElement#apply(Identifier) apply(Identifier) -> Identifier}.
 * </p>
 * <p>
 *     Below is an example:
 * </p>
 * <pre>{@code
 * public static final SoundEvent TEST1 = PCSoundEventRegister.createSoundEvent(MOD_ID, "pc_pickup", id -> Identifier.of(id.getNamespace(), "custom." + id.getPath()))
 *     .registerAndBuild()
 *     .get();
 * }</pre>
 *
 * @author REN YuanTong
 * @Date 2025-07-19
 * @since 1.1.2
 * @see SoundEventRepository
 */
public final class PCSoundEventRegister
    extends PCSoundEventBaseRegister<SoundEvent>
{
    private PCSoundEventRegister(Identifier id) {
        super(id);
    }

    /**
     * Creates a {@code SoundEvent} configuration with the given identifier.
     *
     * @param id The identifier for the {@code SoundEvent}
     */
    @Contract("_ -> new")
    @NotNull
    public static SoundEventPreRegisterConfig<SoundEvent> createSoundEvent(Identifier id) {
        return new PCSoundEventRegister(id);
    }

    /**
     * Creates a {@code SoundEvent} configuration from a full path string.
     *
     * @param fullPath The full path of the {@code SoundEvent}.
     */
    @Contract("_ -> new")
    @NotNull
    public static SoundEventPreRegisterConfig<SoundEvent> createSoundEvent(String fullPath) {
        return new PCSoundEventRegister(Identifier.tryParse(fullPath));
    }

    /**
     * Creates a {@code SoundEvent} configuration with namespace and path.
     *
     * @param namespace The namespace for the {@code SoundEvent}
     * @param path The path for the {@code SoundEvent}
     */
    @Contract("_, _ -> new")
    @NotNull
    public static SoundEventPreRegisterConfig<SoundEvent> createSoundEvent(String namespace, String path) {
        return new PCSoundEventRegister(Identifier.of(namespace, path));
    }

    /**
     * Creates a {@code SoundEvent} configuration with custom repository transformation.
     *
     * @param id The identifier of the {@code SoundEvent}.
     * @param repository The repository of the {@code SoundEvent}.
     */
    @Contract("_, _ -> new")
    @NotNull
    public static SoundEventPreRegisterConfig<SoundEvent> createSoundEvent(Identifier id, @NotNull SoundEventRepositoryElement repository) {
        return new PCSoundEventRegister(repository.apply(id));
    }

    /**
     * Creates an item-related {@code SoundEvent} configuration.
     *
     * @param id The identifier for the item {@code SoundEvent}
     */
    @Contract("_ -> new")
    public static @NotNull SoundEventPreRegisterConfig<SoundEvent> createItemSoundEvent(Identifier id) {
        return createSoundEvent(id, SoundEventRepository.SOUND_EVENT_REPOSITORY_ITEM);
    }

    /**
     * Creates a block-related {@code SoundEvent} configuration.
     *
     * @param id The identifier for the block {@code SoundEvent}
     */
    @Contract("_ -> new")
    public static @NotNull SoundEventPreRegisterConfig<SoundEvent> createBlockSoundEvent(Identifier id) {
        return createSoundEvent(id, SoundEventRepository.SOUND_EVENT_REPOSITORY_BLOCK);
    }

    /**
     * Creates an entity-related {@code SoundEvent} configuration.
     *
     * @param id The identifier for the entity {@code SoundEvent}
     */
    @Contract("_ -> new")
    public static @NotNull SoundEventPreRegisterConfig<SoundEvent> createEntitySoundEvent(Identifier id) {
        return createSoundEvent(id, SoundEventRepository.SOUND_EVENT_REPOSITORY_ENTITY);
    }

    /**
     * Creates a music-related {@code SoundEvent} configuration.
     *
     * @param id The identifier for the music {@code SoundEvent}
     */
    @Contract("_ -> new")
    public static @NotNull SoundEventPreRegisterConfig<SoundEvent> createMusicSoundEvent(Identifier id) {
        return createSoundEvent(id, SoundEventRepository.SOUND_EVENT_REPOSITORY_MUSIC);
    }

    /**
     * Creates a UI-related {@code SoundEvent} configuration.
     *
     * @param id The identifier for the UI {@code SoundEvent}
     */
    @Contract("_ -> new")
    public static @NotNull SoundEventPreRegisterConfig<SoundEvent> createUISoundEvent(Identifier id) {
        return createSoundEvent(id, SoundEventRepository.SOUND_EVENT_REPOSITORY_UI);
    }

    /**
     * Creates a weather-related {@code SoundEvent} configuration.
     *
     * @param id The identifier for the weather {@code SoundEvent}
     */
    @Contract("_ -> new")
    public static @NotNull SoundEventPreRegisterConfig<SoundEvent> createWeatherSoundEvent(Identifier id) {
        return createSoundEvent(id, SoundEventRepository.SOUND_EVENT_REPOSITORY_WEATHER);
    }

    /**
     * Creates an ambient {@code SoundEvent} configuration.
     *
     * @param id The identifier for the ambient {@code SoundEvent}
     */
    @Contract("_ -> new")
    public static @NotNull SoundEventPreRegisterConfig<SoundEvent> createAmbientSoundEvent(Identifier id) {
        return createSoundEvent(id, SoundEventRepository.SOUND_EVENT_REPOSITORY_AMBIENT);
    }

    /**
     * Creates an item {@code SoundEvent} from a full path string.
     *
     * @param fullPath The full path string for the item {@code SoundEvent}.
     */
    @Contract("_ -> new")
    public static @NotNull SoundEventPreRegisterConfig<SoundEvent> createItemSoundEvent(String fullPath) {
        return createSoundEvent(fullPath, SoundEventRepository.SOUND_EVENT_REPOSITORY_ITEM);
    }

    /**
     * Creates a block {@code SoundEvent} from a full path string.
     *
     * @param fullPath The full path string for the block {@code SoundEvent}.
     */
    @Contract("_ -> new")
    public static @NotNull SoundEventPreRegisterConfig<SoundEvent> createBlockSoundEvent(String fullPath) {
        return createSoundEvent(fullPath, SoundEventRepository.SOUND_EVENT_REPOSITORY_BLOCK);
    }

    /**
     * Creates an entity {@code SoundEvent} from a full path string.
     *
     * @param fullPath The full path string for the entity {@code SoundEvent}.
     */
    @Contract("_ -> new")
    public static @NotNull SoundEventPreRegisterConfig<SoundEvent> createEntitySoundEvent(String fullPath) {
        return createSoundEvent(fullPath, SoundEventRepository.SOUND_EVENT_REPOSITORY_ENTITY);
    }

    /**
     * Creates a music {@code SoundEvent} from a full path string.
     *
     * @param fullPath The full path string for the music {@code SoundEvent}.
     */
    @Contract("_ -> new")
    public static @NotNull SoundEventPreRegisterConfig<SoundEvent> createMusicSoundEvent(String fullPath) {
        return createSoundEvent(fullPath, SoundEventRepository.SOUND_EVENT_REPOSITORY_MUSIC);
    }

    /**
     * Creates a UI {@code SoundEvent} from a full path string.
     *
     * @param fullPath The full path string for the UI {@code SoundEvent}.
     */
    @Contract("_ -> new")
    public static @NotNull SoundEventPreRegisterConfig<SoundEvent> createUISoundEvent(String fullPath) {
        return createSoundEvent(fullPath, SoundEventRepository.SOUND_EVENT_REPOSITORY_UI);
    }

    /**
     * Creates a weather {@code SoundEvent} from a full path string.
     *
     * @param fullPath The full path string for the weather {@code SoundEvent}.
     */
    @Contract("_ -> new")
    public static @NotNull SoundEventPreRegisterConfig<SoundEvent> createWeatherSoundEvent(String fullPath) {
        return createSoundEvent(fullPath, SoundEventRepository.SOUND_EVENT_REPOSITORY_WEATHER);
    }

    /**
     * Creates an ambient {@code SoundEvent} from a full path string.
     *
     * @param fullPath The full path string for the ambient {@code SoundEvent}.
     */
    @Contract("_ -> new")
    public static @NotNull SoundEventPreRegisterConfig<SoundEvent> createAmbientSoundEvent(String fullPath) {
        return createSoundEvent(fullPath, SoundEventRepository.SOUND_EVENT_REPOSITORY_AMBIENT);
    }

    /**
     * Creates an item {@code SoundEvent} with namespace and path.
     *
     * @param namespace The namespace for the item {@code SoundEvent}.
     * @param path The path for the item {@code SoundEvent}.
     */
    @Contract("_, _ -> new")
    public static @NotNull SoundEventPreRegisterConfig<SoundEvent> createItemSoundEvent(String namespace, String path) {
        return createSoundEvent(namespace, path, SoundEventRepository.SOUND_EVENT_REPOSITORY_ITEM);
    }

    /**
     * Creates a block {@code SoundEvent} with namespace and path.
     *
     * @param namespace The namespace for the block {@code SoundEvent}.
     * @param path The path for the block {@code SoundEvent}.
     */
    @Contract("_, _ -> new")
    public static @NotNull SoundEventPreRegisterConfig<SoundEvent> createBlockSoundEvent(String namespace, String path) {
        return createSoundEvent(namespace, path, SoundEventRepository.SOUND_EVENT_REPOSITORY_BLOCK);
    }

    /**
     * Creates an entity {@code SoundEvent} with namespace and path.
     *
     * @param namespace The namespace for the entity {@code SoundEvent}.
     * @param path The path for the entity {@code SoundEvent}.
     */
    @Contract("_, _ -> new")
    public static @NotNull SoundEventPreRegisterConfig<SoundEvent> createEntitySoundEvent(String namespace, String path) {
        return createSoundEvent(namespace, path, SoundEventRepository.SOUND_EVENT_REPOSITORY_ENTITY);
    }

    /**
     * Creates a music {@code SoundEvent} with namespace and path.
     *
     * @param namespace The namespace for the music {@code SoundEvent}.
     * @param path The path for the music {@code SoundEvent}.
     */
    @Contract("_, _ -> new")
    public static @NotNull SoundEventPreRegisterConfig<SoundEvent> createMusicSoundEvent(String namespace, String path) {
        return createSoundEvent(namespace, path, SoundEventRepository.SOUND_EVENT_REPOSITORY_MUSIC);
    }

    /**
     * Creates a UI {@code SoundEvent} with namespace and path.
     *
     * @param namespace The namespace for the UI {@code SoundEvent}.
     * @param path The path for the UI {@code SoundEvent}.
     */
    @Contract("_, _ -> new")
    public static @NotNull SoundEventPreRegisterConfig<SoundEvent> createUISoundEvent(String namespace, String path) {
        return createSoundEvent(namespace, path, SoundEventRepository.SOUND_EVENT_REPOSITORY_UI);
    }

    /**
     * Creates a weather {@code SoundEvent} with namespace and path.
     *
     * @param namespace The namespace for the weather {@code SoundEvent}.
     * @param path The path for the weather {@code SoundEvent}.
     */
    @Contract("_, _ -> new")
    public static @NotNull SoundEventPreRegisterConfig<SoundEvent> createWeatherSoundEvent(String namespace, String path) {
        return createSoundEvent(namespace, path, SoundEventRepository.SOUND_EVENT_REPOSITORY_WEATHER);
    }

    /**
     * Creates an ambient {@code SoundEvent} with namespace and path.
     *
     * @param namespace The namespace for the ambient {@code SoundEvent}.
     * @param path The path for the ambient {@code SoundEvent}.
     */
    @Contract("_, _ -> new")
    public static @NotNull SoundEventPreRegisterConfig<SoundEvent> createAmbientSoundEvent(String namespace, String path) {
        return createSoundEvent(namespace, path, SoundEventRepository.SOUND_EVENT_REPOSITORY_AMBIENT);
    }

    /**
     * Creates a {@code SoundEvent} from a full path string with custom repository.
     *
     * @param fullPath The full path string to parse.
     * @param repository The repository to transform the identifier.
     */
    @Contract("_, _ -> new")
    @NotNull
    public static SoundEventPreRegisterConfig<SoundEvent> createSoundEvent(String fullPath, @NotNull SoundEventRepositoryElement repository) {
        Identifier id = Identifier.tryParse(fullPath);
        id = repository.apply(id);
        return new PCSoundEventRegister(id);
    }

    /**
     * Creates a {@code SoundEvent} with namespace, path, and custom repository.
     *
     * @param namespace The namespace for the {@code SoundEvent}.
     * @param path The path for the {@code SoundEvent}.
     * @param repository The repository to transform the identifier.
     */
    @Contract("_, _, _ -> new")
    @NotNull
    public static SoundEventPreRegisterConfig<SoundEvent> createSoundEvent(String namespace, String path, @NotNull SoundEventRepositoryElement repository) {
        Identifier id = Identifier.of(namespace, path);
        id = repository.apply(id);
        return new PCSoundEventRegister(id);
    }

    /**
     * Registers the {@code SoundEvent} in the Minecraft registry.
     */
    @Override
    public SoundEventPostRegisterConfig<SoundEvent> registerAndBuild() {
        this.targetRegistered = Registry.register(
            Registries.SOUND_EVENT,
            this.id,
            SoundEvent.of(this.id)
        );

        return this;
    }
}
