package cn.edu.jlu.renyt1621.datagen.advancements;


import cn.edu.jlu.renyt1621.utils.CheckUtils;
import net.minecraft.data.DataOutput;
import net.minecraft.data.advancement.AdvancementProvider;
import net.minecraft.data.advancement.AdvancementTabGenerator;
import net.minecraft.registry.RegistryWrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * <h2>Description</h2>
 *
 * <h2>Usages</h2>
 *
 * @author REN YuanTong
 * @Date 2025-05-21
 * @since 1.1.0
 */
public class PCAdvancementContainer {
    private static final List<AdvancementTabGenerator> ADVANCEMENT_TABS =
        new ArrayList<>();

    private PCAdvancementContainer() {}

    private static volatile PCAdvancementContainer INSTANCE;

    public static PCAdvancementContainer instance() {
        if (INSTANCE == null) {
            synchronized (PCAdvancementContainer.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PCAdvancementContainer();
                }
            }
        }
        return INSTANCE;
    }

    public <T extends PCAdvancementTabGenerator> PCAdvancementContainer addAdvancementTab(T tab) {
        ADVANCEMENT_TABS.add(tab);
        return this;
    }

    @SafeVarargs
    public final <T extends PCAdvancementTabGenerator> PCAdvancementContainer addAdvancementTabs(T... tabs) {
        Collections.addAll(ADVANCEMENT_TABS, tabs);
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
