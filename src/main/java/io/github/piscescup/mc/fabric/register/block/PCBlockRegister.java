package io.github.piscescup.mc.fabric.register.block;

import io.github.piscescup.mc.fabric.annotations.schedule.Until;
import io.github.piscescup.mc.fabric.datagen.loot.lists.PCBlockDropperList;
import io.github.piscescup.mc.fabric.datagen.models.maps.PCBlockModelMap;
import io.github.piscescup.mc.fabric.register.PCRegister;
import io.github.piscescup.mc.fabric.register.loot.PCBlockDropRegister;
import io.github.piscescup.mc.fabric.utils.CheckUtils;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.client.data.Model;
import net.minecraft.item.ItemConvertible;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.LootNumberProvider;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;


/**
 * <h1>Description</h1>
 *
 * <p>
 * A register util for registering {@code Block} in  Minecraft.
 * </p>
 *
 * <h1>Usages</h1>
 * Below is a usage:
 * <blockquote><pre>
 *     public static final Block BLOCK = PCBlockRegister.create(MOD_ID, "block1")
 *         .settings(
 *             AbstractBlock.Settings.create()
 *                 .burnable()
 *                 .mapColor(DyeColor.BROWN)
 *         )
 *         .registerAndBuild()
 *         .translate(Language.EN_US, "Test Block1")
 *         .translate(Language.ZH_CN, "测试方块1")
 *         .simpleCubeAll()
 *         .get();
 * </pre></blockquote>
 *
 * @author REN YuanTong
 * @Date 2025-04-06
 * @since 1.0.0
 */
