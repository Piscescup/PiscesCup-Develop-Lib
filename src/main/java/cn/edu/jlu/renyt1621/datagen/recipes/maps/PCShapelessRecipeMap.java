package cn.edu.jlu.renyt1621.datagen.recipes.maps;

import cn.edu.jlu.renyt1621.reg.recipes.PCShapelessRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;

import java.util.HashMap;
import java.util.Map;

/**
 * <h2>Description</h2>
 *
 * <h2>Usages</h2>
 *
 * @author REN YuanTong
 * @Date 2025-04-19
 * @since 1.0.0
 */
public final class PCShapelessRecipeMap {
    private static volatile PCShapelessRecipeMap INSTANCE;

    private static final Map<PCShapelessRecipe, ItemConvertible> SHAPELESS_RECIPE_ITEM_MAP = new HashMap<>();

    private PCShapelessRecipeMap() {}

    public static PCShapelessRecipeMap instance() {
        if (INSTANCE == null) {
            synchronized (PCShapelessRecipeMap.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PCShapelessRecipeMap();
                }
            }
        }
        return INSTANCE;
    }

    public Map<PCShapelessRecipe, ItemConvertible> getShapelessRecipeItemMap() {
        return SHAPELESS_RECIPE_ITEM_MAP;
    }

    public void putAll(Map<PCShapelessRecipe, Item> map) {
        SHAPELESS_RECIPE_ITEM_MAP.putAll(map);
    }

    public boolean containsKey(PCShapelessRecipe recipe) {
        return SHAPELESS_RECIPE_ITEM_MAP.containsKey(recipe);
    }

    public ItemConvertible put(PCShapelessRecipe recipe, ItemConvertible item) {
        return SHAPELESS_RECIPE_ITEM_MAP.put(recipe, item);
    }

    public ItemConvertible get(PCShapelessRecipe recipe) {
        return SHAPELESS_RECIPE_ITEM_MAP.get(recipe);
    }

    public ItemConvertible remove(PCShapelessRecipe recipe) {
        return SHAPELESS_RECIPE_ITEM_MAP.remove(recipe);
    }

    public void clear() {
        SHAPELESS_RECIPE_ITEM_MAP.clear();
    }

    public int size() {
        return SHAPELESS_RECIPE_ITEM_MAP.size();
    }

    public boolean isEmpty() {
        return SHAPELESS_RECIPE_ITEM_MAP.isEmpty();
    }


}
