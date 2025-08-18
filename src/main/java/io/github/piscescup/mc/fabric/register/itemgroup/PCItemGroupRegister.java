package io.github.piscescup.mc.fabric.register.itemgroup;

import io.github.piscescup.mc.fabric.annotations.schedule.Until;
import io.github.piscescup.mc.fabric.register.PCRegister;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.*;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Supplier;

import static io.github.piscescup.mc.fabric.References.MOD_LOGGER;

/**
 * <h1>Description</h1>
 * <p>
 *     A register util for register the {@code ItemGroup} in Minecraft.
 * </p>
 *
 * <h1>Usages</h1>
 * Below is a simple usage:
 * <blockquote><pre>
 * public static final ItemGroup PC_ITEM_GROUP1 = PCItemGroupRegister.create(MOD_ID, "item_group1")
 *     .position(ItemGroup.Row.TOP, 7)
 *     .icon(ModItems.PC_ITEM1)
 *     .addEntry(ModItems.PC_ITEM1)
 *     .addEntry(ModBlocks.PC_BLOCK)
 *     .registerAndBuild()
 *     .translate(Language.EN_US, "Test ItemGroup1")
 *     .translate(Language.ZH_CN, "测试物品组1")
 *     .get();
 * </pre></blockquote>
 *
 * @author REN YuanTong
 * @Date 2025-04-08
 * @since 1.0.0
 */
public final class PCItemGroupRegister
    extends PCRegister<ItemGroup, PCItemGroupRegister, ItemGroupPostRegisterConfig>
    implements ItemGroupPostRegisterConfig, ItemGroupPreRegisterConfig,
        ItemGroupPreRegisterConfig.PositionConfig, ItemGroupPreRegisterConfig.IconConfig,
        ItemGroupPreRegisterConfig.OptionalConfig
{
    private ItemGroup.Builder builder;
    private String translateKey;

    private ItemGroup.Row row;
    private int column = -1;
    private Text displayName = Text.empty();
    private Supplier<ItemStack> iconSupplier = () -> ItemStack.EMPTY;
    private boolean scrollbar = true;
    private boolean renderName = true;
    private boolean special = false;
    private Identifier texture = ItemGroup.getTabTextureId("items");

    private final Map<ItemConvertible, ItemGroup.StackVisibility> items = new HashMap<>();

    private PCItemGroupRegister(Identifier id) {
        super(id);
        this.key = RegistryKey.of(RegistryKeys.ITEM_GROUP, this.id);
    }

    @Contract("_ -> new")
    public static @NotNull ItemGroupPreRegisterConfig.PositionConfig create(String fullPath) {
        return new PCItemGroupRegister(Identifier.of(fullPath));
    }

    @Contract("_, _ -> new")
    public static @NotNull ItemGroupPreRegisterConfig.PositionConfig create(String namespace, String path) {
        return new PCItemGroupRegister(Identifier.of(namespace, path));
    }

    @Contract("_ -> new")
    public static @NotNull ItemGroupPreRegisterConfig.PositionConfig create(Identifier identifier) {
        return new PCItemGroupRegister(identifier);
    }

    @Until("1.1.1")
    @Deprecated(forRemoval = true)
    public ItemGroupPreRegisterConfig itemGroupBuilder(ItemGroup.Builder builder) {
        this.builder = builder;
        return this;
    }

    @Override
    public ItemGroupPreRegisterConfig.IconConfig position(ItemGroup.Row row, int column) {
        this.builder = new ItemGroup.Builder(row, column);
        return this;
    }

    @Override
    public OptionalConfig displayName(@NotNull Text displayName) {
        Objects.requireNonNull(displayName);
        MOD_LOGGER.warn(
            "The method displayName() in PCItemGroupRegister is deprecated, use the method translate() instead after using the registerAndBuild()."
        );
        this.displayName = displayName;
        return this;
    }

    @Override
    public OptionalConfig icon(@NotNull ItemConvertible icon) {
        Objects.requireNonNull(icon);
        this.iconSupplier = () -> new ItemStack(icon);
        return this;
    }

    @Override
    public ItemGroupPreRegisterConfig.OptionalConfig addEntry(@NotNull ItemConvertible item) {
        Objects.requireNonNull(item);
        this.items.put(item, ItemGroup.StackVisibility.PARENT_AND_SEARCH_TABS);
        return this;
    }
    @Override
    public ItemGroupPreRegisterConfig.OptionalConfig addEntry(@NotNull ItemConvertible item, ItemGroup.StackVisibility visibility) {
        Objects.requireNonNull(item);
        this.items.put(item, ItemGroup.StackVisibility.PARENT_AND_SEARCH_TABS);
        return this;
    }

    @Override
    public ItemGroupPreRegisterConfig.OptionalConfig addEntries(ItemConvertible... items) {
        Objects.requireNonNull(items);
        Map<ItemConvertible, ItemGroup.StackVisibility> map = new HashMap<>();
        Arrays.stream(items)
                .forEach(item -> map.put(item, ItemGroup.StackVisibility.PARENT_AND_SEARCH_TABS));
        this.items.putAll(map);
        return this;
    }

    @Override
    public ItemGroupPreRegisterConfig.OptionalConfig addEntries(@NotNull Map<ItemConvertible, ItemGroup.StackVisibility> entries) {
        Objects.requireNonNull(entries);
        this.items.putAll(entries);
        return this;
    }

    @Override
    public ItemGroupPreRegisterConfig.OptionalConfig special() {
        this.special = true;
        return this;
    }

    @Override
    public ItemGroupPreRegisterConfig.OptionalConfig noRenderedName() {
        this.renderName = false;
        return this;
    }

    @Override
    public ItemGroupPreRegisterConfig.OptionalConfig noScrollbar() {
        this.scrollbar = false;
        return this;
    }

    @Override
    public ItemGroupPreRegisterConfig.OptionalConfig texture(Identifier texture) {
        this.texture = texture;
        return this;
    }

    @Override
    public ItemGroupPostRegisterConfig registerAndBuild() {
        if (this.items.isEmpty()) {
            MOD_LOGGER.warn(
                "The Item Group {} does not have any items to show. So this Item Group may be not displayed.",
                this.key
            );
        }

        this.translateKey = Util.createTranslationKey("itemGroup", this.id);
        this.builder
            .icon(iconSupplier)
            .texture(texture)
            .displayName(Text.translatable(this.translateKey))
            .entries(
                (displayContext, entries) -> items.forEach(entries::add)
            );

        if (this.special) this.builder.special();
        if (!this.renderName) this.builder.noRenderedName();
        if (!this.scrollbar) this.builder.noScrollbar();

        this.targetRegistered = this.builder.build();

        Registry.register(Registries.ITEM_GROUP, this.key, this.targetRegistered);
        return this;
    }


}
