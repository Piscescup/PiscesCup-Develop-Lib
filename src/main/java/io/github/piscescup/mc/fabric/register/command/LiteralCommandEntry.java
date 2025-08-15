package io.github.piscescup.mc.fabric.register.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.github.piscescup.mc.fabric.utils.PermissionUtils;
import net.minecraft.server.command.ServerCommandSource;

import static net.minecraft.server.command.CommandManager.literal;

/**
 * <h2>Description</h2>
 *
 * <h2>Usages</h2>
 *
 * @author REN YuanTong
 * @Date 2025-07-17
 * @since 1.1.2
 */
@Deprecated
public class LiteralCommandEntry
    extends CommandEntry<LiteralArgumentBuilder<ServerCommandSource>>
{
    protected LiteralCommandEntry(LiteralCommandEntry.Builder builder) {
        super(builder);
    }

    public LiteralArgumentBuilder<ServerCommandSource> buildEntry() {
        LiteralArgumentBuilder<ServerCommandSource> builder = literal(this.commandTip)
            .requires(PermissionUtils.permission(this.permissionLevel));

        if (this.executor != null)
            builder.executes(executor);

        if (this.redirectTarget != null) {
            builder.forward(
                this.redirectTarget,
                this.redirectModifier,
                this.forks
            );
        }

        return builder;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public static class Builder
        extends AbsBuilder<LiteralArgumentBuilder<ServerCommandSource>, LiteralCommandEntry, LiteralCommandEntry.Builder>
    {
        private Builder(String commandTip) {
            super(commandTip);
        }

        public static LiteralCommandEntry.Builder createFor(String commandTip) {
            return new LiteralCommandEntry.Builder(commandTip);
        }

        @Override
        public LiteralCommandEntry build() {
            return new LiteralCommandEntry(this);
        }
    }
}
