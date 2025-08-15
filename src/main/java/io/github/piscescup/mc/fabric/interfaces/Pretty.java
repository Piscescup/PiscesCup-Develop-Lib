package io.github.piscescup.mc.fabric.interfaces;

/**
 *<p>
 *     This interface is used to make the configs pretty name of the configs.
 *</p>
 * @author REN YuanTong
 * @Date 2025-05-25
 * @since 1.1.0
 */
public interface Pretty {
    /**
     * Get the pretty name of the configs.
     * @return The pretty name of the configs.
     */
    String getPrettyName();

    /**
     * Set the pretty name of the configs.
     * @param prettyName The pretty name of the configs to be set.
     */
    void setPrettyName(String prettyName);
}
