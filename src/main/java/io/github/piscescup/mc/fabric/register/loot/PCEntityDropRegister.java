package io.github.piscescup.mc.fabric.register.loot;

import io.github.piscescup.mc.fabric.datagen.loot.lists.PCEntityDropperList;
import io.github.piscescup.mc.fabric.register.PCRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemConvertible;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.provider.number.LootNumberProvider;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * <h2>Description</h2>
 *
 * <h2>Usages</h2>
 *
 * @author REN YuanTong
 * @Date 2025-07-12
 * @since 1.1.0
 */
public final class PCEntityDropRegister
    extends PCRegister<LootTable, PCEntityDropRegister, LootTablePostRegisterConfig<PCEntityDropRegister>>
    implements Droppable<EntityType<?>>, LootTablePreRegisterConfig<PCEntityDropRegister>, LootTablePostRegisterConfig<PCEntityDropRegister>
{
    private final EntityType<?> target;

    private ItemConvertible droppedItem;
    private LootTable.Builder builder;
    private boolean isLooting = true;
    private LootNumberProvider count;


    private PCEntityDropRegister(EntityType<?> target) {
        this.target = target;
    }

    public static <E extends Entity> LootTablePreRegisterConfig<PCEntityDropRegister> createFor(EntityType<E> target) {
        return new PCEntityDropRegister(target);
    }

    @Override
    public PCEntityDropRegister registerAndBuild() {

        PCEntityDropperList.instance().addDropper(this);

        return this;
    }


    @Override
    public LootTablePostRegisterConfig<PCEntityDropRegister> drops(@NotNull ItemConvertible drop, LootNumberProvider count, boolean looting) {
        Objects.requireNonNull(drop);
        this.droppedItem = drop;
        this.count = count;
        this.isLooting = looting;
        return this;
    }

    @Override
    public LootTablePostRegisterConfig<PCEntityDropRegister> dropBuilder(@NotNull LootTable.Builder lootBuilder) {
        Objects.requireNonNull(lootBuilder);
        this.builder = lootBuilder;
        return this;
    }

    @Override
    public EntityType<?> getTarget() {
        return target;
    }

    @Override
    public ItemConvertible getDroppedItem() {
        return droppedItem;
    }

    @Override
    public LootNumberProvider getTargetCount() {
        return count;
    }

    @Override
    public LootTable.Builder getLootBuilder() {
        return builder;
    }
}
