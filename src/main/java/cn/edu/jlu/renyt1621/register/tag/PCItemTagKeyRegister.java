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
 *
 * <h2>Usages</h2>
 *
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

    public static PCItemTagKeyRegister create(String path) {
        return new PCItemTagKeyRegister(Identifier.of(path));
    }

    public static PCItemTagKeyRegister create(Identifier identifier) {
        return new PCItemTagKeyRegister(identifier);
    }

    public static PCItemTagKeyRegister create(String namespace, String path) {
        return new PCItemTagKeyRegister(Identifier.of(namespace, path));
    }

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

    public PCItemTagKeyRegister addItem(Item item) {
        CheckUtils.checkIsNullThenThrow(
            item, "Item cannot be null when using PCItemTagKeyRegister.Builder"
        );
        this.pcItemTag.addItem(item);
        return this;
    }

    @SuppressWarnings("unchecked")
    public PCItemTagKeyRegister addItems(List<Item> items) {
        CheckUtils.checkAnyIsNullThenThrow(
            Collections.singletonList(items), "Items cannot be null when using PCItemTagKeyRegister.Builder"
        );

        this.pcItemTag.addItems(items);
        return this;
    }

    public PCItemTagKeyRegister addTag(TagKey<Item> tag) {
        CheckUtils.checkIsNullThenThrow(
            tag, "Tag cannot be null when using PCItemTagKeyRegister.Builder"
        );

        this.pcItemTag.addTag(tag);

        return this;
    }

    public final PCItemTagKeyRegister add(List<TagKey<Item>> tags) {
        CheckUtils.checkAnyIsNullThenThrow(
            List.of(tags), "Tags cannot be null when using PCItemTagKeyRegister.Builder"
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
