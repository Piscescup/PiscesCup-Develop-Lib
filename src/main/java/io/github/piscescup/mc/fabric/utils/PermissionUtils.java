package io.github.piscescup.mc.fabric.utils;

import net.minecraft.server.command.ServerCommandSource;

import java.util.function.Predicate;

import static io.github.piscescup.mc.fabric.References.MOD_LOGGER;

/**
 * <h2>Description</h2>
 *
 * <h2>Usages</h2>
 *
 * @author REN YuanTong
 * @Date 2025-07-17
 * @since
 */
public final class PermissionUtils {
    private PermissionUtils() {}

    public static final int ALL_PERMISSION = 0;
    public static final int MODERATOR_PERMISSION = 1;
    public static final int GAME_MASTER_PERMISSION = 2;
    public static final int ADMIN_PERMISSION = 3;
    public static final int OP_PERMISSION = 4;

    public static boolean isAllPermission(ServerCommandSource source) {
        return source.hasPermissionLevel(ALL_PERMISSION);
    }

    public static boolean isModeratorPermission(ServerCommandSource source) {
        return source.hasPermissionLevel(MODERATOR_PERMISSION);
    }

    public static boolean isGameMasterPermission(ServerCommandSource source) {
        return source.hasPermissionLevel(GAME_MASTER_PERMISSION);
    }

    public static boolean isAdminPermission(ServerCommandSource source) {
        return source.hasPermissionLevel(ADMIN_PERMISSION);
    }

    public static boolean isOpPermission(ServerCommandSource source) {
        return source.hasPermissionLevel(OP_PERMISSION);
    }

    public static boolean isPlayerPermission(ServerCommandSource source) {
        return source.hasPermissionLevel(ALL_PERMISSION);
    }

    public static Predicate<ServerCommandSource> permission(int level) {
        return scs -> scs.hasPermissionLevel(level);
    }

    public static Predicate<ServerCommandSource> permission0() {
        return permission(ALL_PERMISSION);
    }

    public static Predicate<ServerCommandSource> permission1() {
        return permission(MODERATOR_PERMISSION);
    }

    public static Predicate<ServerCommandSource> permission2() {
        return permission(GAME_MASTER_PERMISSION);
    }

    public static Predicate<ServerCommandSource> permission3() {
        return permission(ADMIN_PERMISSION);
    }

    public static Predicate<ServerCommandSource> permission4() {
        return permission(OP_PERMISSION);
    }

    public static int requireValidPermissionLevel(int level) {
        return requireValidPermissionLevel(level, ALL_PERMISSION, OP_PERMISSION);
    }

    public static int requireValidPermissionLevel(int level, int fromInclusive, int toInclusive) {
        if (!isValidPermissionLevel(level, fromInclusive, toInclusive)) {
            MOD_LOGGER.warn(
                "Invalid permission level: {}. The level should be in [{}, {}]. And the level will be set as {}",
                level, fromInclusive, toInclusive, fromInclusive
            );
            return ALL_PERMISSION;
        }
        return level;
    }

    public static boolean isValidPermissionLevel(int level, int fromInclusive, int toInclusive) {
        return level >= fromInclusive && level <= toInclusive;
    }

    public static boolean isValidPermissionLevel(int level) {
        return level >= ALL_PERMISSION && level <= OP_PERMISSION;
    }

}
