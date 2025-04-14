package cn.edu.jlu.renyt1621.reg.item;

import cn.edu.jlu.renyt1621.deprecated.annotations.scanners.datagen.lang.LangMap;
import cn.edu.jlu.renyt1621.reg.PCRegister;
import cn.edu.jlu.renyt1621.utils.constant.Language;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

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
    public static PCItemRegister create(String path) {
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
    public static PCItemRegister create(String namespace, String path) {
        return new PCItemRegister(Identifier.of(namespace, path));
    }


    public static PCItemRegister create(Identifier identifier) {
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

    /**
     * <p>
     * Translate the {@code Item} to the language.
     * </p>
     *
     * <p>
     *     You should use {@link cn.edu.jlu.renyt1621.datagen.lang.PCDLLanguageProvider} to generate the language file.
     * </p>
     *
     * <p>
     *     You should use the method{@link #registerAndBuild()} before you use this method.
     *     Because this method is depended on the method {@link #registerAndBuild()}.
     * </p>
     * @param lang The language to translate.
     * @param value The string after translation.
     * @return The register.
     * @see cn.edu.jlu.renyt1621.datagen.lang.PCDLLanguageProvider
     */
    @Override
    public PCItemRegister translate(Language lang, String value) {
        checkNotNull("translate(Language, String)");
        LangMap.instance().put(lang, this.t.getTranslationKey(), value);
        return this;
    }
}
