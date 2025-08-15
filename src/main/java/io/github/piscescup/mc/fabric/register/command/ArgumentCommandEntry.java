package io.github.piscescup.mc.fabric.register.command;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import io.github.piscescup.mc.fabric.utils.PermissionUtils;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

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
public class ArgumentCommandEntry<T>
    extends CommandEntry<RequiredArgumentBuilder<ServerCommandSource, T>>
{
    private ArgumentType<T> argumentType;

    protected ArgumentCommandEntry(ArgumentCommandEntry.Builder<T> builder) {
        super(builder);
        this.argumentType = builder.argumentType;
    }

    @Override
    public RequiredArgumentBuilder<ServerCommandSource, T> buildEntry() {
        RequiredArgumentBuilder<ServerCommandSource, T> builder =
            CommandManager.argument(this.commandTip, this.argumentType)
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

    @Deprecated
    public static class Builder<T>
        extends AbsBuilder<
            RequiredArgumentBuilder<ServerCommandSource, T>,
        ArgumentCommandEntry<T>, ArgumentCommandEntry.Builder<T>
    > {
        private ArgumentType<T> argumentType;

        private Builder(String commandTip, ArgumentType<T> argumentType) {
            super(commandTip);
            this.argumentType = argumentType;
        }

        public static <T> ArgumentCommandEntry.Builder<T> createFor(String commandTip, ArgumentType<T> argumentType) {
            return new ArgumentCommandEntry.Builder<>(commandTip, argumentType);
        }

        @Override
        public ArgumentCommandEntry<T> build() {
            return new ArgumentCommandEntry<>(this);
        }
    }

}
