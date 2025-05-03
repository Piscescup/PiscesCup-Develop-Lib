package cn.edu.jlu.renyt1621.datagen.loot.drop;

import cn.edu.jlu.renyt1621.register.block.PCBlockRegister;
import cn.edu.jlu.renyt1621.utils.CheckUtils;
import net.minecraft.block.Block;
import net.minecraft.item.ItemConvertible;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.provider.number.LootNumberProvider;

/**
 * <h2>Description</h2>
 * <p>
 *     A drop util for block loot table.
 * </p>
 * <h2>Usages</h2>
 * <p>
 *     You can use the method in inner class: {@link Builder#create()} or {@link Builder#createFor(Block)} to create a {@code Builder}
 *     for the PCBlockDrop. <br>
 *     The method {@link Builder#create()} is used in {@link PCBlockRegister#drop(PCBlockDrop drop)}.<br>
 *     The method {@link Builder#createFor(Block block)} is used to create a {@code PCBlockDrop} in a class.
 * </p>
 * Below are the methods you can use:
 * <ul>
 *     <li>{@link Builder#dropBlock(Block block)}</li>Add a block dropped for the block.
 *     <li>{@link Builder#dropItem(ItemConvertible itemDropped)}</li>Add an item dropped for the block.
 *     <li>{@link Builder#dropItemCount(int itemCount)}</li>Set the count of the item dropped.
 *     <li>{@link Builder#lootBuilder(LootTable.Builder lootBuilder)}</li>Set a loot table builder for the block.
 *     <li>{@link Builder#build()}</li>Build the PCBlockDrop.
 * </ul>
 *
 * <h2>Examples</h2>
 * Drop Item:
 * <blockquote><pre>
 * public static final Block PC_BLOCK1 = PCBlockRegister.create(MOD_ID, "block1")
 *     .settings(AbstractBlock.Settings.create()
 *         .burnable()
 *         .mapColor(DyeColor.BROWN)
 *         .hardness(1.0f)
 *         .requiresTool()
 *     )
 *     .registerAndBuild()
 *     .translate(Language.EN_US, "Test Block1")
 *     .translate(Language.ZH_CN, "测试方块1")
 *     .simpleCubeAll()
 *     // Drop 18 diamonds.
 *     .drop(Items.DIAMOND, 18)
 *     .get();
 * </pre></blockquote>
 *
 * Drop Block:
 * <blockquote><pre>
 * public static final Block PC_BLOCK2 = PCBlockRegister.create(MOD_ID, "block2")
 *     .settings(AbstractBlock.Settings.create()
 *         .burnable()
 *         .mapColor(DyeColor.BROWN)
 *         .hardness(1.0f)
 *         .requiresTool()
 *     )
 *     .registerAndBuild()
 *     .translate(Language.EN_US, "Test Block2")
 *     .translate(Language.ZH_CN, "测试方块2")
 *     .simpleCubeAll()
 *     // Drop 2 iron blocks.
 *     .drop(Blocks.IRON_BLOCK, 2)
 *     .get();
 * </pre></blockquote>
 *
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

        /**
         * Create a {@link Builder} of {@link PCBlockDrop} for the given {@link Block}
         * @param block The block to create a {@link PCBlockDrop} for.
         */
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
