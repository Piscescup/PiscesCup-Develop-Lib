package cn.edu.jlu.renyt1621.register.itemgroup;

import cn.edu.jlu.renyt1621.datagen.lang.PCLanguageProvider;
import cn.edu.jlu.renyt1621.register.PCRegister;
import cn.edu.jlu.renyt1621.utils.constant.Language;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.*;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * <h1>Description</h1>
 * <p>
 *     A register util for register the {@code ItemGroup} in Minecraft.
 * </p>
 *
 * <h1>Usages</h1>
 * Below is a simple usage:
 * <blockquote><pre>
 *     public static final ItemGroup ITEM_GROUP1 = PCItemGroupRegister.create(MOD_ID, "item_group1")
 *         .itemGroupBuilder(
 *             ItemGroup.create(ItemGroup.Row.BOTTOM, 7)
 *                 .icon(() -> new ItemStack(Items.DIAMOND))
 *                 .entries(
 *                     (text, entries) -> {
 *                         entries.add(ModItems.ITEM1);
 *                         entries.add(ModBlocks.BLOCK);
 *                     }
 *                 )
 *         )
 *         .registerAndBuild()
 *         .translate(Language.EN_US, "Test ItemGroup1")
 *         .translate(Language.ZH_CN, "测试物品组1")
 *         .get();
 * </pre></blockquote>
 *
 * @author REN YuanTong
 * @Date 2025-04-08
 * @since 1.0.0
 */
public class PCItemGroupRegister
    extends PCRegister<ItemGroup, PCItemGroupRegister>
{
    private ItemGroup.Builder builder;
    private String translateKey;

    public PCItemGroupRegister(Identifier id) {
        super(id);
        this.key = RegistryKey.of(RegistryKeys.ITEM_GROUP, this.id);
    }

    @Contract("_ -> new")
    public static @NotNull PCItemGroupRegister create(String path) {
        return new PCItemGroupRegister(Identifier.of(path));
    }

    @Contract("_, _ -> new")
    public static @NotNull PCItemGroupRegister create(String namespace, String path) {
        return new PCItemGroupRegister(Identifier.of(namespace, path));
    }

    @Contract("_ -> new")
    public static @NotNull PCItemGroupRegister create(Identifier identifier) {
        return new PCItemGroupRegister(identifier);
    }

    public PCItemGroupRegister itemGroupBuilder(ItemGroup.Builder builder) {
        this.builder = builder;
        return this;
    }

    @Override
    public PCItemGroupRegister registerAndBuild() {
        this.translateKey = Util.createTranslationKey("itemGroup", this.id);
        this.t = this.builder
            .displayName(Text.translatable(this.translateKey))
            .build();

        Registry.register(Registries.ITEM_GROUP, this.key, this.t);
        return this;
    }

    /**
     * <p>
     * Translate the {@code ItemGroup} to the language.
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
     *
     * @param lang The language.
     * @param value The string after translation.
     * @return The register.
     * @see PCLanguageProvider
     */
    // @Override
    // public PCItemGroupRegister translate(Language lang, String value) {
    //     checkNotNull("translate(Language, String)");
    //
    //     PCLanguageProvider.LangMap.instance().put(lang, this.translateKey, value);
    //
    //     return this;
    // }

    @Override
    protected PCItemGroupRegister self() {
        return this;
    }
}
