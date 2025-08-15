package io.github.piscescup.mc.fabric.register.item;

import io.github.piscescup.mc.fabric.register.recipe.PCShapedRecipe;
import io.github.piscescup.mc.fabric.register.recipe.PCShapelessRecipe;
import io.github.piscescup.mc.fabric.register.PCRegister;
import io.github.piscescup.mc.fabric.register.PostRegisterConfig;
import net.minecraft.client.data.Model;
import net.minecraft.client.data.Models;
import net.minecraft.item.Item;
import org.jetbrains.annotations.NotNull;

/**
 * A {@link PostRegisterConfig} for {@link Item}.
 * @author REN YuanTong
 * @Date 2025-07-31
 * @since 1.1.2
 */
public interface ItemPostRegisterConfig<R extends PCRegister<Item, R, ItemPostRegisterConfig<R>>>
    extends PostRegisterConfig<ItemPostRegisterConfig<R>, Item, R>
{
    /**
     * Set the model of the item.
     * @param model The model of the item
     * @see Models
     * @throws NullPointerException if {@code model} is null
     */
    ItemPostRegisterConfig<R> model(@NotNull Model model);

    /**
     * Set the shaped recipe of the item.
     * @param shapedRecipe The shaped recipe of the item
     * @see PCShapedRecipe
     * @throws NullPointerException if {@code shapedRecipe} is null
     */
    ItemPostRegisterConfig<R> shapedRecipe(@NotNull PCShapedRecipe shapedRecipe);

    /**
     * Set the shapeless recipe of the item.
     * @param shapelessRecipe The shapeless recipe of the item
     * @see PCShapelessRecipe
     * @throws NullPointerException if {@code shapelessRecipe} is null
     */
    ItemPostRegisterConfig<R> shapelessRecipe(@NotNull PCShapelessRecipe shapelessRecipe);
}
