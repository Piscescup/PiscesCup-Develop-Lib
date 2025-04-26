package cn.edu.jlu.renyt1621.datagen.loot.drop;

import cn.edu.jlu.renyt1621.register.block.PCBlockRegister;
import cn.edu.jlu.renyt1621.utils.CheckUtils;
import net.minecraft.block.Block;
import net.minecraft.item.ItemConvertible;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.provider.number.LootNumberProvider;

/**
 * <h2>Description</h2>
 *
 * <h2>Usages</h2>
 *
 * @author REN YuanTong
 * @Date 2025-04-24
 * @since 1.0.0
 */
public class PCBlockDrop {
    private final Block block;

    private final Block drop;
    private final ItemConvertible itemDropped;
    private final int itemCount;

    private final LootTable.Builder lootBuilder;

    private PCBlockDrop(Builder builder) {
        this.block = builder.block;
        this.drop = builder.drop;
        this.itemDropped = builder.itemDropped;
        this.itemCount = builder.itemCount;
        this.lootBuilder = builder.lootBuilder;
    }

    @Deprecated
    public Block getBlock() {
        return block;
    }

    public Block getDrop() {
        return drop;
    }

    public ItemConvertible getItemDropped() {
        return itemDropped;
    }

    public LootTable.Builder getLootBuilder() {
        return lootBuilder;
    }

    public int getItemCount() {
        return itemCount;
    }

    public static class Builder {
        private Block block;

        private Block drop;
        private ItemConvertible itemDropped;
        private int itemCount = 1;
        private LootNumberProvider countRange;


        private LootTable.Builder lootBuilder;

        private Builder() {}

        /**
         * This {@code create} method is used in {@link PCBlockRegister#drop(PCBlockDrop)} to create a {@link PCBlockDrop}
         * @return A {@link Builder} of {@link PCBlockDrop}
         */
        public static Builder create() {
            return new Builder();
        }

        public static Builder createFor(Block block) {
            CheckUtils.checkIsNullThenThrow(
                block, "Block cannot be null when using PCBlockDrop.Builder"
            );
            Builder builder = new Builder();
            builder.block = block;
            return builder;
        }

        public Builder dropBlock(Block drop) {
            CheckUtils.checkIsNullThenThrow(
                drop, "Drop cannot be null when using PCBlockDrop.Builder"
            );
            this.itemDropped = null;
            this.drop = drop;
            return this;
        }

        public Builder dropItem(ItemConvertible itemDropped) {
            CheckUtils.checkIsNullThenThrow(
                itemDropped, "ItemDropped cannot be null when using PCBlockDrop.Builder"
            );
            this.drop = null;
            this.itemDropped = itemDropped;
            return this;
        }

        public Builder dropItemCount(int itemCount) {
            this.itemCount = itemCount;
            return this;
        }

        // public Builder dropItemRange(int from, int to) {
        //     this.countRange =
        // }

        public Builder lootBuilder(LootTable.Builder lootBuilder) {
            CheckUtils.checkIsNullThenThrow(
                lootBuilder, "LootBuilder cannot be null when using PCBlockDrop.Builder"
            );
            this.lootBuilder = lootBuilder;
            return this;
        }

        public PCBlockDrop build() {
            if ( drop == null && itemDropped == null && lootBuilder == null )
                throw new IllegalStateException(
                    "The (drop block, drop item) / (the loot table builder) must be set."
                );

            return new PCBlockDrop(this);
        }
    }

}
