package io.github.piscescup.mc.fabric.datagen.tag;


import io.github.piscescup.mc.fabric.register.tag.container.PCTagKeyContainer;
import io.github.piscescup.mc.fabric.register.tag.lists.PCTagKeyContainerList;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/**
 * <h2>Description</h2>
 *
 * <h2>Usage</h2>
 *
 * @author REN YuanTong
 * @Date 2025-07-02
 * @since 1.1.0
 */
public class PCTagProvider<T>
    extends FabricTagProvider<T>
{
    protected final List<PCTagKeyContainer<T>> tagList;

    private final Consumer<PCTagKeyContainer<T>> defaultAction =
        c -> {
            TagKey<T> targetTag = c.getTargetTag();
            List<T> containedThings = c.getContainedThings();
            List<TagKey<T>> containedTags = c.getContainedTags();

            FabricTagProvider<T>.FabricTagBuilder tagBuilder = this.getOrCreateTagBuilder(targetTag);

            containedThings.forEach(
                tagBuilder::add
            );

            containedTags.forEach(
                tagBuilder::addOptionalTag
            );
        };

    private Consumer<PCTagKeyContainer<T>> action = defaultAction;


    protected PCTagProvider(Builder<T> builder) {
        super(builder.output, builder.registryRef, builder.registriesFuture);
        this.tagList = builder.containerList.getContainerList();
    }

    /**
     * <p>
     *     Generate the tags.
     * </p>
     * <p>
     *     This method depends on the method {@link #startGenerate()}.
     * </p>
     * <p>
     *     To be honest, I suggest that you override the method {@link #startGenerate()} instead of this.
     * </p>
     */
    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        this.startGenerate();
    }

    /**
     * <p>
     *     This method defines the operations when generating the tags.
     * </p>
     */
    protected void startGenerate() {
        this.tagList.forEach(
            action
        );
    }

    public static
    class Builder<T> {
        private FabricDataOutput output;
        private RegistryKey<? extends Registry<T>> registryRef;
        private CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture;
        private PCTagKeyContainerList<T> containerList;

        Builder(RegistryKey<? extends Registry<T>> registryRef) {
            this.registryRef = registryRef;
        }

        public static <T> Builder<T> create(RegistryKey<? extends Registry<T>> registryRef) {
            return new Builder<>(registryRef);
        }

        public Builder<T> output(FabricDataOutput output) {
            this.output = output;
            return this;
        }


        public Builder<T> registriesFuture(CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            this.registriesFuture = registriesFuture;
            return this;
        }

        public Builder<T> containerList(PCTagKeyContainerList<T> containerList) {
            this.containerList = containerList;
            return this;
        }

        public PCTagProvider<T> build() {
            return new PCTagProvider<>(this);
        }
    }

}