public final class PCBlockRegister
    extends PCRegister<Block, PCBlockRegister, BlockPostRegisterConfig>
    implements BlockPreRegisterConfig, BlockPostRegisterConfig
{
    private AbstractBlock.Settings settings = AbstractBlock.Settings.create();
    private Function<AbstractBlock.Settings, Block> factory = Block::new;


    private PCBlockRegister(Identifier id) {
        super(id);
        this.key = RegistryKey.of(RegistryKeys.BLOCK, id);
    }

    /**
     * <p>
     * Creates a new block register with the given path.
     * </p>
     *
     * @param path the path of the block to be registered
     * @return a new block register
     */
    @Contract("_ -> new")
    public static @NotNull BlockPreRegisterConfig create(String path) {
        return new PCBlockRegister(Identifier.of(path));
    }


    /**
     * <p>
     * Creates a new block register with the given {@code namespace} and {@code path}.
     * </p>
     *
     * @param namespace the namespace of the block to be registered
     * @param path      the path of the block to be registered
     * @return a new block register
     */
    @Contract("_, _ -> new")
    public static @NotNull BlockPreRegisterConfig create(String namespace, String path) {
        return new PCBlockRegister(Identifier.of(namespace, path));
    }

    /**
     * <p>
     * Creates a new block register with the given {@code Identifier}.
     * </p>
     *
     * @param identifier the identifier of the block to be registered
     * @return a new block register
     * @see Identifier#of(String namespace, String path)
     */
    @Contract("_ -> new")
    public static @NotNull BlockPreRegisterConfig create(Identifier identifier) {
        return new PCBlockRegister(identifier);
    }

    /**
     * <p>
     * Set the base settings of the block to be registered.
     * </p>
     *
     * <p>
     * The settings have a default value: {@link AbstractBlock.Settings#create()}
     * </p>
     *
     * @param settings The base block settings
     * @return A block register
     */
    public BlockPreRegisterConfig settings(AbstractBlock.@NotNull Settings settings) {
        this.settings = settings;
        return this;
    }


    /**
     * <p>
     * A factory to create the block to be registered.
     * </p>
     * <p>
     * The factory is a {@link Function} which takes a {@link AbstractBlock.Settings} as input
     * and returns a {@link Block}.
     * </p>
     *
     * <p>
     * The factory has a default value:
     * <blockquote><pre>
     *         Block::new
     *     </pre></blockquote>
     * </p>
     *
     * <p>
     * Take registering a slab block as example:
     * <blockquote><pre>
     *         SlabBlock::new
     *     </pre></blockquote>
     * is equal to
     * <blockquote><pre>
     *         (settings) -&gt; new SlabBlock(settings)
     *     </pre></blockquote>
     * </p>
     *
     * @param factory the factory of creating the block.
     * @return A block register
     */
    public BlockPreRegisterConfig factory(@NotNull Function<AbstractBlock.Settings, Block> factory) {
        this.factory = factory;
        return this;
    }

    @Deprecated(forRemoval = true)
    public BlockPostRegisterConfig model(Model model) {
        return this;
    }

    /**
     * <p>
     * Register the block, and return the registered block.
     * </p>
     *
     * @return The registered block.
     */
    @Override
    public BlockPostRegisterConfig registerAndBuild() {
        Block b = factory.apply(this.settings.registryKey(this.key));

        this.targetRegistered = Registry.register(Registries.BLOCK, this.key, b);
        return this;
    }


    /**
     * <p>
     * Create the settings of the log block.
     * </p>
     *
     * @param topMapColor  The top and the bottom map color of the log block.
     * @param sideMapColor The side map color of the log block.
     * @param sounds       The sound of the log block.
     * @return The settings.
     */
    public static AbstractBlock.Settings createLogSettings(MapColor topMapColor, MapColor sideMapColor, BlockSoundGroup sounds) {
        return AbstractBlock.Settings.create()
            .mapColor(state -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .sounds(sounds)
            .burnable();
    }


    public BlockPostRegisterConfig simpleCubeAll() {
        PCBlockModelMap.instance().addToCubeAll(this.targetRegistered);

        return this;
    }

    private void drop(PCBlockDropRegister register) {
        PCBlockDropperList dropperList = PCBlockDropperList.instance();
        if (dropperList.containsDropper(register))
            return;
        dropperList.addDropper(register);
    }

    /**
     * @deprecated The method for the loot table is deprecated, use the {@link PCBlockDropRegister} instead.
     */
    @Deprecated(since = "1.1.2")
    @Until("1.1.2")
    public BlockPostRegisterConfig drop(@NotNull Block drop) {
        CheckUtils.NullChecker.nonNull(
            drop, "Block cannot be null when using PCBlockRegister.drop(Block)"
        );

        drop(PCBlockDropRegister.createFor(this.targetRegistered)
            .drops(drop, ConstantLootNumberProvider.create(1.0F))
            .getRegister()
        );

        return this;
    }

    /**
     * @deprecated The method for the loot table is deprecated, use the {@link PCBlockDropRegister} instead.
     */
    @Deprecated(since = "1.1.2")
    @Until("1.1.2")
    public BlockPostRegisterConfig drop(@NotNull ItemConvertible itemDropped) {
        CheckUtils.NullChecker.nonNull(
            itemDropped, "ItemConvertible cannot be null when using PCBlockRegister.drop(ItemConvertible)"
        );

        return drop(itemDropped, ConstantLootNumberProvider.create(1.0F));
    }

    /**
     * @deprecated The method for the loot table is deprecated, use the {@link PCBlockDropRegister} instead.
     */
    @Deprecated(since = "1.1.2")
    @Until("1.1.2")
    public BlockPostRegisterConfig drop(@NotNull ItemConvertible itemDropped, LootNumberProvider itemCount) {
        CheckUtils.NullChecker.nonNull(
            itemDropped, "ItemConvertible cannot be null when using PCBlockRegister.drop(ItemConvertible, int)"
        );

        drop(PCBlockDropRegister.createFor(this.targetRegistered)
            .drops(itemDropped, itemCount)
            .getRegister()
        );

        return this;
    }

    /**
     * @deprecated The method for the loot table is deprecated, use the {@link PCBlockDropRegister} instead.
     */
    @Deprecated(since = "1.1.2")
    @Until("1.1.2")
    public BlockPostRegisterConfig dropBuilder(LootTable.Builder lootBuilder) {
        CheckUtils.NullChecker.nonNull(
            lootBuilder, "LootTable.Builder cannot be null when using PCBlockRegister.dropBuilder(LootTable.Builder)"
        );
        drop(PCBlockDropRegister.createFor(this.targetRegistered)
            .dropBuilder(lootBuilder)
            .getRegister()
        );

        return this;
    }

    /**
     * @deprecated The method for the loot table is deprecated, use the {@link PCBlockDropRegister} instead.
     */
    @Deprecated(since = "1.1.2")
    @Until("1.1.2")
    public BlockPostRegisterConfig dropSelf() {
        return drop(this.targetRegistered);
    }

    /**
     * @deprecated The method for the loot table is deprecated, use the {@link PCBlockDropRegister} instead.
     */
    @Deprecated(since = "1.1.2")
    @Until("1.1.2")
    public BlockPostRegisterConfig dropWithSilkTouch() {

        return dropWithSilkTouch(this.targetRegistered);
    }

    /**
     * @deprecated The method for the loot table is deprecated, use the {@link PCBlockDropRegister} instead.
     */
    @Deprecated(since = "1.1.2")
    @Until("1.1.2")
    public BlockPostRegisterConfig dropWithSilkTouch(Block blockWithoutMatchTool) {
        CheckUtils.NullChecker.nonNull(
            blockWithoutMatchTool, "Block cannot be null when using PCBlockRegister.dropWithSilkTouch(Block)"
        );
        drop(
            PCBlockDropRegister.createFor(this.targetRegistered)
                .dropsWithSilkTouch(blockWithoutMatchTool)
                .getRegister()
        );
        return this;
    }

}
