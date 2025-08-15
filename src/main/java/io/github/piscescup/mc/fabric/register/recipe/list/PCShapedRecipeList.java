package io.github.piscescup.mc.fabric.register.recipe.list;

import io.github.piscescup.mc.fabric.register.recipe.PCShapedRecipe;
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
public class PCShapedRecipeList implements List<PCShapedRecipe> {
    private final List<PCShapedRecipe> shapedRecipes;

    private PCShapedRecipeList() {
        shapedRecipes = new ArrayList<>();
    }

    public static PCShapedRecipeList instance() {
        if (INSTANCE == null) {
            synchronized (PCShapedRecipeList.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PCShapedRecipeList();
                }
            }
        }
        return INSTANCE;
    }

    private static volatile PCShapedRecipeList INSTANCE;

    @Override
    public int size() {
        return shapedRecipes.size();
    }

    @Override
    public boolean isEmpty() {
        return shapedRecipes.isEmpty();
    }

    @Override
    public boolean contains(Object otherRecipe) {
        return shapedRecipes.contains(otherRecipe);
    }

    @Override
    public @NotNull Iterator<PCShapedRecipe> iterator() {
        return shapedRecipes.iterator();
    }

    @Override
    public @NotNull Object[] toArray() {
        return shapedRecipes.toArray();
    }

    @Override
    public @NotNull <T> T[] toArray(@NotNull T[] a) {
        return shapedRecipes.toArray(a);
    }

    @Override
    public boolean add(PCShapedRecipe shapedRecipe) {
        CheckUtils.NullChecker.nonNull(shapedRecipe);
        return shapedRecipes.add(shapedRecipe);
    }

    @Override
    public boolean remove(Object o) {
        CheckUtils.NullChecker.nonNull(o);
        return shapedRecipes.remove(o);
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        return new HashSet<>(shapedRecipes).containsAll(c);
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends PCShapedRecipe> c) {
        CheckUtils.NullChecker.nonNullElements(c);
        return shapedRecipes.addAll(c);
    }

    @Override
    public boolean addAll(int index, @NotNull Collection<? extends PCShapedRecipe> c) {
        CheckUtils.NullChecker.nonNullElements(c);
        return false;
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        CheckUtils.NullChecker.nonNullElements(c);
        return shapedRecipes.removeAll(c);
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        CheckUtils.NullChecker.nonNullElements(c);
        return shapedRecipes.retainAll(c);
    }

    @Override
    public void clear() {
        shapedRecipes.clear();
    }

    @Override
    public PCShapedRecipe get(int index) {
        return shapedRecipes.get(index);
    }

    @Override
    public PCShapedRecipe set(int index, PCShapedRecipe element) {
        CheckUtils.NullChecker.nonNull(element);
        return shapedRecipes.set(index, element);
    }

    @Override
    public void add(int index, PCShapedRecipe element) {
        CheckUtils.NullChecker.nonNull(element);
        shapedRecipes.add(index, element);
    }

    @Override
    public PCShapedRecipe remove(int index) {
        return shapedRecipes.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        CheckUtils.NullChecker.nonNull(o);
        return shapedRecipes.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        CheckUtils.NullChecker.nonNull(o);
        return shapedRecipes.lastIndexOf(o);
    }

    @Override
    public @NotNull ListIterator<PCShapedRecipe> listIterator() {
        return this.shapedRecipes.listIterator();
    }

    @Override
    public @NotNull ListIterator<PCShapedRecipe> listIterator(int index) {
        return shapedRecipes.listIterator(index);
    }

    @Override
    public @NotNull List<PCShapedRecipe> subList(int fromIndexInclusive, int toIndexExclusive) {
        return shapedRecipes.subList(fromIndexInclusive, toIndexExclusive);
    }
}
