package cn.edu.jlu.renyt1621.register.tag;

import cn.edu.jlu.renyt1621.datagen.tag.container.PCItemTag;
import cn.edu.jlu.renyt1621.datagen.tag.map.PCItemTagKeyMap;
import cn.edu.jlu.renyt1621.register.PCRegister;
import cn.edu.jlu.renyt1621.utils.CheckUtils;
import cn.edu.jlu.renyt1621.utils.constant.Language;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.*;

/**
 * <h2>Description</h2>
 * A util for registering item tag key.
 * <h2>Usages</h2>
 * <p>
 * You can use the method {@link #create(String namespace, String path)}, {@link #create(String path)} or {@link #create(Identifier identifier)}
 * to create a new {@code PCItemTagKeyRegister}. <br>
 * You can also use the method {@link #createForVanilla(TagKey)} to create a new {@code PCItemTagKeyRegister} for a vanilla tag key.
 * </p>
 * <p>
 * Below is a simple usage:
 * </p>
 * <blockquote><pre>
 * public static final TagKey<Item> TAG_KEY_1 =
 *     PCItemTagKeyRegister.create(MOD_ID, "tag/tag1")
 *         .addItem(ModItems.ITEM1)
 *         .addItem(ModItems.ITEM2)
 *         .addTag(ItemTags.PLANKS)
 *         .registerAndBuild()
 *         .get();
 * </pre></blockquote>
 *
 * @see PCItemTag
 * @author REN YuanTong
 * @Date 2025-04-21
 * @since 1.0.0
 */
public class PCItemTagKeyRegister
    extends PCRegister<TagKey<Item>, PCItemTagKeyRegister>
{
    private final PCItemTag pcItemTag;

    private PCItemTagKeyRegister(Identifier id) {
        super(id);
        this.t = TagKey.of(RegistryKeys.ITEM, this.id);
        this.pcItemTag = new PCItemTag(this.t);
    }

    /**
     * Create a new PCItemTagKeyRegister for the given {@code path}
     * @param path The path of the PCItemTagKeyRegister
     */
    public static PCItemTagKeyRegister create(String path) {
        return new PCItemTagKeyRegister(Identifier.of(path));
    }

    /**
     * Create a new PCItemTagKeyRegister for the given {@code identifier}
     * @param identifier The identifier of the PCItemTagKeyRegister
     */
    public static PCItemTagKeyRegister create(Identifier identifier) {
        return new PCItemTagKeyRegister(identifier);
    }

    /**
     * Create a new PCItemTagKeyRegister for the given {@code namespace} and {@code path}
     * @param namespace The namespace of the PCItemTagKeyRegister
     * @param path The path of the PCItemTagKeyRegister
     */
    public static PCItemTagKeyRegister create(String namespace, String path) {
        return new PCItemTagKeyRegister(Identifier.of(namespace, path));
    }

    /**
     * Create a new PCItemTagKeyRegister for Vanilla Tag.
     * @param vanillaTag The vanilla tag.
     */
    public static PCItemTagKeyRegister createForVanilla(TagKey<Item> vanillaTag) {
        return new PCItemTagKeyRegister(vanillaTag.id());
    }


    @Override
    public PCItemTagKeyRegister registerAndBuild() {
        if ( this.pcItemTag.isEmpty() )
            throw new IllegalArgumentException(
                "`Item` or `Tag` cannot be empty when using PCItemTagKeyRegister."
            );

        PCItemTagKeyMap.instance().putItemTag(this.t, this.pcItemTag);

        return this;
    }

    /**
     * Add the given item to the PCItemTagKeyRegister.
     * @param item The item to be added.
     */
    public PCItemTagKeyRegister addItem(Item item) {
        CheckUtils.checkIsNullThenThrow(
            item, "Item cannot be null when using PCItemTagKeyRegister.Builder"
        );
        this.pcItemTag.addItem(item);
        return this;
    }

    /**
     * Add the given items to the PCItemTagKeyRegister.
     * @param items The items to be added.
     */
    public PCItemTagKeyRegister addItems(List<Item> items) {
        CheckUtils.checkAnyIsNullThenThrow(
            items, "Items cannot be null when using PCItemTagKeyRegister.Builder"
        );

        this.pcItemTag.addItems(items);
        return this;
    }

    /**
     * Add the given tag to the PCItemTagKeyRegister.
     * @param tag The tag to be added.
     */
    public PCItemTagKeyRegister addTag(TagKey<Item> tag) {
        CheckUtils.checkIsNullThenThrow(
            tag, "Tag cannot be null when using PCItemTagKeyRegister.Builder"
        );

        this.pcItemTag.addTag(tag);

        return this;
    }

    /**
     * Add the given tags to the PCItemTagKeyRegister.
     * @param tags The tags to be added.
     */
    public final PCItemTagKeyRegister addTags(List<TagKey<Item>> tags) {
        CheckUtils.checkAnyIsNullThenThrow(
            tags, "Tags cannot be null when using PCItemTagKeyRegister.Builder"
        );

        this.pcItemTag.addTags(tags);

        return this;
    }

    @Override
    protected PCItemTagKeyRegister self() {
        return this;
    }

    @Override
    public PCItemTagKeyRegister translate(Language lang, String value) {
        throw new UnsupportedOperationException(
            "You shouldn't try to translate 'Block Tag Key': %s"
                .formatted(this.t.id())
        );
    }

}
