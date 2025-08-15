package io.github.piscescup.mc.fabric.mixin;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.VertexConsumerProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * <h2>Description</h2>
 *
 * <h2>Usages</h2>
 *
 * @author REN YuanTong
 * @Date 2025-06-16
 * @since 1.1.0
 */
@Mixin(DrawContext.class)
public interface DrawContextVertexConsumersAccessor {
    @Accessor("vertexConsumers")
    VertexConsumerProvider.Immediate pcGetVertexConsumers();
}
