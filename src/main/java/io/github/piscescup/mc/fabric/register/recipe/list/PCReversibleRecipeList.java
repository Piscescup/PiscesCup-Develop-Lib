package io.github.piscescup.mc.fabric.register.recipe.list;

import io.github.piscescup.mc.fabric.register.recipe.PCReversibleCompactingRecipe;
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
 * @since
 */
public class PCReversibleRecipeList implements List<PCReversibleCompactingRecipe> {
    private final List<PCReversibleCompactingRecipe> compactingRecipes;

    private PCReversibleRecipeList() {
        compactingRecipes = new ArrayList<>();
    }

    private static volatile PCReversibleRecipeList INSTANCE = new PCReversibleRecipeList();

    public static PCReversibleRecipeList instance() {
        if (INSTANCE == null) {
            synchronized (PCReversibleRecipeList.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PCReversibleRecipeList();
                }
            }
        }
        return INSTANCE;
    }


    @Override
    public int size() {
        return compactingRecipes.size();
    }

    @Override
    public boolean isEmpty() {
        return compactingRecipes.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        CheckUtils.NullChecker.nonNull(o);
        return compactingRecipes.contains(o);
    }

    @Override
    public @NotNull Iterator<PCReversibleCompactingRecipe> iterator() {
        return compactingRecipes.iterator();
    }

    @Override
    public @NotNull Object @NotNull [] toArray() {
        return compactingRecipes.toArray();
    }

    @Override
    public @NotNull <T> T @NotNull [] toArray(@NotNull T @NotNull [] a) {
        return compactingRecipes.toArray(a);
    }

    @Override
    public boolean add(PCReversibleCompactingRecipe pcReversibleCompactingRecipe) {
        CheckUtils.NullChecker.nonNull(pcReversibleCompactingRecipe);
        return compactingRecipes.add(pcReversibleCompactingRecipe);
    }

    @Override
    public boolean remove(Object o) {
        CheckUtils.NullChecker.nonNull(o);
        return compactingRecipes.remove(o);
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        CheckUtils.NullChecker.nonNullElements(c);
        return new HashSet<>(compactingRecipes).containsAll(c);
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends PCReversibleCompactingRecipe> c) {
        CheckUtils.NullChecker.nonNullElements(c);
        return compactingRecipes.addAll(c);
    }

    @Override
    public boolean addAll(int index, @NotNull Collection<? extends PCReversibleCompactingRecipe> c) {
        CheckUtils.NullChecker.nonNullElements(c);
        return false;
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        CheckUtils.NullChecker.nonNullElements(c);
        return compactingRecipes.removeAll(c);
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        CheckUtils.NullChecker.nonNullElements(c);
        return compactingRecipes.retainAll(c);
    }

    @Override
    public void clear() {
        compactingRecipes.clear();
    }

    @Override
    public PCReversibleCompactingRecipe get(int index) {
        return compactingRecipes.get(index);
    }

    @Override
    public PCReversibleCompactingRecipe set(int index, PCReversibleCompactingRecipe element) {
        CheckUtils.NullChecker.nonNull(element);
        return compactingRecipes.set(index, element);
    }

    @Override
    public void add(int index, PCReversibleCompactingRecipe element) {
        CheckUtils.NullChecker.nonNull(element);
        compactingRecipes.add(index, element);
    }

    @Override
    public PCReversibleCompactingRecipe remove(int index) {
        return compactingRecipes.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        CheckUtils.NullChecker.nonNull(o);
        return compactingRecipes.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        CheckUtils.NullChecker.nonNull(o);
        return compactingRecipes.lastIndexOf(o);
    }

    @Override
    public @NotNull ListIterator<PCReversibleCompactingRecipe> listIterator() {
        return compactingRecipes.listIterator();
    }

    @Override
    public @NotNull ListIterator<PCReversibleCompactingRecipe> listIterator(int index) {
        return compactingRecipes.listIterator(index);
    }

    @Override
    public @NotNull List<PCReversibleCompactingRecipe> subList(int fromIndexInclusive, int toIndexExclusive) {
        return compactingRecipes.subList(fromIndexInclusive, toIndexExclusive);
    }
}
