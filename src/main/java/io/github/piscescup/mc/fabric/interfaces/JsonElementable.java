package io.github.piscescup.mc.fabric.interfaces;

import com.google.gson.JsonElement;

/**
 * <h2>Description</h2>
 * <p>
 *     This interface is used to convert an object to a {@link JsonElement} and vice versa.
 * </p>
 * <h2>Usages</h2>
 * <p>
 *     The implementation of this class should implement the {@link #toJsonElement()} and {@link #fromJsonElement(JsonElement)} methods.<br>
 *     The method {@link #toJsonElement()} should return a {@link JsonElement} which contains the value of the object.<br>
 *     The method {@link #fromJsonElement(JsonElement)} should set the value of the object from a {@link JsonElement}.
 * </p>
 *
 * @author REN YuanTong
 * @Date 2025-05-25
 * @since 1.1.0
 */
public interface JsonElementable  {
    /**
     * Convert the object to a {@link  JsonElement}
     * @return The {@link JsonElement}.
     */
    JsonElement toJsonElement();


    /**
     * Set the value of an object from a {@link JsonElement}
     * @param jsonElement The JSON element which contains the value to be set.
     */
    void fromJsonElement(JsonElement jsonElement);
}
