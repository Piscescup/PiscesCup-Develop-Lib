package io.github.piscescup.mc.fabric.register.tag;

import io.github.piscescup.mc.fabric.register.PostRegisterConfig;
import net.minecraft.registry.tag.TagKey;

/**
 * <h2>Description</h2>
 *
 * <h2>Usages</h2>
 *
 * @author REN YuanTong
 * @Date 2025-07-31
 * @since 1.1.2
 */
public interface TagKeyPostRegisterConfig<T>
    extends PostRegisterConfig<TagKeyPostRegisterConfig<T>, TagKey<T>, PCTagKeyRegister<T>>
{
}
