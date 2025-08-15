package io.github.piscescup.mc.fabric.interfaces;

import javax.annotation.Nullable;

/**
 * <p>
 *     This interface is used to add a comment to a config which can be displayed when hovering over the config name in the config GUI.
 * </p>
 *
 * @author REN YuanTong
 * @Date 2025-05-25
 * @since 1.1.0
 */
public interface Commentable {

    /**
     * Returns the comment displayed when hovering over the config name in the config GUI.
     * Newlines can be added with "\n". Can be null if there is no comment for this config.
     * @return The comment, or {@code null} if no comment has been set
     */
    @Nullable
    String getComment();

    /**
     * Sets the comment displayed when hovering over the config name in the config GUI.
     * @param comment The comment, or {@code null} to remove the comment
     */
    void setComment(@Nullable String comment);
}
