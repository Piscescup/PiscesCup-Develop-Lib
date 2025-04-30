package cn.edu.jlu.renyt1621.register.block;

import cn.edu.jlu.renyt1621.datagen.lang.PCLanguageProvider;
import cn.edu.jlu.renyt1621.datagen.loot.drop.PCBlockDrop;
import cn.edu.jlu.renyt1621.datagen.loot.map.PCBlockDropMap;
import cn.edu.jlu.renyt1621.datagen.loot.map.PCSilkTouchDropMap;
import cn.edu.jlu.renyt1621.datagen.models.maps.PCBlockModelMap;
import cn.edu.jlu.renyt1621.register.PCRegister;
import cn.edu.jlu.renyt1621.utils.CheckUtils;
import cn.edu.jlu.renyt1621.utils.constant.Language;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.client.data.Model;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.loot.LootTable;
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

import static cn.edu.jlu.renyt1621.References.MOD_LOGGER;

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
public class PCBlockRegister
    extends PCRegister<Block, PCBlockRegister>
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
    public static @NotNull PCBlockRegister create(String path) {
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
    public static @NotNull PCBlockRegister create(String namespace, String path) {
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
    public static @NotNull PCBlockRegister create(Identifier identifier) {
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
    public PCBlockRegister settings(AbstractBlock.Settings settings) {
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
    public PCBlockRegister factory(Function<AbstractBlock.Settings, Block> factory) {
        this.factory = factory;
        return this;
    }

    @Deprecated(forRemoval = true)
    public PCBlockRegister model(Model model) {
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
    public PCBlockRegister registerAndBuild() {
        Block b = factory.apply(this.settings.registryKey(this.key));

        this.t = Registry.register(Registries.BLOCK, this.key, b);
        return this;
    }

    /**
     * <p>
     * Translate the {@code Block} to the language.
     * </p>
     *
     * <p>
     * You should use {@link PCLanguageProvider} to generate the language file.
     * </p>
     *
     * <p>
     * You should use the method{@link #registerAndBuild()} before you use this method.
     * Because this method is depended on the method {@link #registerAndBuild()}.
     * </p>
     *
     * @param lang  The language.
     * @param value The string after translation.
     * @return The register.
     * @see PCLanguageProvider
     */
    @Override
    public PCBlockRegister translate(Language lang, String value) {
        checkNotNull("translate(Language, String)");

        PCLanguageProvider.LangMap.instance().put(lang, this.t, value);

        return this;
    }


    @Override
    protected PCBlockRegister self() {
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


    public PCBlockRegister simpleCubeAll() {
        checkNotNull("simpleCubeAll()");
        PCBlockModelMap.instance().addToCubeAll(this.t);

        return this;
    }

    public PCBlockRegister drop(PCBlockDrop pcBlockDrop) {
        checkNotNull("drop(PCBlockDrop)");
        CheckUtils.checkIsNullThenThrow(
            pcBlockDrop, "PCBlockDrop cannot be null when using PCBlockRegister.drop(PCBlockDrop)"
        );
        PCBlockDropMap blockDropMap = PCBlockDropMap.getInstance();

        if (blockDropMap.containsBlock(this.t))
            MOD_LOGGER.warning(
                "Warning! You have always added the loot table for the Block: %s. The loot table added will be override."
                    .formatted(this.t.toString())
            );
        blockDropMap.putDrop(this.t, pcBlockDrop);

        return this;
    }

    public PCBlockRegister drop(Block drop) {
        checkNotNull("drop(Block)");
        CheckUtils.checkIsNullThenThrow(
            drop, "Block cannot be null when using PCBlockRegister.drop(Block)"
        );

        return drop(PCBlockDrop.Builder.create()
            .dropBlock(drop)
            .build()
        );
    }

    public PCBlockRegister drop(ItemConvertible itemDropped) {
        checkNotNull("drop(ItemConvertible)");
        CheckUtils.checkIsNullThenThrow(
            itemDropped, "ItemConvertible cannot be null when using PCBlockRegister.drop(ItemConvertible)"
        );

        return drop(PCBlockDrop.Builder.create()
            .dropItem(itemDropped)
            .build()
        );
    }

    public PCBlockRegister drop(ItemConvertible itemDropped, int itemCount) {
        checkNotNull("drop(ItemConvertible, int)");
        CheckUtils.checkIsNullThenThrow(
            itemDropped, "ItemConvertible cannot be null when using PCBlockRegister.drop(ItemConvertible, int)"
        );

        return drop(PCBlockDrop.Builder.create()
            .dropItem(itemDropped)
            .dropItemCount(itemCount)
            .build()
        );
    }


    public PCBlockRegister dropBuilder(LootTable.Builder lootBuilder) {
        checkNotNull("dropBuilder(LootTable.Builder)");
        CheckUtils.checkIsNullThenThrow(
            lootBuilder, "LootTable.Builder cannot be null when using PCBlockRegister.dropBuilder(LootTable.Builder)"
        );
        return drop(PCBlockDrop.Builder.create()
            .lootBuilder(lootBuilder)
            .build()
        );
    }

    public PCBlockRegister dropSelf() {
        checkNotNull("dropSelf()");
        return drop(this.t);
    }


    public PCBlockRegister dropWithSilkTouch() {
        checkNotNull("dropWithSilkTouch()");
        return dropWithSilkTouch(this.t);
    }


    public PCBlockRegister dropWithSilkTouch(Block blockDropped) {
        checkNotNull("dropWithSilkTouch(Block)");
        CheckUtils.checkIsNullThenThrow(
            blockDropped, "Block cannot be null when using PCBlockRegister.dropWithSilkTouch(Block)"
        );

        PCSilkTouchDropMap silkTouchDropMap = PCSilkTouchDropMap.instance();

        if (silkTouchDropMap.containsBlock(this.t))
            MOD_LOGGER.warning(
                "Warning! You have always added the silk touch loot table for the Block: %s. The loot table added will be override."
                    .formatted(this.t.toString())
            );

        silkTouchDropMap.put(this.t, blockDropped);

        return this;
    }

}
