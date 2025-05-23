package cn.edu.jlu.renyt1621.register.advancement;

import cn.edu.jlu.renyt1621.register.PCRegister;
import net.minecraft.advancement.Advancement;


/**
 * <h2>Description</h2>
 *
 * <h2>Usages</h2>
 *
 * @author REN YuanTong
 * @Date 2025-05-17
 * @since 1.1.0
 */
@Deprecated(
    since = "1.1.0",
    forRemoval = true
)
public class PCAdvancementRegister
    extends PCRegister<Advancement, PCAdvancementRegister>
{
    private PCAdvancementRegister() {}

    @Override
    public PCAdvancementRegister registerAndBuild() {
        return null;
    }

    @Override
    protected PCAdvancementRegister self() {
        return null;
    }
}
