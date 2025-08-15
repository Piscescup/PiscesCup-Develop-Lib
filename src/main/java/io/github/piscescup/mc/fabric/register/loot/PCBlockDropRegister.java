package io.github.piscescup.mc.fabric.register.loot;

import io.github.piscescup.mc.fabric.datagen.loot.lists.PCBlockDropperList;
import io.github.piscescup.mc.fabric.register.PCRegister;
import net.minecraft.block.Block;
import net.minecraft.item.ItemConvertible;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.provider.number.LootNumberProvider;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * A loot table util for blocks in Minecraft.
 *
 * <h2>Usages</h2>
 * <pre>{@code
 * PCBlockDropRegister.createFor(this.targetRegistered)
 *             .drops(drop, ConstantLootNumberProvider.create(1.0F))
 *             .getRegister()
 * }</pre>
 * @author REN YuanTong
 * @Date 2025-07-11
 * @since 1.1.2
 */
public final class PCBlockDropRegister
    extends PCRegister<LootTable, PCBlockDropRegister, LootTablePostRegisterConfig<PCBlockDropRegister>>
    implements BlockLootTablePreRegisterConfig, LootTablePostRegisterConfig<PCBlockDropRegister>, Droppable<Block>
{
    private final Block targetBlock;

    private ItemConvertible droppedItem;
    private Block blockWithoutMatchTool;

    private LootTable.Builder lootBuilder;
    private boolean isSilkTouch;
    private boolean isFortune = true;

    private LootNumberProvider targetCount;

    private PCBlockDropRegister(Block targetBlock) {
        this.targetBlock = targetBlock;
    }

    public static BlockLootTablePreRegisterConfig createFor(Block block) {
        return new PCBlockDropRegister(block);
    }

    @Override
    public Block getTarget() {
        return targetBlock;
    }

    @Override
    public ItemConvertible getDroppedItem() {
        return droppedItem;
    }

    @Override
    public LootNumberProvider getTargetCount() {
        return targetCount;
    }

    public Block getBlockWithoutMatchTool() {
        return blockWithoutMatchTool;
    }

    public boolean isSilkTouch() {
        return isSilkTouch;
    }

    public boolean isFortune() {
        return isFortune;
    }

    @Override
    public LootTable.Builder getLootBuilder() {
        return lootBuilder;
    }

    @Override
    public LootTablePostRegisterConfig<PCBlockDropRegister> registerAndBuild() {
        PCBlockDropperList.instance().addDropper(this);
        return this;
    }

    @Override
    public LootTablePostRegisterConfig<PCBlockDropRegister> dropBuilder(@NotNull LootTable.Builder lootBuilder) {
        Objects.requireNonNull(lootBuilder);
        this.lootBuilder = lootBuilder;
        return this;
    }


    @Override
    public LootTablePostRegisterConfig<PCBlockDropRegister> drops(@NotNull ItemConvertible itemDropped, LootNumberProvider itemCount, boolean fortune) {
        Objects.requireNonNull(itemDropped);

        this.droppedItem = itemDropped;
        this.targetCount = itemCount;
        this.isFortune = fortune;

        return this;
    }

    public LootTablePostRegisterConfig<PCBlockDropRegister> dropsWithSilkTouch(@NotNull Block blockWithoutMatchTool) {
        Objects.requireNonNull(blockWithoutMatchTool);

        this.isSilkTouch = true;
        this.blockWithoutMatchTool = blockWithoutMatchTool;

        return this;
    }
    
}
