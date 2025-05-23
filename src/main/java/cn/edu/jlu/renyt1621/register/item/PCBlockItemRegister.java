package cn.edu.jlu.renyt1621.register.item;

import cn.edu.jlu.renyt1621.datagen.models.maps.PCItemModelMap;
import cn.edu.jlu.renyt1621.datagen.recipes.maps.PCShapedRecipeMap;
import cn.edu.jlu.renyt1621.datagen.recipes.maps.PCShapelessRecipeMap;
import cn.edu.jlu.renyt1621.exceptions.register.BlockItemUnsupportedTranslateException;
import cn.edu.jlu.renyt1621.register.PCRegister;
import cn.edu.jlu.renyt1621.datagen.recipes.craft.PCShapedRecipe;
import cn.edu.jlu.renyt1621.datagen.recipes.craft.PCShapelessRecipe;
import cn.edu.jlu.renyt1621.utils.constant.Language;
import net.minecraft.block.Block;
import net.minecraft.client.data.Model;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.BiFunction;

/**
 * <h1>Description</h1>
 *
 * <p>
 *     A register util for registering {@code BlockItem} in Minecraft.
 * </p>
 *
 * <h1>Usages</h1>
 * Below is a simple usage:
 * <blockquote><pre>
 *     public static final Item BLOCK_ITEM = PCBlockItemRegister.create(BLOCK)
 *         .settings(new Item.Settings()
 *             .maxCount(16)
 *             .fireproof()
 *             .rarity(Rarity.COMMON)
 *         )
 *         .registerAndBuild()
 *         .shapelessRecipe(PCShapelessRecipe.Builder.create()
 *             .category(RecipeCategory.BUILDING_BLOCKS)
 *             .input(ItemTags.PLANKS)
 *             .input(ItemTags.BUTTONS)
 *             .input(Items.IRON_INGOT)
 *             .count(4)
 *             .criterion("has_planks", Items.IRON_INGOT)
 *             .build()
 *         )
 *         .model(Models.GENERATED)
 *         .get();
 * </pre></blockquote>
 *
 * @author REN YuanTong
 * @Date 2025-04-06
 * @since 1.0.0
 */
public class PCBlockItemRegister
    extends PCRegister<Item, PCBlockItemRegister>
{
    private Block block;
    private Item.Settings settings = new Item.Settings().useBlockPrefixedTranslationKey();
    private BiFunction<Block, Item.Settings, Item> factory = BlockItem::new;

    private PCBlockItemRegister() {
        super();
    }

    /**
     * <p>
     *     Creates a block item register for the given block.
     * </p>
     *
     * <p>
     *     Automatically derives the RegistryKey from the block's registry entry,
     *     maintaining the same identifier as the associated block.
     * </p>
     *
     * @param block The block to create an item for
     * @return A new block item register
     */
    public static @NotNull PCBlockItemRegister create(@NotNull Block block) {
        PCBlockItemRegister reg = new PCBlockItemRegister();
        reg.block = block;
        reg.key = RegistryKey.of(RegistryKeys.ITEM, block.getRegistryEntry().registryKey().getValue());
        return reg;
    }

    /**
     * <p>
     *     Sets the item settings for the block item.
     * </p>
     *
     * <p>
     *     Default value enables block-prefixed translation keys automatically.
     *     Any custom settings provided will retain this behavior through
     *     {@link Item.Settings#useBlockPrefixedTranslationKey()}.
     * </p>
     *
     * @param settings The item settings to use
     * @return This block item register
     */
    public PCBlockItemRegister settings(Item.Settings settings) {
        this.settings = settings.useBlockPrefixedTranslationKey();
        return this;
    }

    public PCBlockItemRegister factory(BiFunction<Block, Item.Settings, Item> factory) {
        this.factory = factory;
        return this;
    }

    /**
     * <p>
     *     Registers the block item and returns the registered item instance.
     * </p>
     *
     * <p>
     *     Automatically handles block item registration by adding it to
     *     {@link Item#BLOCK_ITEMS} when applicable.
     * </p>
     *
     * @return The block item to be registered
     */
    @Override
    public PCBlockItemRegister registerAndBuild() {
        Item item = factory.apply(this.block, settings.registryKey(this.key));

        if ( item instanceof BlockItem blockItem )
            blockItem.appendBlocks(Item.BLOCK_ITEMS, item);

        this.t = Registry.register(Registries.ITEM, key, item);
        return this;
    }

    public PCBlockItemRegister model(Model model) {
        PCItemModelMap.instance().put(this.t, model);
        return this;
    }

    // Below are recipes.
    public PCBlockItemRegister shapedRecipe(PCShapedRecipe shapedRecipe) {
        checkNotNull("shapelessRecipe(List, int)");
        PCShapedRecipeMap.instance().put(shapedRecipe, this.t);
        return this;
    }

    public PCBlockItemRegister shapedRecipe(List<PCShapedRecipe> shapedRecipes) {
        checkNotNull("shapelessRecipe(List, int)");
        PCShapedRecipeMap shapedRecipeMap = PCShapedRecipeMap.instance();
        shapedRecipes.forEach(recipe -> shapedRecipeMap.put(recipe, this.t));
        return this;
    }

    public PCBlockItemRegister shapelessRecipe(PCShapelessRecipe shapelessRecipe) {
        checkNotNull("shapelessRecipe(List, int)");
        PCShapelessRecipeMap.instance().put(shapelessRecipe, this.t);
        return this;
    }

    public PCBlockItemRegister shapelessRecipe(List<PCShapelessRecipe> shapelessRecipes) {
        checkNotNull("shapelessRecipe(List, int)");
        PCShapelessRecipeMap shapedRecipeMap = PCShapelessRecipeMap.instance();
        shapelessRecipes.forEach(recipe -> shapedRecipeMap.put(recipe, this.t));
        return this;
    }


    /**
     * <strong>
     *     Translating 'Block Item' is prohibited.
     * </strong>
     */
    @Override
    public PCBlockItemRegister translate(Language lang, String value) {
        throw new BlockItemUnsupportedTranslateException(this.t);
    }

    @Override
    protected PCBlockItemRegister self() {
        return this;
    }
}
