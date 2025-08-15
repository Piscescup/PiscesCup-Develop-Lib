package io.github.piscescup.mc.fabric.register.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.RedirectModifier;
import com.mojang.brigadier.SingleRedirectModifier;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.tree.CommandNode;
import io.github.piscescup.mc.fabric.utils.PermissionUtils;
import net.minecraft.server.command.ServerCommandSource;

import java.util.Collections;
import java.util.Objects;

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
public abstract class CommandEntry<A extends ArgumentBuilder<ServerCommandSource, A>>
{
    protected String commandTip;
    protected int permissionLevel;
    protected Command<ServerCommandSource> executor;

    protected CommandNode<ServerCommandSource> redirectTarget;
    protected RedirectModifier<ServerCommandSource> redirectModifier;
    protected boolean forks;


    protected CommandEntry(AbsBuilder<A, ?, ?> builder) {
        this.commandTip = builder.commandTip;
        this.permissionLevel = builder.permissionLevel;
        this.executor = builder.executor;
    }

    public String getCommandTip() {
        return commandTip;
    }

    public int getPermissionLevel() {
        return permissionLevel;
    }

    public Command<ServerCommandSource> getExecutor() {
        return executor;
    }


    public abstract A buildEntry();

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CommandEntry<?> that = (CommandEntry<?>) o;
        return Objects.equals(commandTip, that.commandTip);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(commandTip);
    }

    @Override
    public String toString() {
        return "CommandEntry: " + commandTip;
    }

    public abstract static
    class AbsBuilder<
        A extends ArgumentBuilder<ServerCommandSource, A>,
        T extends CommandEntry<A>,
        B extends CommandEntry.AbsBuilder<A, T, B>
    > {
        protected String commandTip;
        protected int permissionLevel = 0;
        protected Command<ServerCommandSource> executor;

        protected CommandNode<ServerCommandSource> redirectTarget;
        protected RedirectModifier<ServerCommandSource> redirectModifier;
        protected boolean forks;

        protected AbsBuilder(String commandTip) {
            this.commandTip = commandTip;
        }

        @SuppressWarnings("unchecked")
        public B permissionLevel(int permissionLevel) {
            this.permissionLevel =
                PermissionUtils.requireValidPermissionLevel(permissionLevel);
            return (B) this;
        }

        @SuppressWarnings("unchecked")
        public B executor(Command<ServerCommandSource> executor) {
            this.executor = executor;
            return (B) this;
        }


        public B redirect(final CommandNode<ServerCommandSource> target) {
            return forward(target, null, false);
        }

        public B redirect(
            final CommandNode<ServerCommandSource> target,
            final SingleRedirectModifier<ServerCommandSource> modifier
        ) {
            return forward(
                target, modifier == null ? null :
                    o -> Collections.singleton(modifier.apply(o)),
                false
            );
        }

        public B fork(
            final CommandNode<ServerCommandSource> target,
            final RedirectModifier<ServerCommandSource> modifier
        ) {
            return forward(target, modifier, true);
        }

        @SuppressWarnings("unchecked")
        public B forward(
            final CommandNode<ServerCommandSource> target,
            final RedirectModifier<ServerCommandSource> modifier,
            final boolean fork
        ) {
            this.redirectTarget = target;
            this.redirectModifier = modifier;
            this.forks = fork;
            return (B) this;
        }

        public abstract T build();

    }
}
