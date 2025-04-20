package cn.edu.jlu.renyt1621.register;


import cn.edu.jlu.renyt1621.datagen.lang.PCLanguageProvider;
import cn.edu.jlu.renyt1621.utils.constant.Language;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

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
 *     <li>{@link cn.edu.jlu.renyt1621.register.itemgroup.PCItemGroupRegister}</li>
 *     <li>{@link cn.edu.jlu.renyt1621.register.item.PCItemRegister}</li>
 *     <li>{@link cn.edu.jlu.renyt1621.register.block.PCBlockRegister}</li>
 *     <li>{@link cn.edu.jlu.renyt1621.register.item.PCBlockItemRegister}</li>
 * </ul>
 *
 * @param <T> The type of the thing to be registered, such as: {@code Item}, {@code Block}, {@code ItemGroup} and so on.
 * @param <R> The type of the subclass of {@code PCRegister}, such as: {@code PCItemRegister}, {@code PCBlockRegister}, {@code PCBlockItemRegister} and so on.
 * @author REN YuanTong
 * @Date 2025-04-06
 * @since 1.0.0
 * @see cn.edu.jlu.renyt1621.register.itemgroup.PCItemGroupRegister
 * @see cn.edu.jlu.renyt1621.register.item.PCItemRegister
 * @see cn.edu.jlu.renyt1621.register.block.PCBlockRegister
 * @see cn.edu.jlu.renyt1621.register.item.PCBlockItemRegister
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

    public Identifier id() {
        return this.id;
    }


    /**
     * Register and build the thing to be registered, and return the register object.
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
     *     Use {@link PCLanguageProvider} to generate the language file.
     * </p>
     *
     * <p>
     *     Use the method{@link #registerAndBuild()} before using this method.
     * </p>
     * @param lang the language to translate.
     * @param value the string after translation.
     * @return the register.
     * @see PCLanguageProvider
     */
    public R translate(Language lang, String value){
        checkNotNull("translate(Language, String)");

        PCLanguageProvider.LangMap.instance().put(lang, this.t, value);

        return self();
    }

    protected abstract R self();

    protected void checkNotNull(String method) {
        if (this.t == null)
            throw new IllegalStateException(
                "The object hasn't registered. You should use the method 'registerAndBuild()' before using the method '%s'"
                    .formatted(method)
            );
    }

    /**
     * This method can add the thing to a {@link List}
     *
     * You should implement the method {@link #self()} before using this method.
     * @param list The list, which thing will be added to.
     * @return The register.
     */
    public R addToList(@NotNull List<T> list) {
        checkNotNull("addToList(List)");
        list.add(this.t);
        return self();
    }
}
