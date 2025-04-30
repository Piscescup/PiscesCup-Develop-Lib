package cn.edu.jlu.renyt1621.tests.items.custom;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

/**
 * Test Custom Item.
 *
 * @author REN YuanTong
 * @Date 2025-04-27
 * @since 1.0.0
 */
public class PCCustomItem
    extends Item
{
    public PCCustomItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (hand == Hand.OFF_HAND)
            return ActionResult.PASS;

        if (world.isClient)
            return ActionResult.PASS;

        if ( user.hasStatusEffect(StatusEffects.NAUSEA) )
            return ActionResult.PASS;

        user.addStatusEffect(
            new StatusEffectInstance(
                StatusEffects.NAUSEA,
                100,
                0,
                false,
                true
            )
        );
        ItemStack mainHandStack = user.getMainHandStack();
        mainHandStack.decrement(1);

        return ActionResult.SUCCESS;
    }


}
