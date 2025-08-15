package io.github.piscescup.mc.fabric.register.tag;

import io.github.piscescup.mc.fabric.register.PCRegister;
import io.github.piscescup.mc.fabric.register.tag.container.PCTagKeyContainer;
import io.github.piscescup.mc.fabric.register.tag.container.TagKeyContainer;
import io.github.piscescup.mc.fabric.register.tag.lists.PCTagKeyContainerList;
import io.github.piscescup.mc.fabric.utils.CheckUtils;
import net.minecraft.block.Block;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.PointOfInterestTypeTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.poi.PointOfInterestType;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

import static io.github.piscescup.mc.fabric.References.MOD_LOGGER;

/**
 * <h2>Description</h2>
 * <p>
 * An abstract, builder-style helper for constructing and registering
 * {@link net.minecraft.registry.tag.TagKey TagKey}s together with the elements
 * (or nested tag keys) they should contain.<br>
 * Concrete subclasses (e.g.&nbsp;{@code PCItemTagKeyRegister}) decide
 * the exact element type&nbsp;{@code T} and supply the concrete
 * {@link TagKeyContainer} implementation&nbsp;{@code C}.
 * </p>
 *
 * <h2>Typical workflow</h2>
 * <ol>
 *   <li>Create a subclass instance via a factory such as
 *       {@code PCItemTagKeyRegister.createFor("my_mod", "my_tag")};</li>
 *   <li>Chain one or more {@code add(…)} or {@code addTag(…)} calls to
 *       populate the container;</li>
 *   <li>Invoke {@code registerAndBuild()} in the concrete subclass to register
 *       the container with your central list and obtain the final
 *       {@code TagKey<T>}.</li>
 * </ol>
 *
 * <h3>Usage</h3>
 * <pre>{@code
 * TagKey<Block> DECORATIVE_BLOCKS =
 *     PCBlockTagKeyRegister.createFor("example_mod", "decorative_blocks")
 *         .add(Blocks.GOLD_BLOCK)
 *         .add(List.of(Blocks.DIAMOND_BLOCK, Blocks.EMERALD_BLOCK))
 *         .addTag(BlockTags.PLANKS)
 *         .registerAndBuild()
 *         .get();
 * }</pre>
 *
 * @param <T> the registry entry type contained in the tag
 * @param <C> the concrete {@link TagKeyContainer} implementation
 *
 * @author  REN YuanTong
 * @since   1.1.0
 */
