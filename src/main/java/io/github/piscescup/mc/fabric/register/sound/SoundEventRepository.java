package io.github.piscescup.mc.fabric.register.sound;

import net.minecraft.util.Identifier;

import java.util.function.UnaryOperator;

/**
 * Represents a repository of standard sound event categories, acting as a factory for
 * creating conventional {@link Identifier}s for sounds.
 * <p>
 * Each enum constant functions as a transformer that modifies a base {@link Identifier}
 * to conform to a standard naming convention (e.g., prepending "item.", "block.", etc.).
 * This approach promotes consistency and reduces errors when referencing sound events
 * throughout an application.
 *
 * <h2>Core Functionality</h2>
 * <p>
 * The primary function is to transform a base identifier string into a categorized one.
 * The transformation process is clear and direct:
 * <pre>{@code
 * // Example for an item sound:
 * "mymod:armor_equip_generic" ---After ITEM Repository---> "mymod:item.armor_equip_generic"
 *
 * // Example for a block sound:
 * "minecraft:glass_break" ---After BLOCK Repository---> "minecraft:block.glass_break"
 *
 * // Example for a "no-op" (empty) transformation:
 * "mymod:custom/special" ---After EMPTY Repository (no change)---> "mymod:custom/special"
 * }</pre>
 *
 * <h2>Usage in Code</h2>
 * <p>
 * This is achieved by invoking the {@link #apply(Identifier)} method on an enum constant
 * with a base {@code Identifier}:
 * <pre>{@code
 * // 1. Define the base identifier for a sound
 * Identifier baseId = Identifier.of("mymod", "magic_wand_cast");
 *
 * // 2. Apply the ITEM category transformation
 * Identifier finalItemId = SoundRepository.SOUND_EVENT_ITEM.apply(baseId);
 * // finalItemId now represents "mymod:item.magic_wand_cast"
 *
 * // 3. Apply the ENTITY category transformation
 * Identifier finalEntityId = SoundRepository.SOUND_EVENT_ENTITY.apply(baseId);
 * // finalEntityId now represents "mymod:entity.magic_wand_cast"
 * }</pre>
 *
 * @author REN YuanTong
 * @Date 2025-07-19
 * @since 1.1.2
 * @see SoundEventRepositoryElement
 * @see Identifier
 */
public enum SoundEventRepository implements SoundEventRepositoryElement {
    /**
     * Category for sounds related to items, such as equipping, using, or breaking them.
     * <p>
     * <b>Transformation:</b> Prepends {@code "item."} to the identifier's path.
     * <p>
     * <b>Examples:</b>
     * <pre>{@code
     * Input:  mymod:armor_equip_generic -> Output: mymod:item.armor_equip_generic
     * Input:  minecraft:book_page_turn  -> Output: minecraft:item.book_page_turn
     * }</pre>
     */
    SOUND_EVENT_REPOSITORY_ITEM(
        id -> Identifier.of(id.getNamespace(), "item." + id.getPath())
    ),

    /**
     * Category for sounds related to blocks, such as breaking, placing, or stepping on them.
     * <p>
     * <b>Transformation:</b> Prepends {@code "block."} to the identifier's path.
     * <p>
     * <b>Examples:</b>
     * <pre>{@code
     * Input:  mymod:magic_stone_place -> Output: mymod:block.magic_stone_place
     * Input:  minecraft:glass_break   -> Output: minecraft:block.glass_break
     * }</pre>
     */
    SOUND_EVENT_REPOSITORY_BLOCK(
        id -> Identifier.of(id.getNamespace(), "block." + id.getPath())
    ),

    /**
     * Category for sounds produced by entities, such as mobs, players, or other living creatures.
     * <p>
     * <b>Transformation:</b> Prepends {@code "entity."} to the identifier's path.
     * <p>
     * <b>Examples:</b>
     * <pre>{@code
     * Input:  mymod:dragon_roar   -> Output: mymod:entity.dragon_roar
     * Input:  minecraft:zombie_hurt -> Output: minecraft:entity.zombie_hurt
     * }</pre>
     */
    SOUND_EVENT_REPOSITORY_ENTITY(
        id -> Identifier.of(id.getNamespace(), "entity." + id.getPath())
    ),

