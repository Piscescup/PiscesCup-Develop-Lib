package cn.edu.jlu.renyt1621.reg.item;

import cn.edu.jlu.renyt1621.datagen.lang.PCLanguageProvider;
import cn.edu.jlu.renyt1621.datagen.models.maps.PCItemModelMap;
import cn.edu.jlu.renyt1621.datagen.recipes.maps.PCShapedRecipeMap;
import cn.edu.jlu.renyt1621.datagen.recipes.maps.PCShapelessRecipeMap;
import cn.edu.jlu.renyt1621.reg.PCRegister;
import cn.edu.jlu.renyt1621.reg.recipes.PCShapedRecipe;
import cn.edu.jlu.renyt1621.reg.recipes.PCShapelessRecipe;
import cn.edu.jlu.renyt1621.utils.constant.Language;
import net.minecraft.client.data.Model;
import net.minecraft.data.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;
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
 *     public static final Block BLOCK = PCBlockRegister.create(MOD_ID, "block")
 *         .settings(
 *             AbstractBlock.Settings.create()
 *                 .burnable()
 *                 .mapColor(DyeColor.BROWN)
 *         )
 *         .registerAndBuild()
 *         .translate(Language.EN_US, "Test Block1")
 *         .translate(Language.ZH_CN, "测试方块1")
 *         .get();
 * </pre></blockquote>
 *
 *
 * @author REN YuanTong
 * @Date 2025-04-06
 * @since 1.0.0
 */
public class PCItemRegister
    extends PCRegister<Item, PCItemRegister>
{
    private Item.Settings settings = new Item.Settings();
    private Function<Item.Settings, Item> factory = Item::new;
    private ShapedRecipeJsonBuilder builder;


    // private List<PCShapelessRecipe> shapelessRecipes = new ArrayList<>();


    private PCItemRegister(Identifier id) {
        super(id);
        this.key = RegistryKey.of(RegistryKeys.ITEM, id);
    }

    /**
     * <p>
     *     Registers the item and returns the registered instance.
     * </p>
     *
     * <p>
     *     Automatically handles block item registration when applicable
     *     by adding to {@link Item#BLOCK_ITEMS}.
     * </p>
     *
     * @return The register.
     */
    @Override
    public PCItemRegister registerAndBuild() {
        Item item = factory.apply(settings.registryKey(this.key));

        if ( item instanceof BlockItem blockItem )
            blockItem.appendBlocks(Item.BLOCK_ITEMS, item);

        this.t = Registry.register(Registries.ITEM, key, item);
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
    public static @NotNull PCItemRegister create(String path) {
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
    public static @NotNull PCItemRegister create(String namespace, String path) {
        return new PCItemRegister(Identifier.of(namespace, path));
    }


    @Contract("_ -> new")
    public static @NotNull PCItemRegister create(Identifier identifier) {
        return new PCItemRegister(identifier);
    }

    /**
     * <p>
     *     Sets the item settings configuration.
     * </p>
     *
     * <p>
     *     Default settings are empty. Use this to configure stack size,
     *     durability, or other item properties.
     * </p>
     *
     * @param settings The item settings to apply
     * @return This item register
     */
    public PCItemRegister settings(Item.Settings settings) {
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
    public PCItemRegister factory(Function<Item.Settings, Item> factory) {
        this.factory = factory;
        return this;
    }


    public PCItemRegister model(Model model) {
        checkNotNull("model(Model)");
        PCItemModelMap.instance().put(this.t, model);
        return this;
    }

    // Below are recipes.
    public PCItemRegister shapedRecipe(PCShapedRecipe shapedRecipe) {
        checkNotNull("shapelessRecipe(List, int)");
        PCShapedRecipeMap.instance().put(shapedRecipe, this.t);
        return this;
    }

    public PCItemRegister shapedRecipe(List<PCShapedRecipe> shapedRecipes) {
        checkNotNull("shapelessRecipe(List, int)");
        PCShapedRecipeMap shapedRecipeMap = PCShapedRecipeMap.instance();
        shapedRecipes.forEach(recipe -> shapedRecipeMap.put(recipe, this.t));
        return this;
    }

    public PCItemRegister shapelessRecipe(PCShapelessRecipe shapelessRecipe) {
        checkNotNull("shapelessRecipe(List, int)");
        PCShapelessRecipeMap.instance().put(shapelessRecipe, this.t);
        return this;
    }

    public PCItemRegister shapelessRecipe(List<PCShapelessRecipe> shapelessRecipes) {
        checkNotNull("shapelessRecipe(List, int)");
        PCShapelessRecipeMap shapedRecipeMap = PCShapelessRecipeMap.instance();
        shapelessRecipes.forEach(recipe -> shapedRecipeMap.put(recipe, this.t));
        return this;
    }


    /**
     * <p>
     * Translate the {@code Item} to the language.
     * </p>
     *
     * <p>
     *     You should use {@link PCLanguageProvider} to generate the language file.
     * </p>
     *
     * <p>
     *     You should use the method{@link #registerAndBuild()} before you use this method.
     *     Because this method is depended on the method {@link #registerAndBuild()}.
     * </p>
     * @param lang The language to translate.
     * @param value The string after translation.
     * @return The register.
     * @see PCLanguageProvider
     */
    @Override
    public PCItemRegister translate(Language lang, String value) {
        checkNotNull("translate(Language, String)");
        PCLanguageProvider.LangMap.instance().put(lang, this.t.getTranslationKey(), value);
        return this;
    }

    @Override
    protected PCItemRegister self() {
        return this;
    }



}
