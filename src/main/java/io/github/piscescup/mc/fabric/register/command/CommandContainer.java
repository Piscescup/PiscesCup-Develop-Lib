package io.github.piscescup.mc.fabric.register.command;

import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.server.command.ServerCommandSource;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static io.github.piscescup.mc.fabric.References.MOD_LOGGER;

/**
 * <h2>Description</h2>
 *
 * <h2>Usages</h2>
 *
 * @author REN YuanTong
 * @Date 2025-07-22
 * @since 1.1.2
 */
@Deprecated
class CommandContainer {
    private final LiteralCommandEntry rootCommandEntry;
    private final Map<
        CommandEntry<?>,
        List<CommandEntry<?>>
    > commandTree = new IdentityHashMap<>();

    private CommandContainer(@NotNull LiteralCommandEntry rootCommandEntry) {
        Objects.requireNonNull(rootCommandEntry);
        this.rootCommandEntry = rootCommandEntry;
        commandTree.put(rootCommandEntry, new ArrayList<>());
    }

    public static CommandContainer createFor(@NotNull LiteralCommandEntry rootCommandEntry) {
        Objects.requireNonNull(rootCommandEntry, "rootCommandEntry");
        return new CommandContainer(rootCommandEntry);
    }

    public CommandContainer addSubCommand(CommandEntry<?> parent, LiteralCommandEntry subcommand) {
        Objects.requireNonNull(parent, "Parent Command");
        Objects.requireNonNull(subcommand, "commandEntry");

        if (!commandTree.containsKey(parent)) {
            MOD_LOGGER.warn(
                "The {} does not exist in the tree.",
                parent.toString()
            );
            return this;
        }
        commandTree.get(parent).add(subcommand);
        commandTree.put(subcommand, new ArrayList<>());
        return this;
    }

    public <T> CommandContainer addSubCommand(CommandEntry<?> parent, ArgumentCommandEntry<T> subcommand) {
        Objects.requireNonNull(parent, "Parent Command");
        Objects.requireNonNull(subcommand, "commandEntry");
        if (!commandTree.containsKey(parent)) {
            MOD_LOGGER.warn(
                "The {} does not exist in the tree.",
                parent.toString()
            );

            return this;
        }
        commandTree.get(parent).add(subcommand);
        commandTree.put(subcommand, new ArrayList<>());
        return this;
    }

    public LiteralArgumentBuilder<ServerCommandSource> build() {
        LiteralArgumentBuilder<ServerCommandSource> rootBuilder = rootCommandEntry.buildEntry();

        Map<CommandEntry<?>, ArgumentBuilder<ServerCommandSource, ?>> builders = new IdentityHashMap<>();
        builders.put(rootCommandEntry, rootBuilder);

        Queue<CommandEntry<?>> queue = new LinkedList<>();
        queue.offer(rootCommandEntry);

        while (!queue.isEmpty()) {
            CommandEntry<?> parentEntry = queue.poll();
            ArgumentBuilder<ServerCommandSource, ?> parentBuilder = builders.get(parentEntry);

            List<CommandEntry<?>> children = commandTree.get(parentEntry);

            if (children == null || parentBuilder == null) {
                continue;
            }

            for (CommandEntry<?> childEntry : children) {
                ArgumentBuilder<ServerCommandSource, ?> childBuilder = childEntry.buildEntry();

                parentBuilder.then(childBuilder);

                builders.put(childEntry, childBuilder);
                queue.offer(childEntry);
            }
        }

        return rootBuilder;
    }

}
