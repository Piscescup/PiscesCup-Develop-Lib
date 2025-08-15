package io.github.piscescup.mc.fabric.register.command;

/**
 * <h2>Description</h2>
 *
 * <h2>Usages</h2>
 *
 * @author REN YuanTong
 * @Date 2025-07-22
 * @since
 */
// @Deprecated
// public class PCCommandRegister
//     extends PCRegister<CommandNode<ServerCommandSource>, PCCommandRegister>
// {
//     private CommandContainer commands;
//
//     private PCCommandRegister(@NotNull CommandContainer commands) {
//         Objects.requireNonNull(commands);
//         this.commands = commands;
//     }
//
//     public static PCCommandRegister createFor(
//         @NotNull LiteralCommandEntry rootCommandEntry
//     ) {
//         Objects.requireNonNull(rootCommandEntry);
//         return new PCCommandRegister(
//             CommandContainer.createFor(rootCommandEntry)
//         );
//     }
//
//     public PCCommandRegister addSubCommand(@NotNull CommandEntry<?> parent, @NotNull LiteralCommandEntry subcommand) {
//         Objects.requireNonNull(parent);
//         Objects.requireNonNull(subcommand);
//         commands.addSubCommand(parent, subcommand);
//         return this;
//     }
//
//     public <T> PCCommandRegister addSubCommand(@NotNull CommandEntry<?> parent, @NotNull ArgumentCommandEntry<T> subcommand) {
//         Objects.requireNonNull(parent);
//         Objects.requireNonNull(subcommand);
//         commands.addSubCommand(parent, subcommand);
//         return this;
//     }
//
//     @Override
//     public PCCommandRegister registerAndBuild() {
//         CommandRegistrationCallback.EVENT.register(
//             this::register
//         );
//         return this;
//     }
//
//     private void register(
//         CommandDispatcher<ServerCommandSource> dispatcher,
//         CommandRegistryAccess registryAccess,
//         CommandManager.RegistrationEnvironment environment
//     ) {
//         dispatcher.register(
//             commands.build()
//         );
//     }
//
//     @Override
//     protected PCCommandRegister self() {
//         return this;
//     }
// }
