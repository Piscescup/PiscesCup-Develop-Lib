package io.github.piscescup.mc.fabric.register.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * <h2>Description</h2>
 * A util for registering {@code RegistryEntry.Reference<SoundEvent>} in <b>Minecraft</b>.
 *
 * <h2>Usages</h2>
 * <p>
 *     This util provides static methods to create a configuration for a {@code RegistryEntry.Reference<SoundEvent>}
 *     using the given parameters, such as an {@link Identifier}, a {@code fullPath} string, or a {@code namespace:path} pair.
 * </p>
 * <p>
 *     This util also provides static methods to create a {@code RegistryEntry.Reference<SoundEvent>} that belongs to a
 *     special repository. These methods, such as {@link #createItemSoundEventRegistryReference}, {@link #createBlockSoundEventRegistryReference},
 *     and {@link #createEntitySoundEventRegistryReference}, apply a predefined path prefix.
 * </p>
 * <p>
 *     Below is an example of typical usage:
 * </p>
 * <pre>{@code
 * public static final RegistryEntry.Reference<SoundEvent> TEST1 = PCSoundEventRegRefRegister.createItemSoundEventRegistryReference(MOD_ID, "pc_pickup")
 *     .registerAndBuild()
 *     .get();
 * }</pre>
 * <p>
 *     You can also create a custom repository using the
 *     {@link #createSoundEventRegistryReference(Identifier, SoundEventRepositoryElement) createSoundEventRegistryReference(..., SoundEventRepositoryElement)}
 *     overload. The {@link SoundEventRepositoryElement} is a functional interface that allows you to define a custom transformation for the sound event's {@link Identifier}.
 * </p>
 * <p>
 *     Below is an example of using a custom repository:
 * </p>
 * <pre>{@code
 * public static final RegistryEntry.Reference<SoundEvent> TEST1 = PCSoundEventRegRefRegister.createSoundEventRegistryReference(MOD_ID, "pc_pickup", id -> Identifier.of(id.getNamespace(), "custom." + id.getPath()))
 *     .registerAndBuild()
 *     .get();
 * }</pre>
 *
 * @author REN YuanTong
 * @Date 2025-07-19
 * @since 1.1.2
 * @see SoundEventRepository
 * @see PCSoundEventRegister
 * @see RegistryEntry.Reference
 */
public final class PCSoundEventRegRefRegister
    extends PCSoundEventBaseRegister<RegistryEntry.Reference<SoundEvent>>
{
    private PCSoundEventRegRefRegister(Identifier id) {
        super(id);
    }

    /**
     * Create a {@code RegistryEntry.Reference<SoundEvent>} using the given {@code Identifier}.
     * @param id The identifier of the {@code RegistryEntry.Reference<SoundEvent>}.
     */
    @Contract("_ -> new")
    @NotNull
    public static SoundEventPreRegisterConfig<RegistryEntry.Reference<SoundEvent>> createSoundEventRegistryReference(Identifier id) {
        return createSoundEventRegistryReference(id, SoundEventRepository.SOUND_EVENT_REPOSITORY_EMPTY);
    }

    /**
     * Create a {@code RegistryEntry.Reference<SoundEvent>} using the given {@code fullPath} string.
     * @param fullPath The full path of the {@code RegistryEntry.Reference<SoundEvent>}.
     */
    @Contract("_ -> new")
    @NotNull
    public static SoundEventPreRegisterConfig<RegistryEntry.Reference<SoundEvent>> createSoundEventRegistryReference(String fullPath) {
        return createSoundEventRegistryReference(fullPath, SoundEventRepository.SOUND_EVENT_REPOSITORY_EMPTY);
    }

    /**
     * Create a {@code RegistryEntry.Reference<SoundEvent>} using the given {@code namespace} and {@code path}.
     * @param namespace The namespace of the {@code RegistryEntry.Reference<SoundEvent>}.
     * @param path The path of the {@code RegistryEntry.Reference<SoundEvent>}.
     */
    @Contract("_, _ -> new")
    @NotNull
    public static SoundEventPreRegisterConfig<RegistryEntry.Reference<SoundEvent>> createSoundEventRegistryReference(String namespace, String path) {
        return createSoundEventRegistryReference(namespace, path, SoundEventRepository.SOUND_EVENT_REPOSITORY_EMPTY);
    }

    /**
     * Create a {@code RegistryEntry.Reference<SoundEvent>} belonging to {@code item} by the given {@link Identifier} .
     * @param id The identifier of the {@code RegistryEntry.Reference<SoundEvent>}.
     */
    @Contract("_ -> new")
    public static @NotNull SoundEventPreRegisterConfig<RegistryEntry.Reference<SoundEvent>> createItemSoundEventRegistryReference(Identifier id) {
        return createSoundEventRegistryReference(id, SoundEventRepository.SOUND_EVENT_REPOSITORY_ITEM);
    }

    /**
     * Create a {@code RegistryEntry.Reference<SoundEvent>} belonging to {@code block} by the given {@link Identifier} .
     * @param id The identifier of the {@code RegistryEntry.Reference<SoundEvent>}.
     */
    @Contract("_ -> new")
    public static @NotNull SoundEventPreRegisterConfig<RegistryEntry.Reference<SoundEvent>> createBlockSoundEventRegistryReference(Identifier id) {
        return createSoundEventRegistryReference(id, SoundEventRepository.SOUND_EVENT_REPOSITORY_BLOCK);
    }

    /**
     * Create a {@code RegistryEntry.Reference<SoundEvent>} belonging to {@code entity} by the given {@link Identifier} .
     * @param id The identifier of the {@code RegistryEntry.Reference<SoundEvent>}.
     */
    @Contract("_ -> new")
    public static @NotNull SoundEventPreRegisterConfig<RegistryEntry.Reference<SoundEvent>> createEntitySoundEventRegistryReference(Identifier id) {
        return createSoundEventRegistryReference(id, SoundEventRepository.SOUND_EVENT_REPOSITORY_ENTITY);
    }

    /**
     * Create a {@code RegistryEntry.Reference<SoundEvent>} belonging to {@code music} by the given {@link Identifier} .
     * @param id The identifier of the {@code RegistryEntry.Reference<SoundEvent>}.
     */
    @Contract("_ -> new")
    public static @NotNull SoundEventPreRegisterConfig<RegistryEntry.Reference<SoundEvent>> createMusicSoundEventRegistryReference(Identifier id) {
        return createSoundEventRegistryReference(id, SoundEventRepository.SOUND_EVENT_REPOSITORY_MUSIC);
    }

    /**
     * Create a {@code RegistryEntry.Reference<SoundEvent>} belonging to {@code ui} by the given {@link Identifier} .
     * @param id The identifier of the {@code RegistryEntry.Reference<SoundEvent>}.
     */
    @Contract("_ -> new")
    public static @NotNull SoundEventPreRegisterConfig<RegistryEntry.Reference<SoundEvent>> createUISoundEventRegistryReference(Identifier id) {
        return createSoundEventRegistryReference(id, SoundEventRepository.SOUND_EVENT_REPOSITORY_UI);
    }

    /**
     * Create a {@code RegistryEntry.Reference<SoundEvent>} belonging to {@code weather} by the given {@link Identifier} .
     * @param id The identifier of the {@code RegistryEntry.Reference<SoundEvent>}.
     */
    @Contract("_ -> new")
    public static @NotNull SoundEventPreRegisterConfig<RegistryEntry.Reference<SoundEvent>> createWeatherSoundEventRegistryReference(Identifier id) {
        return createSoundEventRegistryReference(id, SoundEventRepository.SOUND_EVENT_REPOSITORY_WEATHER);
    }

    /**
     * Create a {@code RegistryEntry.Reference<SoundEvent>} belonging to {@code ambient} by the given {@link Identifier} .
     * @param id The identifier of the {@code RegistryEntry.Reference<SoundEvent>}.
     */
    @Contract("_ -> new")
    public static @NotNull SoundEventPreRegisterConfig<RegistryEntry.Reference<SoundEvent>> createAmbientSoundEventRegistryReference(Identifier id) {
        return createSoundEventRegistryReference(id, SoundEventRepository.SOUND_EVENT_REPOSITORY_AMBIENT);
    }

    /**
     * Create a {@code RegistryEntry.Reference<SoundEvent>} belonging to {@code block} by the given {@code fullPath} string.
     * @param fullPath The full path of the {@code RegistryEntry.Reference<SoundEvent>}.
     */
    @Contract("_ -> new")
    public static @NotNull SoundEventPreRegisterConfig<RegistryEntry.Reference<SoundEvent>> createItemSoundEventRegistryReference(String fullPath) {
        return createSoundEventRegistryReference(
            fullPath,
            SoundEventRepository.SOUND_EVENT_REPOSITORY_ITEM
        );
    }

    /**
     * Create a {@code RegistryEntry.Reference<SoundEvent>} belonging to {@code block} by the given {@code fullPath} string.
     * @param fullPath The full path of the {@code RegistryEntry.Reference<SoundEvent>}.
     */
    @Contract("_ -> new")
    public static @NotNull SoundEventPreRegisterConfig<RegistryEntry.Reference<SoundEvent>> createBlockSoundEventRegistryReference(String fullPath) {
        return createSoundEventRegistryReference(
            fullPath,
            SoundEventRepository.SOUND_EVENT_REPOSITORY_BLOCK
        );
    }

    /**
     * Create a {@code RegistryEntry.Reference<SoundEvent>} belonging to {@code entity} by the given {@code fullPath} string.
     * @param fullPath The full path of the {@code RegistryEntry.Reference<SoundEvent>}.
     */
    @Contract("_ -> new")
    public static @NotNull SoundEventPreRegisterConfig<RegistryEntry.Reference<SoundEvent>> createEntitySoundEventRegistryReference(String fullPath) {
        return createSoundEventRegistryReference(
            fullPath,
            SoundEventRepository.SOUND_EVENT_REPOSITORY_ENTITY
        );
    }

    /**
     * Create a {@code RegistryEntry.Reference<SoundEvent>} belonging to {@code music} by the given {@code fullPath} string.
     * @param fullPath The full path of the {@code RegistryEntry.Reference<SoundEvent>}.
     */
    @Contract("_ -> new")
    public static @NotNull SoundEventPreRegisterConfig<RegistryEntry.Reference<SoundEvent>> createMusicSoundEventRegistryReference(String fullPath) {
        return createSoundEventRegistryReference(
            fullPath,
            SoundEventRepository.SOUND_EVENT_REPOSITORY_MUSIC
        );
    }

    /**
     * Create a {@code RegistryEntry.Reference<SoundEvent>} belonging to {@code ui} by the given {@code fullPath} string.
     * @param fullPath The full path of the {@code RegistryEntry.Reference<SoundEvent>}.
     */
    @Contract("_ -> new")
    public static @NotNull SoundEventPreRegisterConfig<RegistryEntry.Reference<SoundEvent>> createUISoundEventRegistryReference(String fullPath) {
        return createSoundEventRegistryReference(
            fullPath,
            SoundEventRepository.SOUND_EVENT_REPOSITORY_UI
        );
    }

    /**
     * Create a {@code RegistryEntry.Reference<SoundEvent>} belonging to {@code weather} by the given {@code fullPath} string.
     * @param fullPath The full path of the {@code RegistryEntry.Reference<SoundEvent>}.
     */
    @Contract("_ -> new")
    public static @NotNull SoundEventPreRegisterConfig<RegistryEntry.Reference<SoundEvent>> createWeatherSoundEvent(String fullPath) {
        return createSoundEventRegistryReference(
            fullPath,
            SoundEventRepository.SOUND_EVENT_REPOSITORY_WEATHER
        );
    }

    /**
     * Create a {@code RegistryEntry.Reference<SoundEvent>} belonging to {@code ambient} by the given {@code fullPath} string.
     * @param fullPath The full path of the {@code RegistryEntry.Reference<SoundEvent>}.
     */
    @Contract("_ -> new")
    public static @NotNull SoundEventPreRegisterConfig<RegistryEntry.Reference<SoundEvent>> createAmbientSoundEvent(String fullPath) {
        return createSoundEventRegistryReference(
            fullPath,
            SoundEventRepository.SOUND_EVENT_REPOSITORY_AMBIENT
        );
    }

    /**
     * Create a {@code RegistryEntry.Reference<SoundEvent>} belonging to {@code item} by the given {@code namespace} and {@code path} string.
     * @param namespace The namespace of the {@code RegistryEntry.Reference<SoundEvent>}.
     * @param path The path of the {@code RegistryEntry.Reference<SoundEvent>}.
     */
    @Contract("_, _ -> new")
    public static @NotNull SoundEventPreRegisterConfig<RegistryEntry.Reference<SoundEvent>> createItemSoundEvent(String namespace, String path) {
        return createSoundEventRegistryReference(
            namespace, path,
            SoundEventRepository.SOUND_EVENT_REPOSITORY_ITEM
        );
    }

    /**
     * Create a {@code RegistryEntry.Reference<SoundEvent>} belonging to {@code block} by the given {@code namespace} and {@code path} string.
     * @param namespace The namespace of the {@code RegistryEntry.Reference<SoundEvent>}.
     * @param path The path of the {@code RegistryEntry.Reference<SoundEvent>}.
     */
    @Contract("_, _ -> new")
    public static @NotNull SoundEventPreRegisterConfig<RegistryEntry.Reference<SoundEvent>> createBlockSoundEvent(String namespace, String path) {
        return createSoundEventRegistryReference(
            namespace, path,
            SoundEventRepository.SOUND_EVENT_REPOSITORY_BLOCK
        );
    }

    /**
     * Create a {@code RegistryEntry.Reference<SoundEvent>} belonging to {@code entity} by the given {@code namespace} and {@code path} string.
     * @param namespace The namespace of the {@code RegistryEntry.Reference<SoundEvent>}.
     * @param path The path of the {@code RegistryEntry.Reference<SoundEvent>}.
     */
    @Contract("_, _ -> new")
    public static @NotNull SoundEventPreRegisterConfig<RegistryEntry.Reference<SoundEvent>> createEntitySoundEvent(String namespace, String path) {
        return createSoundEventRegistryReference(
            Identifier.of(namespace, path),
            SoundEventRepository.SOUND_EVENT_REPOSITORY_ENTITY
        );
    }

    /**
     * Create a {@code RegistryEntry.Reference<SoundEvent>} belonging to {@code music} by the given {@code namespace} and {@code path} string.
     * @param namespace The namespace of the {@code RegistryEntry.Reference<SoundEvent>}.
     * @param path The path of the {@code RegistryEntry.Reference<SoundEvent>}.
     */
    @Contract("_, _ -> new")
    public static @NotNull SoundEventPreRegisterConfig<RegistryEntry.Reference<SoundEvent>> createMusicSoundEvent(String namespace, String path) {
        return createSoundEventRegistryReference(
            namespace, path,
            SoundEventRepository.SOUND_EVENT_REPOSITORY_MUSIC
        );
    }

    /**
     * Create a {@code RegistryEntry.Reference<SoundEvent>} belonging to {@code ui} by the given {@code namespace} and {@code path} string.
     * @param namespace The namespace of the {@code RegistryEntry.Reference<SoundEvent>}.
     * @param path The path of the {@code RegistryEntry.Reference<SoundEvent>}.
     */
    @Contract("_, _ -> new")
    public static @NotNull SoundEventPreRegisterConfig<RegistryEntry.Reference<SoundEvent>> createUISoundEvent(String namespace, String path) {
        return createSoundEventRegistryReference(
            namespace, path,
            SoundEventRepository.SOUND_EVENT_REPOSITORY_UI
        );
    }

    /**
     * Create a {@code RegistryEntry.Reference<SoundEvent>} belonging to {@code weather} by the given {@code namespace} and {@code path} string.
     * @param namespace The namespace of the {@code RegistryEntry.Reference<SoundEvent>}.
     * @param path The path of the {@code RegistryEntry.Reference<SoundEvent>}.
     */
    @Contract("_, _ -> new")
    public static @NotNull SoundEventPreRegisterConfig<RegistryEntry.Reference<SoundEvent>> createWeatherSoundEvent(String namespace, String path) {
        return createSoundEventRegistryReference(
            namespace, path,
            SoundEventRepository.SOUND_EVENT_REPOSITORY_WEATHER
        );
    }

    /**
     * Create a {@code RegistryEntry.Reference<SoundEvent>} belonging to {@code ambient} by the given {@code namespace} and {@code path} string.
     * @param namespace The namespace of the {@code RegistryEntry.Reference<SoundEvent>}.
     * @param path The path of the {@code RegistryEntry.Reference<SoundEvent>}.
     */
    @Contract("_, _ -> new")
    public static @NotNull SoundEventPreRegisterConfig<RegistryEntry.Reference<SoundEvent>> createAmbientSoundEvent(String namespace, String path) {
        return createSoundEventRegistryReference(
            namespace, path,
            SoundEventRepository.SOUND_EVENT_REPOSITORY_AMBIENT
        );
    }

    /**
     * Create a {@code RegistryEntry.Reference<SoundEvent>} belonging to a custom repository by the given {@code id} and {@code repository}.
     * @param id The identifier of the {@code RegistryEntry.Reference<SoundEvent>}.
     * @param repository The custom repository.
     * @throws NullPointerException if the {@code repository} is {@code null}.
     * @see SoundEventRepositoryElement
     * @see SoundEventRepository
     */
    @Contract("_, _ -> new")
    @NotNull
    public static SoundEventPreRegisterConfig<RegistryEntry.Reference<SoundEvent>> createSoundEventRegistryReference(Identifier id, @NotNull SoundEventRepositoryElement repository) {
        Objects.requireNonNull(repository);
        return new PCSoundEventRegRefRegister(repository.apply(id));
    }

    /**
     * Create a {@code RegistryEntry.Reference<SoundEvent>} belonging to a custom repository by the given {@code fullPath} and {@code repository}.
     * @param fullPath The full path of the {@code RegistryEntry.Reference<SoundEvent>}.
     * @param repository The custom repository.
     * @throws NullPointerException if the {@code repository} is {@code null}.
     * @see SoundEventRepositoryElement
     * @see SoundEventRepository
     */
    @Contract("_, _ -> new")
    @NotNull
    public static SoundEventPreRegisterConfig<RegistryEntry.Reference<SoundEvent>> createSoundEventRegistryReference(String fullPath, @NotNull SoundEventRepositoryElement repository) {
        Objects.requireNonNull(repository);
        Identifier id = Identifier.tryParse(fullPath);
        id = repository.apply(id);
        return new PCSoundEventRegRefRegister(id);
    }

    /**
     * Create a {@code RegistryEntry.Reference<SoundEvent>} belonging to a custom repository by the given {@code namespace:path} and {@code repository}.
     * @param namespace The namespace of the {@code RegistryEntry.Reference<SoundEvent>}.
     * @param path The path of the {@code RegistryEntry.Reference<SoundEvent>}.
     * @param repository The custom repository.
     * @throws NullPointerException If the {@code repository} is null.
     * @see SoundEventRepositoryElement
     * @see SoundEventRepository
     */
    @Contract("_, _, _ -> new")
    @NotNull
    public static SoundEventPreRegisterConfig<RegistryEntry.Reference<SoundEvent>> createSoundEventRegistryReference(String namespace, String path, @NotNull SoundEventRepositoryElement repository) {
        Objects.requireNonNull(repository);
        Identifier id = Identifier.of(namespace, path);
        id = repository.apply(id);
        return new PCSoundEventRegRefRegister(id);
    }

    /**
     * Create a {@code RegistryEntry.Reference<SoundEvent>} belonging to a given {@code repository} by the given {@code Identifier}.
     * @param id The {@code Identifier} of the {@code RegistryEntry.Reference<SoundEvent>}.
     * @param repository The {@link SoundEventRepository} of the {@code RegistryEntry.Reference<SoundEvent>}.
     * @see SoundEventRepositoryElement
     * @see SoundEventRepository
     */
    @Contract("_, _ -> new")
    @NotNull
    public static SoundEventPreRegisterConfig<RegistryEntry.Reference<SoundEvent>> createSoundEventRegistryReference(Identifier id, SoundEventRepository repository) {
        return new PCSoundEventRegRefRegister(repository.apply(id));
    }

    /**
     * Create a {@code RegistryEntry.Reference<SoundEvent>} belonging to a given {@code repository} by the given {@code fullPath}.
     * @param fullPath The full path of the {@code RegistryEntry.Reference<SoundEvent>}.
     * @param repository The {@link SoundEventRepository} of the {@code RegistryEntry.Reference<SoundEvent>}.
     * @return
     */
    @Contract("_, _ -> new")
    @NotNull
    public static SoundEventPreRegisterConfig<RegistryEntry.Reference<SoundEvent>> createSoundEventRegistryReference(String fullPath, SoundEventRepository repository) {
        Identifier id = Identifier.tryParse(fullPath);
        id = repository.apply(id);
        return new PCSoundEventRegRefRegister(id);
    }

    /**
     * Create a {@code RegistryEntry.Reference<SoundEvent>} belonging to a given {@code repository} by the given {@code namespace:path}.
     * @param namespace The namespace of the {@code RegistryEntry.Reference<SoundEvent>}
     * @param path The path of the {@code RegistryEntry.Reference<SoundEvent>}
     * @param repository The {@link SoundEventRepository} of the {@code RegistryEntry.Reference<SoundEvent>}
     */
    @Contract("_, _, _ -> new")
    @NotNull
    public static SoundEventPreRegisterConfig<RegistryEntry.Reference<SoundEvent>> createSoundEventRegistryReference(String namespace, String path, SoundEventRepository repository) {
        Identifier id = Identifier.of(namespace, path);
        id = repository.apply(id);
        return new PCSoundEventRegRefRegister(id);
    }

    /**
     * Registers the {@code RegistryEntry.Reference<SoundEvent>} in the Minecraft registry.
     */
    @Override
    public SoundEventPostRegisterConfig<RegistryEntry.Reference<SoundEvent>> registerAndBuild() {
        this.targetRegistered = Registry.registerReference(
            Registries.SOUND_EVENT,
            this.id,
            SoundEvent.of(this.id)
        );
        return this;
    }
}
