package io.github.piscescup.mc.fabric.datagen.loot.lists;

import io.github.piscescup.mc.fabric.register.loot.Droppable;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * <h2>Description</h2>
 *
 * <h2>Usages</h2>
 *
 * @author REN YuanTong
 * @Date 2025-07-11
 * @since 1.1.2
 */
public interface DropperList<T extends Droppable<?>> {
    List<T> getDropperList();

    boolean addDropper(@NotNull T dropper);

    boolean addDroppers(@NotNull List<T> droppers);

    boolean removeDropper(@NotNull T dropper);

    boolean removeDroppers(@NotNull List<T> droppers);

    boolean isEmpty();

    boolean containsDropper(@NotNull T dropper);

    boolean containsAllDroppers(@NotNull List<T> droppers);

    int size();

    void clear();

    T get(int index);

}
