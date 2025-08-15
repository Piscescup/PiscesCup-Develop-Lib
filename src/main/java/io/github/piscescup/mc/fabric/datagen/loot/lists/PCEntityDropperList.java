package io.github.piscescup.mc.fabric.datagen.loot.lists;

import io.github.piscescup.mc.fabric.register.loot.PCEntityDropRegister;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/**
 * <h2>Description</h2>
 *
 * <h2>Usages</h2>
 *
 * @author REN YuanTong
 * @Date 2025-07-12
 * @since
 */
public class PCEntityDropperList
    implements DropperList<PCEntityDropRegister>
{
    private static volatile PCEntityDropperList INSTANCE;
    private PCEntityDropperList() {}

    public static PCEntityDropperList instance() {
        if (INSTANCE == null) {
            synchronized (PCEntityDropperList.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PCEntityDropperList();
                }
            }
        }
        return INSTANCE;
    }

    private final List<PCEntityDropRegister> droppers = new ArrayList<>();

    @Override
    public List<PCEntityDropRegister> getDropperList() {
        return droppers;
    }

    @Override
    public boolean addDropper(@NotNull PCEntityDropRegister dropper) {
        Objects.requireNonNull(dropper);
        return this.droppers.add(dropper);
    }

    @Override
    public boolean addDroppers(@NotNull List<PCEntityDropRegister> droppers) {
        Objects.requireNonNull(droppers);
        return this.droppers.addAll(droppers);
    }

    @Override
    public boolean removeDropper(@NotNull PCEntityDropRegister dropper) {
        Objects.requireNonNull(dropper);
        return this.droppers.remove(dropper);
    }

    @Override
    public boolean removeDroppers(@NotNull List<PCEntityDropRegister> droppers) {
        Objects.requireNonNull(droppers);
        return this.droppers.removeAll(droppers);
    }

    @Override
    public boolean isEmpty() {
        return this.droppers.isEmpty();
    }

    @Override
    public boolean containsDropper(@NotNull PCEntityDropRegister dropper) {
        Objects.requireNonNull(dropper);
        return this.droppers.contains(dropper);
    }

    @Override
    public boolean containsAllDroppers(@NotNull List<PCEntityDropRegister> droppers) {
        Objects.requireNonNull(droppers);
        return new HashSet<>(this.droppers).containsAll(droppers);
    }

    @Override
    public int size() {
        return this.droppers.size();
    }

    @Override
    public void clear() {
        this.droppers.clear();
    }

    @Override
    public PCEntityDropRegister get(int index) {
        return this.droppers.get(index);
    }
}
