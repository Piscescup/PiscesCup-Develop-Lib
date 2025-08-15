package io.github.piscescup.mc.fabric.register.item;

import io.github.piscescup.mc.fabric.datagen.models.maps.PCItemModelMap;
import io.github.piscescup.mc.fabric.register.recipe.PCShapedRecipe;
import io.github.piscescup.mc.fabric.register.recipe.PCShapelessRecipe;
import io.github.piscescup.mc.fabric.exceptions.register.BlockItemUnsupportedTranslateException;
import io.github.piscescup.mc.fabric.register.PCRegister;
import io.github.piscescup.mc.fabric.utils.constant.Language;
import net.minecraft.block.Block;
import net.minecraft.client.data.Model;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

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
 *             .buildEntry()
 *         )
 *         .model(Models.GENERATED)
 *         .get();
 * </pre></blockquote>
 *
 * @author REN YuanTong
 * @Date 2025-04-06
 * @since 1.0.0
 */
public final class PCBlockItemRegister
    extends PCRegister<Item, PCBlockItemRegister, ItemPostRegisterConfig<PCBlockItemRegister>>
    implements ItemPreRegisterConfig<PCBlockItemRegister>, ItemPostRegisterConfig<PCBlockItemRegister>
{
    private Block block;
    private Item.Settings settings = new Item.Settings().useBlockPrefixedTranslationKey();
    private BiFunction<Block, Item.Settings, Item> biFactory = BlockItem::new;
    private Function<Item.Settings, Item> factory = itemSettings -> biFactory.apply(block, itemSettings);

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
    public static @NotNull ItemPreRegisterConfig<PCBlockItemRegister> create(@NotNull Block block) {
        PCBlockItemRegister reg = new PCBlockItemRegister();
        reg.block = block;
        reg.key = RegistryKey.of(
            RegistryKeys.ITEM,
            block.getRegistryEntry().registryKey().getValue()
        );
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
    public ItemPreRegisterConfig<PCBlockItemRegister> settings(Item.@NotNull Settings settings) {
        this.settings = settings.useBlockPrefixedTranslationKey();
        return this;
    }

    @Override
    public ItemPreRegisterConfig<PCBlockItemRegister> factory(@NotNull Function<Item.Settings, Item> factory) {
        this.factory = factory;
        return this;
    }

    public ItemPreRegisterConfig<PCBlockItemRegister> factory(BiFunction<Block, Item.Settings, Item> factory) {
        this.factory = itemSettings -> (Item) factory.apply(block, itemSettings);
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
    public ItemPostRegisterConfig<PCBlockItemRegister> registerAndBuild() {
        Item item = factory.apply(settings.registryKey(this.key));

        if ( item instanceof BlockItem blockItem )
            blockItem.appendBlocks(Item.BLOCK_ITEMS, item);

        this.targetRegistered = Registry.register(Registries.ITEM, key, item);
        return this;
    }

    public ItemPostRegisterConfig<PCBlockItemRegister> model(@NotNull Model model) {
        PCItemModelMap.instance().put(this.targetRegistered, model);
        return this;
    }

    // Below are recipes.

    /**
     * @deprecated Use {@link PCShapedRecipe#createFor(RecipeCategory, Item, int)} instead.
     */
    @Deprecated
    public ItemPostRegisterConfig<PCBlockItemRegister> shapedRecipe(@NotNull PCShapedRecipe shapedRecipe) {
        // PCShapedRecipeMap.instance().put(shapedRecipe, this.targetRegistered);
        return this;
    }

    /**
     * @deprecated Use {@link PCShapedRecipe#createFor(RecipeCategory, Item, int)} instead.
     */
    @Deprecated
    public ItemPostRegisterConfig<PCBlockItemRegister> shapedRecipe(List<PCShapedRecipe> shapedRecipes) {
        // PCShapedRecipeMap shapedRecipeMap = PCShapedRecipeMap.instance();
        // shapedRecipes.forEach(recipe -> shapedRecipeMap.put(recipe, this.targetRegistered));
        return this;
    }

    /**
     * @deprecated Use {@link PCShapedRecipe#createFor(RecipeCategory, Item, int)} instead.
     */
    @Deprecated
    public ItemPostRegisterConfig<PCBlockItemRegister> shapelessRecipe(@NotNull PCShapelessRecipe shapelessRecipe) {

        return this;
    }

    /**
     * @deprecated Use {@link PCShapedRecipe#createFor(RecipeCategory, Item, int)} instead.
     */
    @Deprecated
    public ItemPostRegisterConfig<PCBlockItemRegister> shapelessRecipe(List<PCShapelessRecipe> shapelessRecipes) {
        // PCShapelessRecipeMap shapedRecipeMap = PCShapelessRecipeMap.instance();
        // shapelessRecipes.forEach(recipe -> shapedRecipeMap.put(recipe, this.targetRegistered));
        return this;
    }

    /**
     * <strong>
     *     Translating 'Block Item' is prohibited.
     * </strong>
     */
    @Override
    public ItemPostRegisterConfig<PCBlockItemRegister> translate(Language lang, String value) {
        throw new BlockItemUnsupportedTranslateException(this.targetRegistered);
    }

}