public final class PCTagKeyRegister<T>
    extends PCRegister<TagKey<T>, PCTagKeyRegister<T>, TagKeyPostRegisterConfig<T>>
    implements TagKeyPreRegisterConfig<T>, TagKeyPostRegisterConfig<T>
{
    /**
     * The container that actually stores the elements and/or nested tag keys.
     * Subclasses are responsible for instantiating it in their constructor.
     */
    private PCTagKeyContainer<T> container;
    private RegistryKey<? extends Registry<T>> registryKey;

    /**
     * Creates a new register with the supplied identifier.
     *
     * @param id the identifier that will back the final {@code TagKey<T>}
     */
    private PCTagKeyRegister(Identifier id, RegistryKey<? extends Registry<T>> registryKey) {
        super(id);
        this.targetRegistered = TagKey.of(
            registryKey,
            id
        );
        this.registryKey = registryKey;
        this.container = PCTagKeyContainer.createFor(this.targetRegistered);
    }

    public static <K> TagKeyPreRegisterConfig<K> createFor(Identifier id, RegistryKey<? extends Registry<K>> registryKey) {
        return new PCTagKeyRegister<>(id, registryKey);
    }

    public static <K> TagKeyPreRegisterConfig<K> createFor(String fullPath, RegistryKey<? extends Registry<K>> registryKey) {
        return new PCTagKeyRegister<>(
            Identifier.tryParse(fullPath),
            registryKey
        );
    }

    public static <K> TagKeyPreRegisterConfig<K> createFor(String namespace, String path, RegistryKey<? extends Registry<K>> registryKey) {
        return new PCTagKeyRegister<>(
            Identifier.of(namespace, path),
            registryKey
        );
    }

    public static <K> TagKeyPreRegisterConfig<K> createForVanilla(@NotNull TagKey<K> vanillaTag) {
        Objects.requireNonNull(vanillaTag);
        return new PCTagKeyRegister<>(
            vanillaTag.id(),
            vanillaTag.registryRef()
        );
    }

    /**
     * Adds a single element to this tag.
     *
     * @param item the element to add; must not be {@code null}
     * @return this register instance
     *
     * @throws IllegalArgumentException if {@code item} is {@code null}
     * @throws IllegalStateException    if the register has already been
     *                                  {@linkplain #registerAndBuild() built}
     */
    public TagKeyPreRegisterConfig<T> add(@NotNull T item) {
        CheckUtils.NullChecker.nonNull(
            item, "Item cannot be null when using PCBlockTagKeyRegister"
        );
        this.container.addContainedThing(item);
        return this;
    }

    /**
     * Adds elements to this tag.
     *
     * @param items the elements to add; must not be {@code null}
     * @param <R>   the concrete register type returned for fluent chaining
     * @return this register instance
     *
     * @throws IllegalArgumentException if {@code items} is {@code null}
     * @throws IllegalStateException    if the register has already been built
     */
    public TagKeyPreRegisterConfig<T> add(@NotNull List<T> items) {
        CheckUtils.NullChecker.nonNull(
            items, "Item cannot be null when using PCBlockTagKeyRegister"
        );
        this.container.addContainedThings(items);
        return this;
    }

    /**
     * Adds another tag key whose contents should be included in this tag at
     * data-pack load time.
     *
     * @param tag the nested {@link TagKey} to add; must not be {@code null}
     * @param <R> the concrete register type returned for fluent chaining
     * @return this register instance
     *
     * @throws IllegalArgumentException if {@code tag} is {@code null}
     * @throws IllegalStateException    if the register has already been built
     */
    public TagKeyPreRegisterConfig<T> addTag(@NotNull TagKey<T> tag) {
        CheckUtils.NullChecker.nonNull(
            tag, "Tag cannot be null when using PCBlockTagKeyRegister"
        );
        this.container.addContainedTag(tag);
        return this;
    }

    /**
     * Adds several nested tag keys at once.
     *
     * @param tags the nested tags to add; must not be {@code null}
     * @param <R>  the concrete register type returned for fluent chaining
     * @return this register instance
     *
     * @throws IllegalArgumentException if {@code tags} is {@code null}
     * @throws IllegalStateException    if the register has already been built
     */
    public TagKeyPreRegisterConfig<T> addTags(@NotNull List<TagKey<T>> tags) {
        CheckUtils.NullChecker.nonNull(
            tags, "Tags cannot be null when using PCBlockTagKeyRegister.add(TagKey...)"
        );
        this.container.addContainedTags(tags);
        return this;
    }

    @Override
    public TagKeyPostRegisterConfig<T> registerAndBuild() {
        if (this.container.isEmpty())
            MOD_LOGGER.warn(
                "The TagKey {} is empty.",
                this.targetRegistered.toString()
            );
        PCTagKeyContainerList.getFor(this.registryKey)
            .addContainer(this.container);
        return this;
    }

    public static final TagKeyPreRegisterConfig<Block> VANILLA_NEED_STONE_TOOLS_TAG =
        createForVanilla(BlockTags.NEEDS_STONE_TOOL);

    public static final TagKeyPreRegisterConfig<Block> VANILLA_NEED_IRON_TOOLS_TAG =
        createForVanilla(BlockTags.NEEDS_IRON_TOOL);

    public static final TagKeyPreRegisterConfig<Block> VANILLA_NEED_DIAMOND_TOOLS_TAG =
        createForVanilla(BlockTags.NEEDS_DIAMOND_TOOL);

    public static final TagKeyPreRegisterConfig<Block> VANILLA_PICKAXE_MINEABLE =
        createForVanilla(BlockTags.PICKAXE_MINEABLE);

    public static final TagKeyPreRegisterConfig<Block> VANILLA_AXE_MINEABLE =
        createForVanilla(BlockTags.AXE_MINEABLE);

    public static final TagKeyPreRegisterConfig<Block> VANILLA_HOE_MINEABLE =
        createForVanilla(BlockTags.HOE_MINEABLE);

    public static final TagKeyPreRegisterConfig<Block> VANILLA_SHOVEL_MINEABLE =
        createForVanilla(BlockTags.SHOVEL_MINEABLE);

    public static final TagKeyPreRegisterConfig<PointOfInterestType> VANILLA_ACQUIRABLE_JOB_SITE =
        createForVanilla(PointOfInterestTypeTags.ACQUIRABLE_JOB_SITE);

}
