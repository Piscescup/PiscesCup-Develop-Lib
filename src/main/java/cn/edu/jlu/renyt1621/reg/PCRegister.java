package cn.edu.jlu.renyt1621.reg;


import cn.edu.jlu.renyt1621.utils.constant.Language;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

import java.util.List;

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
 *     <li>{@link cn.edu.jlu.renyt1621.reg.itemgroup.PCItemGroupRegister}</li>
 *     <li>{@link cn.edu.jlu.renyt1621.reg.item.PCItemRegister}</li>
 *     <li>{@link cn.edu.jlu.renyt1621.reg.block.PCBlockRegister}</li>
 *     <li>{@link cn.edu.jlu.renyt1621.reg.item.PCBlockItemRegister}</li>
 * </ul>
 *
 * @param <T> The type of the thing to be registered, such as: {@code Item}, {@code Block}, {@code ItemGroup} and so on.
 * @param <R> The type of the subclass of {@code PCRegister}, such as: {@code PCItemRegister}, {@code PCBlockRegister}, {@code PCBlockItemRegister} and so on.
 * @author REN YuanTong
 * @Date 2025-04-06
 * @since 1.0.0
 * @see cn.edu.jlu.renyt1621.reg.itemgroup.PCItemGroupRegister
 * @see cn.edu.jlu.renyt1621.reg.item.PCItemRegister
 * @see cn.edu.jlu.renyt1621.reg.block.PCBlockRegister
 * @see cn.edu.jlu.renyt1621.reg.item.PCBlockItemRegister
 */
public abstract class PCRegister<T, R extends PCRegister<T, R>> {
    protected T t;
    protected Identifier id;
    protected RegistryKey<T> key;

    protected PCRegister() {}

    protected PCRegister(Identifier id) {
        this.id = id;
    }

    protected PCRegister(String path) {
        this.id = Identifier.ofVanilla(path);
    }

    protected PCRegister(String namespace, String path) {
        this.id = Identifier.of(namespace, path);
    }

    /**
     * Register and build the thing to be registered, but return the register object.
     * @return The register.
     */
    public abstract R registerAndBuild();

    /**
     * Get the thing, which has been registered.
     * @return The thing has been registered.
     */
    public T get() {
        checkNotNull("get()");
        return this.t;
    }

    /**
     * <p>
     * Translate the thing to the language.
     * </p>
     *
     * <p>
     *     You should use {@link cn.edu.jlu.renyt1621.datagen.lang.PCDLLanguageProvider} to generate the language file.
     * </p>
     *
     * <p>
     *     You should use the method{@link #registerAndBuild()} before you use this method.
     *     Because this method is depended on the method {@link #registerAndBuild()}.
     * </p>
     * @param lang the language to translate.
     * @param value the string after translation.
     * @return the register.
     * @see cn.edu.jlu.renyt1621.datagen.lang.PCDLLanguageProvider
     */
    public abstract R translate(Language lang, String value);

    protected void checkNotNull(String method) {
        if (this.t == null)
            throw new IllegalStateException(
                "The object hasn't registered. You should use the method 'registerAndBuild()' before using the method '%s'"
                    .formatted(method)
            );
    }

    /**
     * This method can add the thing to a {@link List}
     * @param list The list, which thing will be added to.
     * @return The register.
     */
    @Deprecated(
        forRemoval = true
    )
    public R addToList(List<T> list) {
        checkNotNull("addToList(List)");
        list.add(this.t);
        return (R) this;
    }
}
