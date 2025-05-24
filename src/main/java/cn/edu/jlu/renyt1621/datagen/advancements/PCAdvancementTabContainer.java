package cn.edu.jlu.renyt1621.datagen.advancements;


import cn.edu.jlu.renyt1621.datagen.factories.PCAdvancementProviderFactory;
import cn.edu.jlu.renyt1621.utils.CheckUtils;
import net.minecraft.data.DataOutput;
import net.minecraft.data.advancement.AdvancementProvider;
import net.minecraft.data.advancement.AdvancementTabGenerator;
import net.minecraft.registry.RegistryWrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * <h2>Description</h2>
 * <p>
 *     This class is used to collect the subclasses of {@link PCAdvancementTabGenerator},<br>
 *     and used to create a {@link net.minecraft.data.DataGenerator}
 * </p>
 * <h2>Usages</h2>
 * <h4>Steps</h4>
 * <ol>
 *     <li>Get the instance of this class by the method: {@link #instance()}</li>
 *     <li>Add {@code Tabs} by the instance methods: {@link #addAdvancementTab} or {@link #addAdvancementTabs}</li>
 *     <li>Use the instance in the method: {@link PCAdvancementProviderFactory#createFor(PCAdvancementTabContainer)}
 *     to generate the list for {@code DataProvider.Factory}</li>
 * </ol>
 * <h4>Example</h4>
 * <blockquote><pre>
 * PCAdvancementProviderFactory.createFor(
 *     PCAdvancementTabContainer.instance()
 *         .addAdvancementTab(ModTabAdvancement::new)
 * )
 *         .forEach(pack::addProvider);
 * </pre></blockquote>
 *
 * @author REN YuanTong
 * @Date 2025-05-21
 * @since 1.1.0
 */
public class PCAdvancementTabContainer {
    private static final List<AdvancementTabGenerator> ADVANCEMENT_TABS =
        new ArrayList<>();

    private PCAdvancementTabContainer() {}

    private static volatile PCAdvancementTabContainer INSTANCE;

    public static PCAdvancementTabContainer instance() {
        if (INSTANCE == null) {
            synchronized (PCAdvancementTabContainer.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PCAdvancementTabContainer();
                }
            }
        }
        return INSTANCE;
    }

    public <T extends PCAdvancementTabGenerator> PCAdvancementTabContainer addAdvancementTab(Supplier<T> tabSupplier) {
        T tab = tabSupplier.get();

        ADVANCEMENT_TABS.add(tab);
        return this;
    }

    @SafeVarargs
    public final <T extends PCAdvancementTabGenerator> PCAdvancementTabContainer addAdvancementTabs(Supplier<T>... tabs) {
        Stream.of(tabs)
            .map(Supplier::get)
            .forEach(ADVANCEMENT_TABS::add);
        return this;
    }

    public AdvancementProvider createAdvancementProvider(
        DataOutput output,
        CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture
    ) {
        CheckUtils.checkIsNullThenThrow(
            ADVANCEMENT_TABS,
            "The ADVANCEMENT_TABS list is null. You should add some tabs by using the method: `addAdvancementTab` or `addAdvancementTabs` "
        );

        return new AdvancementProvider(
            output,
            registriesFuture,
            ADVANCEMENT_TABS
        );
    }

}
