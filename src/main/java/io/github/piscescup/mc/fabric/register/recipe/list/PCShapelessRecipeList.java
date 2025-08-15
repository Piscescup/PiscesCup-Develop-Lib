package io.github.piscescup.mc.fabric.register.recipe.list;

import io.github.piscescup.mc.fabric.register.recipe.PCShapelessRecipe;
import io.github.piscescup.mc.fabric.utils.CheckUtils;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * <h2>Description</h2>
 *
 * <h2>Usages</h2>
 *
 * @author REN YuanTong
 * @Date 2025-08-09
 * @since 1.1.2
 */
public final class PCShapelessRecipeList implements List<PCShapelessRecipe> {
    private final List<PCShapelessRecipe> shapelessRecipes;

    private PCShapelessRecipeList() {
        shapelessRecipes = new ArrayList<>();
    }
    private static volatile PCShapelessRecipeList INSTANCE;

    public static PCShapelessRecipeList instance() {
        if (INSTANCE == null) {
            synchronized (PCShapelessRecipeList.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PCShapelessRecipeList();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public int size() {
        return shapelessRecipes.size();
    }

    @Override
    public boolean isEmpty() {
        return shapelessRecipes.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return shapelessRecipes.contains(o);
    }

    @Override
    public @NotNull Iterator<PCShapelessRecipe> iterator() {
        return shapelessRecipes.iterator();
    }

    @Override
    public @NotNull Object[] toArray() {
        return shapelessRecipes.toArray();
    }

    @Override
    public @NotNull <T> T[] toArray(@NotNull T[] a) {
        return shapelessRecipes.toArray(a);
    }

    @Override
    public boolean add(PCShapelessRecipe pcShapelessRecipe) {
        CheckUtils.NullChecker.nonNull(pcShapelessRecipe);
        return shapelessRecipes.add(pcShapelessRecipe);
    }

    @Override
    public boolean remove(Object o) {
        CheckUtils.NullChecker.nonNull(o);
        return shapelessRecipes.remove(o);
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        CheckUtils.NullChecker.nonNullElements(c);
        return new HashSet<>(shapelessRecipes).containsAll(c);
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends PCShapelessRecipe> c) {
        CheckUtils.NullChecker.nonNullElements(c);
        return shapelessRecipes.addAll(c);
    }

    @Override
    public boolean addAll(int index, @NotNull Collection<? extends PCShapelessRecipe> c) {
        CheckUtils.NullChecker.nonNullElements(c);
        return shapelessRecipes.addAll(index, c);
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        CheckUtils.NullChecker.nonNullElements(c);
        return shapelessRecipes.removeAll(c);
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        CheckUtils.NullChecker.nonNullElements(c);
        return shapelessRecipes.retainAll(c);
    }

    @Override
    public void clear() {
        shapelessRecipes.clear();
    }

    @Override
    public PCShapelessRecipe get(int index) {
        return shapelessRecipes.get(index);
    }

    @Override
    public PCShapelessRecipe set(int index, PCShapelessRecipe element) {
        CheckUtils.NullChecker.nonNull(element);
        return shapelessRecipes.set(index, element);
    }

    @Override
    public void add(int index, PCShapelessRecipe element) {
        CheckUtils.NullChecker.nonNull(element);
        shapelessRecipes.add(index, element);
    }

    @Override
    public PCShapelessRecipe remove(int index) {
        return shapelessRecipes.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        CheckUtils.NullChecker.nonNull(o);
        return shapelessRecipes.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        CheckUtils.NullChecker.nonNull(o);
        return shapelessRecipes.lastIndexOf(o);
    }

    @Override
    public @NotNull ListIterator<PCShapelessRecipe> listIterator() {
        return shapelessRecipes.listIterator();
    }

    @Override
    public @NotNull ListIterator<PCShapelessRecipe> listIterator(int index) {
        return shapelessRecipes.listIterator(index);
    }

    @Override
    public @NotNull List<PCShapelessRecipe> subList(int fromIndexInclusive, int toIndexExclusive) {
        return shapelessRecipes.subList(fromIndexInclusive, toIndexExclusive);
    }
}
