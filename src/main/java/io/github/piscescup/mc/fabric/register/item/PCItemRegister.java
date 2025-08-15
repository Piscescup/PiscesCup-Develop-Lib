package io.github.piscescup.mc.fabric.register.item;

import io.github.piscescup.mc.fabric.datagen.factories.PCModelProviderFactory;
import io.github.piscescup.mc.fabric.datagen.models.maps.PCItemModelMap;
import io.github.piscescup.mc.fabric.register.recipe.PCShapedRecipe;
import io.github.piscescup.mc.fabric.register.recipe.PCShapelessRecipe;
import io.github.piscescup.mc.fabric.register.PCRegister;
import net.minecraft.client.data.Model;
import net.minecraft.data.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

/**
 * <h1>Description</h1>
 *
 * <p>
 *     A register util for registering {@code Item} in Minecraft.
 * </p>
 *
 * <h1>Usages</h1>
 * Below is a usage:
 * <blockquote><pre>
 *     public static final Item ITEM1 = PCItemRegister.create(MOD_ID, "item1")
 *         .registerAndBuild()
 *         .shapedRecipe(PCShapedRecipe.Builder.create()
 *             .pattern("***")
 *             .pattern("***")
 *             .pattern(" # ")
 *             .definition('*', Items.ACACIA_PLANKS)
 *             .definition('#', Items.ACACIA_BUTTON)
 *             .category(RecipeCategory.BUILDING_BLOCKS)
 *             .criterion("has_item", Items.ACACIA_PLANKS)
 *             .count(4)
 *             .buildEntry()
 *         )
 *         .translate(Language.EN_US, "Test Item1")
 *         .translate(Language.ZH_CN, "测试物品1")
 *         .model(Models.GENERATED)
 *         .get();
 * </pre></blockquote>
 *
 *
 * @author REN YuanTong
 * @Date 2025-04-06
 * @since 1.0.0
 */
public final class PCItemRegister
    extends PCRegister<Item, PCItemRegister, ItemPostRegisterConfig<PCItemRegister>>
    implements ItemPreRegisterConfig<PCItemRegister>, ItemPostRegisterConfig<PCItemRegister>
{
    private Item.Settings settings = new Item.Settings();
    private Function<Item.Settings, Item> factory = Item::new;
    private ShapedRecipeJsonBuilder builder;


    private PCItemRegister(Identifier id) {
        super(id);
        this.key = RegistryKey.of(RegistryKeys.ITEM, id);
    }

    /**
     * <p>
     * Registers the item and returns the registered instance.
     * </p>
     *
     * <p>
     * Automatically handles block item registration when applicable
     * by adding to {@link Item#BLOCK_ITEMS}.
     * </p>
     *
     * @return The register.
     */
    @Override
    public ItemPostRegisterConfig<PCItemRegister> registerAndBuild() {
        Item item = factory.apply(settings.registryKey(this.key));

        if ( item instanceof BlockItem blockItem )
            blockItem.appendBlocks(Item.BLOCK_ITEMS, item);

        this.targetRegistered = Registry.register(Registries.ITEM, key, item);
        return this;
    }


    /**
     * <p>
     *     Creates a new item register with the given path.
    * </p>
    * @param path the path of the block to be registered
     * @return a new block register
     */
    @Contract("_ -> new")
    public static @NotNull ItemPreRegisterConfig<PCItemRegister> createFor(String path) {
        return new PCItemRegister(Identifier.of(path));
    }

    /**
     * <p>
     *     Creates an item register with the specified namespace and path.
     * </p>
     *
     * @param namespace The namespace for the item
     * @param path The item path
     * @return A new item register
     */
    @Contract("_, _ -> new")
    public static @NotNull ItemPreRegisterConfig<PCItemRegister> createFor(String namespace, String path) {
        return new PCItemRegister(Identifier.of(namespace, path));
    }


    @Contract("_ -> new")
    public static @NotNull ItemPreRegisterConfig<PCItemRegister> createFor(Identifier identifier) {
        return new PCItemRegister(identifier);
    }

    /**
     * <p>
     * Sets the item settings configuration.
     * </p>
     *
     * <p>
     * Default settings are empty. Use this to configure stack size,
     * durability, or other item properties.
     * </p>
     *
     * @param settings The item settings to apply
     * @return This item register
     */
    @Override
    public ItemPreRegisterConfig<PCItemRegister> settings(Item.@NotNull Settings settings) {
        this.settings = settings;
        return this;
    }


    /**
     * <p>
     *     Specifies a custom factory for item creation.
     * </p>
     *
     * <p>
     *     Default factory creates basic {@link Item} instances.
     * </p>
     *
     * @param factory The item creation factory
     * @return This item register
     */
    public ItemPreRegisterConfig<PCItemRegister> factory(@NotNull Function<Item.Settings, Item> factory) {
        this.factory = factory;
        return this;
    }


    /**
     * <p>
     *     Set the model of the item.
     * </p>
     *
     * <p>
     *     The model will be added to the {@link PCItemModelMap}.<br>
     *     Use {@link PCModelProviderFactory} to generate the model.
     * </p>
     * @param model The model of the item
     * @return This item register
     * @see PCModelProviderFactory
     */
    public ItemPostRegisterConfig<PCItemRegister> model(@NotNull Model model) {
        PCItemModelMap.instance().put(this.targetRegistered, model);
        return this;
    }

    /**
     * <p>
     *     Provide a shaped recipe for the item.
     * </p>
     *
     * <p>
     *     The recipe will be added to the {@link PCShapedRecipeMap}.<br>
     *     Use the class {@link PCRecipeProviderFactory} to generate the recipe.
     * </p>
     * @param shapedRecipe The shaped recipe to add
     * @return This item register
     */

    @Deprecated
    public ItemPostRegisterConfig<PCItemRegister> shapedRecipe(@NotNull PCShapedRecipe shapedRecipe) {
        // PCShapedRecipeMap.instance().put(shapedRecipe, this.targetRegistered);
        return this;
    }

    /**
     * <p>
     *     Provide a shapeless recipe for the item.
     * </p>
     *
     * <p>
     *     The recipe will be added to the {@link PCShapelessRecipeMap}.<br>
     *     Use the class {@link PCRecipeProviderFactory} to generate the recipe.
     * </p>
     *
     * @param shapelessRecipe The shapeless recipe of the item
     * @return This item register
     * @see PCRecipeProviderFactory
     */
    @Deprecated
    public ItemPostRegisterConfig<PCItemRegister> shapelessRecipe(@NotNull PCShapelessRecipe shapelessRecipe) {
        // PCShapelessRecipeMap.instance().put(shapelessRecipe, this.targetRegistered);
        return this;
    }

}
