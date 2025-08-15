package io.github.piscescup.mc.fabric.register;


import io.github.piscescup.mc.fabric.register.block.PCBlockRegister;
import io.github.piscescup.mc.fabric.register.item.PCBlockItemRegister;
import io.github.piscescup.mc.fabric.register.item.PCItemRegister;
import io.github.piscescup.mc.fabric.register.itemgroup.PCItemGroupRegister;
import io.github.piscescup.mc.fabric.register.loot.PCBlockDropRegister;
import io.github.piscescup.mc.fabric.register.loot.PCEntityDropRegister;
import io.github.piscescup.mc.fabric.register.sound.PCSoundEventRegRefRegister;
import io.github.piscescup.mc.fabric.register.sound.PCSoundEventRegister;
import io.github.piscescup.mc.fabric.register.tag.PCTagKeyRegister;
import io.github.piscescup.mc.fabric.register.villager.PCVillagerRegister;
import io.github.piscescup.mc.fabric.register.poi.PCPointOfInterestTypeRegister;
import io.github.piscescup.mc.fabric.register.villager.profession.PCVillagerProfessionRegister;

import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

/**
 * <h1>Description</h1>
 *
 * <p>
 *     A register util for register things in Minecraft, such as {@code Item}, {@code Block}, {@code ItemGroup} and so on.
 * </p>
 *
 * <h1>Usages</h1>
 *
 * <p>
 *     This class is an abstract class. You should use the subclass to register things.
 * </p>
 * Below are the subclasses:
 * <ul>
 *     <li>{@link PCItemGroupRegister}</li>
 *     <li>{@link PCItemRegister}</li>
 *     <li>{@link PCBlockRegister}</li>
 *     <li>{@link PCBlockItemRegister}</li>
 *     <li>{@link PCBlockDropRegister}</li>
 *     <li>{@link PCEntityDropRegister}</li>
 *     <li>{@link PCSoundEventRegister}</li>
 *     <li>{@link PCSoundEventRegRefRegister}</li>
 *     <li>{@link PCTagKeyRegister}</li>
 *     <li>{@link PCPointOfInterestTypeRegister}</li>
 *     <li>{@link PCVillagerProfessionRegister}</li>
 *     <li>{@link PCVillagerRegister}</li>
 * </ul>
 *
 * @param <T> The type of the thing to be registered,
 *           such as: {@code Item}, {@code Block}, {@code ItemGroup} and so on.
 * @param <R> The type of the subclass of {@code PCRegister},
 *           such as: {@code PCItemRegister}, {@code PCBlockRegister}, {@code PCBlockItemRegister} and so on.
 * @param <C> The type of the subclass of {@code PostRegisterConfig},
 *           such as: {@code ItemPostRegisterConfig}, {@code BlockPostRegisterConfig}, and so on.
 *
 * @author REN YuanTong
 * @Date 2025-04-06
 * @since 1.0.0
 * @see PreRegisterConfig
 * @see PostRegisterConfig
 * @see PCItemGroupRegister
 * @see PCItemRegister
 * @see PCBlockRegister
 * @see PCBlockItemRegister
 * @see PCBlockDropRegister
 * @see PCEntityDropRegister
 * @see PCSoundEventRegister
 * @see PCSoundEventRegRefRegister
 * @see PCTagKeyRegister
 * @see PCPointOfInterestTypeRegister
 * @see PCVillagerProfessionRegister
 * @see PCVillagerRegister
 */
public abstract
class PCRegister<T, R extends PCRegister<T, R, C>, C extends PostRegisterConfig<C, T, R>>
    implements PostRegisterConfig<C, T, R>, PreRegisterConfig<C>
{
    protected T targetRegistered;
    protected Identifier id;
    protected RegistryKey<T> key;

    protected PCRegister() {}

    protected PCRegister(Identifier id) {
        this.id = id;
    }

    protected PCRegister(String fullPath) {
        this.id = Identifier.ofVanilla(fullPath);
    }

    protected PCRegister(String namespace, String path) {
        this.id = Identifier.of(namespace, path);
    }

    @Override
    public Identifier id() {
        return this.id;
    }

    @Override
    public RegistryKey<T> key() {
        return this.key;
    }

    @Override
    public T get() {
        return this.targetRegistered;
    }

    @Override
    public abstract C registerAndBuild();
}
