package io.github.piscescup.mc.fabric.datagen.lang;

import io.github.piscescup.mc.fabric.datagen.factories.PCLanguageProviderFactory;
import io.github.piscescup.mc.fabric.utils.constant.Language;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.stat.StatType;
import net.minecraft.text.TextContent;
import net.minecraft.text.TranslatableTextContent;
import net.minecraft.util.Identifier;
import net.minecraft.village.VillagerProfession;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static io.github.piscescup.mc.fabric.References.MOD_LOGGER;

/**
 * <p>
 *     A provider for generating language files.
 * </p>
 *
 * @see PCLanguageProviderFactory
 * @author REN YuanTong
 * @Date 2025-04-12
 * @since 1.0.0
 */
public class PCLanguageProvider
    extends FabricLanguageProvider
{
    private String name;
    private final Map<Object, String > langMap;

    protected PCLanguageProvider(Builder builder) {
        super(builder.dataOutput, builder.lang.getCode(), builder.registryLookup);
        this.name = builder.name + "/" + builder.lang.getCode();
        this.langMap = builder.langMap;
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        langMap.forEach(
            (o, s) -> {
                if ( o instanceof Item item)
                    translationBuilder.add(item, s);
                else if ( o instanceof Block block)
                    translationBuilder.add(block, s);
                else if ( o instanceof EntityType<?> entityType)
                    translationBuilder.add(entityType, s);
                else if ( o instanceof ItemGroup itemGroup){
                    TextContent content = itemGroup.getDisplayName().getContent();

                    if ( content instanceof TranslatableTextContent translatableTextContent) {
                        translationBuilder.add(translatableTextContent.getKey(), s);
                    } else {
                        throw new UnsupportedOperationException(
                            "Cannot add language entry for ItemGroup (%s) as the display name is not translatable."
                                .formatted(itemGroup.getDisplayName().getString())
                        );
                    }
                }
                else if ( o instanceof StatType<?> statType)
                    translationBuilder.add(statType, s);
                else if ( o instanceof StatusEffect statusEffect)
                    translationBuilder.add(statusEffect, s);
                else if ( o instanceof Identifier id )
                    translationBuilder.add(id, s);
                else if ( o instanceof TagKey<?> tagKey )
                    translationBuilder.add(tagKey, s);
                else if ( o instanceof Path existingLanguageFile)
                    try {
                        translationBuilder.add(existingLanguageFile);
                    } catch (IOException e) {
                        MOD_LOGGER.info(
                            "Error while adding existing language file: {}",
                            existingLanguageFile.getFileName()
                        );
                    }
                else if ( o instanceof String key )
                    translationBuilder.add(key, s);
                else if ( o instanceof VillagerProfession profession)
                    translationBuilder.add(
                        "entity.minecraft.villager." + Identifier.of(profession.id()).getPath(),
                        s
                    );

            }
        );
    }


    public static class Builder {
        private FabricDataOutput dataOutput;
        private CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup;
        private Language lang;
        private String name;
        private Map<Object, String > langMap;

        private Builder() {}

        public static Builder create() {
            return new Builder();
        }

        public Builder dataOutput(FabricDataOutput dataOutput) {
            this.dataOutput = dataOutput;
            return this;
        }

        public Builder registryLookup(CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
            this.registryLookup = registryLookup;
            return this;
        }

        public Builder lang(Language lang) {
            this.lang = lang;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder langMap(Map<Object, String > langMap) {
            this.langMap = langMap;
            return this;
        }

        public PCLanguageProvider build() {
            return new PCLanguageProvider(this);
        }

    }
}
