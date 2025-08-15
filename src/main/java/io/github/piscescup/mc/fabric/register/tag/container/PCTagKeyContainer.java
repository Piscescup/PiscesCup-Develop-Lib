package io.github.piscescup.mc.fabric.register.tag.container;

import net.minecraft.registry.tag.TagKey;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <h2>Description</h2>
 *
 * <h2>Usages</h2>
 *
 * @author REN YuanTong
 * @Date 2025-07-25
 * @since 1.1.2
 */
public class PCTagKeyContainer<T>
    implements TagKeyContainer<T>
{
    private final TagKey<T> targetTag;

    private final List<T> containedThings = new ArrayList<>();
    private final List<TagKey<T>> containedTags = new ArrayList<>();

    public PCTagKeyContainer(TagKey<T> targetTag) {
        this.targetTag = targetTag;
    }

    public static <K> PCTagKeyContainer<K> createFor(TagKey<K> tag) {
        return new PCTagKeyContainer<>(tag);
    }


    @Override
    public TagKey<T> getTargetTag() {
        return targetTag;
    }

    @Override
    public List<T> getContainedThings() {
        return containedThings;
    }

    @Override
    public List<TagKey<T>> getContainedTags() {
        return containedTags;
    }

    @Override
    public boolean addContainedThing(@NotNull T thing) {
        Objects.requireNonNull(thing);
        return this.containedThings.add(thing);
    }

    @Override
    public boolean addContainedThings(@NotNull List<T> things) {
        Objects.requireNonNull(things);
        return this.containedThings.addAll(things);
    }

    @Override
    public boolean removeContainedThing(@NotNull T thing) {
        Objects.requireNonNull(thing);
        return this.containedThings.remove(thing);
    }

    @Override
    public boolean removeContainedThings(@NotNull List<T> things) {
        Objects.requireNonNull(things);
        return this.containedThings.removeAll(things);
    }

    @Override
    public boolean addContainedTag(@NotNull TagKey<T> tag) {
        Objects.requireNonNull(tag);
        return this.containedTags.add(tag);
    }

    @Override
    public boolean addContainedTags(@NotNull List<TagKey<T>> tags) {
        Objects.requireNonNull(tags);
        return this.containedTags.addAll(tags);
    }

    @Override
    public boolean removeContainedTag(@NotNull TagKey<T> tag) {
        Objects.requireNonNull(tag);
        return this.containedTags.remove(tag);
    }

    @Override
    public boolean removeContainedTags(@NotNull List<TagKey<T>> tags) {
        Objects.requireNonNull(tags);
        return this.containedTags.removeAll(tags);
    }

    @Override
    public boolean isEmpty() {
        return this.containedThings.isEmpty() &&
            this.containedTags.isEmpty();
    }

    @Override
    public boolean containsThing(@NotNull T thing) {
        Objects.requireNonNull(thing);
        return containedThings.contains(thing) ;
    }

    @Override
    public boolean containsTag(@NotNull TagKey<T> tag) {
        Objects.requireNonNull(tag);
        return containedTags.contains(tag);
    }
}
