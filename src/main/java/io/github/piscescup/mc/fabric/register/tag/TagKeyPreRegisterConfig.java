package io.github.piscescup.mc.fabric.register.tag;

import io.github.piscescup.mc.fabric.register.PreRegisterConfig;
import net.minecraft.registry.tag.TagKey;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * <h2>Description</h2>
 *
 * <h2>Usages</h2>
 *
 * @author REN YuanTong
 * @Date 2025-07-31
 * @since 1.1.2
 */
public interface TagKeyPreRegisterConfig<T>
    extends PreRegisterConfig<TagKeyPostRegisterConfig<T>>
{
    /**
     * Add a thing to a {@code TagKey}
     * @param item The thing will be added.
     * @throws NullPointerException if the {@code item} is null.
     */
    TagKeyPreRegisterConfig<T> add(@NotNull T item);

    /**
     * Add a list of things to a {@code TagKey}
     * @param items The things will be added.
     * @throws NullPointerException if the {@code items} is null.
     */
    TagKeyPreRegisterConfig<T> add(@NotNull List<T> items);

    /**
     * Add a {@code TagKey} to a {@code TagKey}
     * @param tag The {@code TagKey} will be added.
     * @throws NullPointerException if the {@code tag} is null.
     */
    TagKeyPreRegisterConfig<T> addTag(@NotNull TagKey<T> tag);

    /**
     * Add a list of {@code TagKey} to a {@code TagKey}
     * @param tags The {@code TagKey} will be added.
     * @throws NullPointerException if the {@code tags} is null.
     */
    TagKeyPreRegisterConfig<T> addTags(@NotNull List<TagKey<T>> tags);

}
