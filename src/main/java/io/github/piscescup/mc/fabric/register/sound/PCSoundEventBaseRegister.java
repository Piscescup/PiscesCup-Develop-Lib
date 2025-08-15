package io.github.piscescup.mc.fabric.register.sound;

import io.github.piscescup.mc.fabric.register.PCRegister;
import net.minecraft.util.Identifier;

public sealed abstract class PCSoundEventBaseRegister<S>
    extends PCRegister<S, PCSoundEventBaseRegister<S>, SoundEventPostRegisterConfig<S>>
    implements SoundEventPreRegisterConfig<S>, SoundEventPostRegisterConfig<S>
    permits PCSoundEventRegRefRegister, PCSoundEventRegister
{
    protected PCSoundEventBaseRegister(Identifier id) {
        super(id);
    }

}
