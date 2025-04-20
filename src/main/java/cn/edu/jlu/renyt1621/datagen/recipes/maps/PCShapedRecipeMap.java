package cn.edu.jlu.renyt1621.datagen.recipes.maps;

import cn.edu.jlu.renyt1621.datagen.recipes.craft.PCShapedRecipe;
import net.minecraft.item.ItemConvertible;

import java.util.HashMap;
import java.util.Map;

/**
 * <h2>Description</h2>
 * A map for recording the shaped recipes for the items.
 *
 * <h2>Usages</h2>
 * <p>
 * Use {@link #instance()} to get the instance of this class.<br>
 * Use {@link #getShapedRecipes()} to get the map of shaped recipes.
 * </p>
 *
 * <p>The {@code PCShapedRecipeMap} provides methods to operate the shaped recipes:</p>
 * <ul style="margin-left: 20px;">
 *     <li>{@link #put(PCShapedRecipe, ItemConvertible)}<br>
 *         Put a shaped recipe and its result into {@link #SHAPED_RECIPES}.
 *     </li>
 *     <li>{@link #putAll(Map)}<br>
 *         Put all shaped recipes and their results into {@link #SHAPED_RECIPES}.
 *     </li>
 *     <li>{@link #get(PCShapedRecipe)}<br>
 *         Get the result of the given shaped recipe.
 *     </li>
 *     <li>{@link #remove(PCShapedRecipe)}<br>
 *         Remove the shaped recipe and its result from {@link #SHAPED_RECIPES}.
 *     </li>
 *     <li>{@link #clear()}<br>
 *         Clear all entries in {@link #SHAPED_RECIPES}.
 *     </li>
 *     <li>{@link #isEmpty()}<br>
 *         Check if {@link #SHAPED_RECIPES} is empty.
 *     </li>
 *     <li>{@link #size()}<br>
 *         Get the size of {@link #SHAPED_RECIPES}.
 *     </li>
 *     <li>{@link #containsKey(PCShapedRecipe)}<br>
 *         Check if {@link #SHAPED_RECIPES} contains the given shaped recipe.
 *     </li>
 * </ul>
 *
 * @author REN YuanTong
 * @since 1.0.0
 * @date 2025-04-18
 */

public final class PCShapedRecipeMap {
    private static volatile PCShapedRecipeMap INSTANCE;

    private static final Map<PCShapedRecipe, ItemConvertible> SHAPED_RECIPES = new HashMap<>();

    private PCShapedRecipeMap() {}


    /**
     * Get the singleton instance of {@code PCShapedRecipeMap}.
     *
     * @return the instance of this class
     */
    public static PCShapedRecipeMap instance() {
        if (INSTANCE == null) {
            synchronized (PCShapedRecipeMap.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PCShapedRecipeMap();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Get the internal map of shaped recipes.
     *
     * @return the map of shaped recipes and their results
     */
    public Map<PCShapedRecipe, ItemConvertible> getShapedRecipes() {
        return SHAPED_RECIPES;
    }

    /**
     * Get the number of shaped recipes in the map.
     *
     * @return the size of the map
     */
    public int size() {
        return SHAPED_RECIPES.size();
    }

    /**
     * Check whether the map is empty.
     *
     * @return {@code true} if the map is empty, otherwise {@code false}
     */
    public boolean isEmpty() {
        return SHAPED_RECIPES.isEmpty();
    }

    /**
     * Check if a specific shaped recipe exists in the map.
     *
     * @param recipe the shaped recipe to check
     * @return {@code true} if the map contains the recipe, otherwise {@code false}
     */
    public boolean containsKey(PCShapedRecipe recipe) {
        return SHAPED_RECIPES.containsKey(recipe);
    }

    /**
     * Put a shaped recipe and its result into the map.
     *
     * @param recipe the shaped recipe
     * @param item   the result item of the recipe
     * @return the previous item associated with the recipe, or {@code null} if none
     */
    public ItemConvertible put(PCShapedRecipe recipe, ItemConvertible item) {
        return SHAPED_RECIPES.put(recipe, item);
    }

    /**
     * Put all shaped recipes and their results into the map.
     *
     * @param map a map containing shaped recipes and their result items
     */
    public void putAll(Map<PCShapedRecipe, ItemConvertible> map) {
        SHAPED_RECIPES.putAll(map);
    }

    /**
     * Get the result item for a given shaped recipe.
     *
     * @param recipe the shaped recipe to query
     * @return the result item, or {@code null} if not found
     */
    public ItemConvertible get(PCShapedRecipe recipe) {
        return SHAPED_RECIPES.get(recipe);
    }

    /**
     * Remove a shaped recipe and its result from the map.
     *
     * @param recipe the recipe to remove
     * @return the item that was associated with the recipe or {@code null} if none
     */
    public ItemConvertible remove(PCShapedRecipe recipe) {
        return SHAPED_RECIPES.remove(recipe);
    }

    /**
     * Clear all entries from the map.
     */
    public void clear() {
        SHAPED_RECIPES.clear();
    }

}
