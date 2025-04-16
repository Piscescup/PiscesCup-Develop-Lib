package cn.edu.jlu.renyt1621.reg.block;

import cn.edu.jlu.renyt1621.datagen.lang.PCDLLanguageProvider;
import cn.edu.jlu.renyt1621.reg.PCRegister;
import cn.edu.jlu.renyt1621.reg.item.PCBlockItemRegister;
import cn.edu.jlu.renyt1621.utils.constant.Language;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.client.data.Model;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import java.util.function.Function;

/**
 * <h1>Description</h1>
 *
 * <p>
 *     A register util for registering {@code Block} in  Minecraft.
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
     *     Creates a new block register with the given path.
     * </p>
     * @param path the path of the block to be registered
     * @return a new block register
     */
    public static PCBlockRegister create(String path) {
        return new PCBlockRegister(Identifier.of(path));
    }


    /**
     * <p>
     *     Creates a new block register with the given {@code namespace} and {@code path}.
     * </p>
     *
     * @param namespace the namespace of the block to be registered
     * @param path the path of the block to be registered
     * @return a new block register
     */
    public static PCBlockRegister create(String namespace, String path) {
        return new PCBlockRegister(Identifier.of(namespace, path));
    }

    /**
     * <p>
     *     Creates a new block register with the given {@code Identifier}.
     * </p>
     *
     * @param identifier the identifier of the block to be registered
     * @return a new block register
     * @see Identifier#of(String namespace, String path)
     */
    public static PCBlockRegister create(Identifier identifier) {
        return new PCBlockRegister(identifier);
    }

    /**
     * <p>
     *     Set the base settings of the block to be registered.
     * </p>
     *
     * <p>
     *     The settings have a default value: {@link AbstractBlock.Settings#create()}
     * </p>
     * @param settings The base block settings
     * @return A block register
     */
    public PCBlockRegister settings(AbstractBlock.Settings settings) {
        this.settings = settings;
        return this;
    }


    /**
     * <p>
     *     A factory to create the block to be registered.
     * </p>
     * <p>
     *     The factory is a {@link Function} which takes a {@link AbstractBlock.Settings} as input
     *     and returns a {@link Block}.
     * </p>
     *
     * <p>
     *     The factory has a default value:
     *     <blockquote><pre>
     *         Block::new
     *     </pre></blockquote>
     * </p>
     *
     * <p>
     *     Take registering a slab block for example:
     *     <blockquote><pre>
     *         SlabBlock::new
     *     </pre></blockquote>
     *     is equal to
     *     <blockquote><pre>
     *         (settings) -&gt; new SlabBlock(settings)
     *     </pre></blockquote>
     * </p>
     * @param factory the factory of creating the block.
     * @return A block register
     */
    public PCBlockRegister factory(Function<AbstractBlock.Settings, Block> factory) {
        this.factory = factory;
        return this;
    }

    public PCBlockRegister model(Model model) {
        return this;
    }

    /**
     * <p>
     *     Register the block, and return the registered block.
     * </p>
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
     *     You should use {@link cn.edu.jlu.renyt1621.datagen.lang.PCDLLanguageProvider} to generate the language file.
     * </p>
     *
     * <p>
     *     You should use the method{@link #registerAndBuild()} before you use this method.
     *     Because this method is depended on the method {@link #registerAndBuild()}.
     * </p>
     * @param lang The language.
     * @param value The string after translation.
     * @return The register.
     * @see cn.edu.jlu.renyt1621.datagen.lang.PCDLLanguageProvider
     */
    @Override
    public PCBlockRegister translate(Language lang, String value) {
        checkNotNull("translate(Language, String)");

        PCDLLanguageProvider.LangMap.instance().put(lang, this.t, value);

        return this;
    }


    @Override
    protected PCBlockRegister self() {
        return this;
    }


    /**
     * <p>
     *     Create the settings of the log block.
     * </p>
     * @param topMapColor The top and the bottom map color of the log block.
     * @param sideMapColor The side map color of the log block.
     * @param sounds The sound of the log block.
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
}