    /**
     * Category for background music tracks, such as in-game ambient music or menu themes.
     * <p>
     * <b>Transformation:</b> Prepends {@code "music."} to the identifier's path.
     * <p>
     * <b>Examples:</b>
     * <pre>{@code
     * Input:  mymod:boss_theme_1 -> Output: mymod:music.boss_theme_1
     * Input:  minecraft:creative -> Output: minecraft:music.creative
     * }</pre>
     */
    SOUND_EVENT_REPOSITORY_MUSIC(
        id -> Identifier.of(id.getNamespace(), "music." + id.getPath())
    ),

    /**
     * Category for user interface sounds, such as button clicks or inventory interactions.
     * <p>
     * <b>Transformation:</b> Prepends {@code "ui."} to the identifier's path.
     * <p>
     * <b>Examples:</b>
     * <pre>{@code
     * Input:  mymod:quest_complete   -> Output: mymod:ui.quest_complete
     * Input:  minecraft:button.click -> Output: minecraft:ui.button.click
     * }</pre>
     */
    SOUND_EVENT_REPOSITORY_UI(
        id -> Identifier.of(id.getNamespace(), "ui." + id.getPath())
    ),

    /**
     * Category for sounds related to weather events, like rain or thunder.
     * <p>
     * <b>Transformation:</b> Prepends {@code "weather."} to the identifier's path.
     * <p>
     * <b>Examples:</b>
     * <pre>{@code
     * Input:  minecraft:rain  -> Output: minecraft:weather.rain
     * Input:  mymod:acid_rain -> Output: mymod:weather.acid_rain
     * }</pre>
     */
    SOUND_EVENT_REPOSITORY_WEATHER(
        id -> Identifier.of(id.getNamespace(), "weather." + id.getPath())
    ),

    /**
     * Category for general ambient or environmental sounds not tied to a specific block or entity.
     * <p>
     * <b>Transformation:</b> Prepends {@code "ambient."} to the identifier's path.
     * <p>
     * <b>Examples:</b>
     * <pre>{@code
     * Input:  minecraft:cave    -> Output: minecraft:ambient.cave
     * Input:  mymod:deep_forest -> Output: mymod:ambient.deep_forest
     * }</pre>
     */
    SOUND_EVENT_REPOSITORY_AMBIENT(
        id -> Identifier.of(id.getNamespace(), "ambient." + id.getPath())
    ),

    /**
     * A special "no-op" or "identity" category that performs no transformation.
     * It returns the original identifier unchanged. This is useful for sounds that
     * already have a fully qualified path or do not fit standard categories.
     * <p>
     * <b>Transformation:</b> None. The input is returned directly.
     * <p>
     * <b>Examples:</b>
     * <pre>{@code
     * Input:  mymod:custom/special_effect -> Output: mymod:custom/special_effect
     * Input:  minecraft:random.orb        -> Output: minecraft:random.orb
     * }</pre>
     */
    SOUND_EVENT_REPOSITORY_EMPTY(
        id -> id
    ),

    ;

    /**
     * The transformation function that modifies an identifier's path.
     */
    private final UnaryOperator<Identifier> operator;

    /**
     * Constructs a SoundRepository constant with a specific transformation operator.
     *
     * @param operator The function to apply to an {@link Identifier}.
     */
    SoundEventRepository(UnaryOperator<Identifier> operator) {
        this.operator = operator;
    }

    /**
     * Applies the category-specific transformation to the given identifier.
     *
     * @param id The base {@link Identifier} to transform.
     * @return A new {@link Identifier} with the category prefix applied to its path.
     */
    @Override
    public Identifier apply(Identifier id) {
        return operator.apply(id);
    }
}