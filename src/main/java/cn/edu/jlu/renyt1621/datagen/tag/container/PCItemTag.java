package cn.edu.jlu.renyt1621.datagen.tag.container;

import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.tag.TagKey;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <h2>Description</h2>
 *
 * <h2>Usages</h2>
 *
 * @author REN YuanTong
 * @Date 2025-04-22
 * @since 1.0.0
 */
public class PCItemTag {
    private final TagKey<Item> tag;

    private final List<Item> items = new ArrayList<>();
    private final List<TagKey<Item>> tags = new ArrayList<>();

    public PCItemTag(TagKey<Item> tag) {
        this.tag = tag;
    }

    public TagKey<Item> getTag() {
        return tag;
    }

    public List<Item> getItems() {
        return items;
    }

    public List<TagKey<Item>> getTags() {
        return tags;
    }

    public boolean addItem(Item item) {
        return items.add(item);
    }

    public boolean addTag(TagKey<Item> tag) {
        return tags.add(tag);
    }

    public boolean addItems(Item... items) {
        Collections.addAll(this.items, items);
        return true;
    }

    public final boolean addTags(List<TagKey<Item>> tags) {
        this.tags.addAll(tags);
        return true;
    }

    public boolean isEmpty() {
        return items.isEmpty() && tags.isEmpty();
    }

}
