package io.github.piscescup.mc.fabric.datagen.loot.lists;

import io.github.piscescup.mc.fabric.register.loot.PCBlockDropRegister;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * <h1>Description</h1>
 *
 * <h1>Usages</h1>
 *
 * @author REN YuanTong
 * @Date 2025-04-17
 * @since 1.0.0
 */
public class PCBlockDropperList
    implements DropperList<PCBlockDropRegister>
{
    private static volatile PCBlockDropperList INSTANCE;
    private PCBlockDropperList() {}

    public static PCBlockDropperList instance() {
        if (INSTANCE == null) {
            synchronized (PCBlockDropperList.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PCBlockDropperList();
                }
            }
        }
        return INSTANCE;
    }

    private final List<PCBlockDropRegister> droppers = new CopyOnWriteArrayList<>();

    @Override
    public List<PCBlockDropRegister> getDropperList() {
        return droppers;
    }

    @Override
    public boolean addDropper(@NotNull PCBlockDropRegister dropper) {
        Objects.requireNonNull(dropper);
        return this.droppers.add(dropper);
    }

    @Override
    public boolean addDroppers(@NotNull List<PCBlockDropRegister> droppers) {
        Objects.requireNonNull(droppers);
        return this.droppers.addAll(droppers);
    }

    @Override
    public boolean removeDropper(@NotNull PCBlockDropRegister dropper) {
        Objects.requireNonNull(dropper);
        return this.droppers.remove(dropper);
    }

    @Override
    public boolean removeDroppers(@NotNull List<PCBlockDropRegister> droppers) {
        Objects.requireNonNull(droppers);
        return this.droppers.removeAll(droppers);
    }

    @Override
    public boolean isEmpty() {
        return this.droppers.isEmpty();
    }

    @Override
    public boolean containsDropper(@NotNull PCBlockDropRegister dropper) {
        Objects.requireNonNull(dropper);
        return this.droppers.contains(dropper);
    }

    @Override
    public boolean containsAllDroppers(@NotNull List<PCBlockDropRegister> droppers) {
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
    public PCBlockDropRegister get(int index) {
        return this.droppers.get(index);
    }
}
