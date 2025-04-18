package cn.edu.jlu.renyt1621.datagen.recipes.maps;

import cn.edu.jlu.renyt1621.reg.recipes.PCShapedRecipe;
import net.minecraft.item.ItemConvertible;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h1>Description</h1>
 *
 * <h1>Usages</h1>
 *
 * @author REN YuanTong
 * @Date 2025-04-18
 * @since 1.0.0
 */
public class PCShapedRecipeMap {
    private static volatile PCShapedRecipeMap INSTANCE;

    private static final Map<PCShapedRecipe, ItemConvertible> shapedRecipes= new HashMap<>();

    private PCShapedRecipeMap() {}

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

    public Map<PCShapedRecipe, ItemConvertible> getShapedRecipes() {
        return shapedRecipes;
    }

    public int size() {
        return shapedRecipes.size();
    }

    public boolean isEmpty() {
        return shapedRecipes.isEmpty();
    }

    public boolean containsKey(PCShapedRecipe recipe) {
        return shapedRecipes.containsKey(recipe);
    }

    public ItemConvertible put(PCShapedRecipe recipe, ItemConvertible item) {
        return shapedRecipes.put(recipe, item);
    }

    public void putAll(Map<PCShapedRecipe, ItemConvertible> map) {
        shapedRecipes.putAll(map);
    }


}
