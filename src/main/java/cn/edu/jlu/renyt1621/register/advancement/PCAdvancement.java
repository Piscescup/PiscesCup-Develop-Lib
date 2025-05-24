package cn.edu.jlu.renyt1621.register.advancement;

import cn.edu.jlu.renyt1621.datagen.lang.map.LangMap;
import cn.edu.jlu.renyt1621.utils.constant.Language;
import net.minecraft.advancement.*;
import net.minecraft.item.ItemConvertible;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * <h2>Description</h2>
 * <p>
 *     A new way to simplify registering {@code AdvancementEntry}.
 * </p>
 * <h2>Usages</h2>
 * <p>
 * You can create a new class to restore the advancements in a class.
 * </p>
 *
 * <p>
 *     Below are some examples:
 * </p>
 * <blockquote><pre>
 * public final class ModAdvancements {
 *     public static final PCAdvancement PC_TEST_ADVANCEMENT1 =
 *         PCAdvancement.of(Identifier.of(MOD_ID, "pc_test_advancement1"), "pc_test_advancement1")
 *             .icon(ModItems.PC_ITEM1)
 *             .background(null)
 *             .frame(AdvancementFrame.TASK)
 *             .announce(true, true, false)
 *             .titleTranslation(Language.EN_US, "PiscesCup Test Advancement 1")
 *             .titleTranslation(Language.ZH_CN, "PiscesCup 测试进度 1")
 *             .descriptionTranslation(Language.EN_US, "This is a test advancement 1.")
 *             .descriptionTranslation(Language.ZH_CN, "这是测试进度1。")
 *             .rewards(AdvancementRewards.Builder.experience(1000))
 *             .criterionMerger(AdvancementRequirements.CriterionMerger.AND)
 *             .get();
 * }
 * </pre></blockquote>
 * And then you can use this in the {@code DataGenerator} file provided by {@code Fabric}:
 * <blockquote><pre>
 * public class PiscesCupDevelopLibDataGenerator
 *     implements DataGeneratorEntrypoint
 * {
 *     &#64;Override
 *     public void onInitializeDataGenerator(FabricDataGenerator generator) {
 *         FabricDataGenerator.Pack pack = generator.createPack();
 *         PCAdvancementProviderFactory.createFor(
 *         	   PCAdvancementTabContainer.instance()
 *         		   .addAdvancementTab(ModTabAdvancement::new)
 *         )
 *             .forEach(pack::addProvider);
 *     }
 * }
 * </pre></blockquote>
 *
 *
 * @author REN YuanTong
 * @Date 2025-05-20
 * @since 1.1.0
 */
public class PCAdvancement {
    private static final UnaryOperator<String> APPLY_ADVANCEMENT_TRANSLATION_PREFIX =
        info -> "advancements." + info;
    private static final UnaryOperator<String> APPLY_ADVANCEMENT_TITLE_SUFFIX =
        key -> key + ".title" ;
    private static final UnaryOperator<String> APPLY_ADVANCEMENT_DESCRIPTION_SUFFIX =
        key -> key + ".description" ;
    private static final UnaryOperator<Identifier> APPLY_PATH_PREFIX =
        id -> Identifier.of(id.getNamespace(), "advancements/" + id.getPath());

    private final Identifier id;
    private final String advancementName;
    private final String translateKey;
    private ItemConvertible icon;
    private boolean showToast;
    private boolean announceToChat;
    private boolean hidden;
    private AdvancementFrame frame;
    @Nullable
    private Identifier background;
    @Nullable
    private AdvancementRewards rewards;
    @Nullable
    private AdvancementRequirements requirements;
    private AdvancementRequirements.CriterionMerger criterionMerger;

    private PCAdvancement(Identifier id, String advancementName) {
        this.id = id;
        this.advancementName = advancementName;
        this.translateKey =
            APPLY_ADVANCEMENT_TRANSLATION_PREFIX.apply(
                id.getNamespace()  + "." + id.getPath().replace("/", ".")
            );
    }

    public static PCAdvancement of(Identifier id, String advancementName) {
        return new PCAdvancement(APPLY_PATH_PREFIX.apply(id), advancementName);
    }


    public PCAdvancement icon(ItemConvertible icon) {
        this.icon = icon;
        return this;
    }

    public PCAdvancement announce(boolean showToast, boolean announceToChat, boolean hidden) {
        this.showToast = showToast;
        this.announceToChat = announceToChat;
        this.hidden = hidden;
        return this;
    }

    public PCAdvancement background(Identifier background) {
        this.background = background;
        return this;
    }

    public PCAdvancement rewards(AdvancementRewards rewards) {
        this.rewards = rewards;
        return this;
    }

    public PCAdvancement rewards(AdvancementRewards.Builder builder) {
        this.rewards = builder.build();
        return this;
    }

    public PCAdvancement frame(AdvancementFrame frame) {
        this.frame = frame;
        return this;
    }

    public PCAdvancement requirements(AdvancementRequirements requirements) {
        this.requirements = requirements;
        return this;
    }

    public PCAdvancement criterionMerger(AdvancementRequirements.CriterionMerger criterionMerger) {
        this.criterionMerger = criterionMerger;
        return this;
    }

    public Identifier getId() {
        return id;
    }

    public String getAdvancementName() {
        return advancementName;
    }

    public String getTranslateKey() {
        return translateKey;
    }

    public ItemConvertible getIcon() {
        return icon;
    }

    public boolean isShowToast() {
        return showToast;
    }

    public boolean isAnnounceToChat() {
        return announceToChat;
    }

    public boolean isHidden() {
        return hidden;
    }

    @Nullable
    public Identifier getBackground() {
        return background;
    }

    @Nullable
    public AdvancementRewards getRewards() {
        return rewards;
    }

    public AdvancementFrame getFrame() {
        return frame;
    }

    @Nullable
    public AdvancementRequirements getRequirements() {
        return requirements;
    }

    @Nullable
    public AdvancementRequirements.CriterionMerger getCriterionMerger() {
        return criterionMerger;
    }

    public PCAdvancement get() {
        return this;
    }

    public String titleKey() {
        return APPLY_ADVANCEMENT_TITLE_SUFFIX.apply(translateKey);
    }

    public String descriptionKey() {
        return APPLY_ADVANCEMENT_DESCRIPTION_SUFFIX.apply(translateKey);
    }

    public PCAdvancement titleTranslation(Language lang, String translation) {
        LangMap.instance().put(lang, titleKey(), translation);
        return this;
    }

    public PCAdvancement descriptionTranslation(Language lang, String translation) {
        LangMap.instance().put(lang, descriptionKey(), translation);
        return this;
    }

    public AdvancementEntry applyParentAndCriterion(
        AdvancementEntry parent,
        Map<String, AdvancementCriterion<?>> criteria,
        Consumer<AdvancementEntry> exporter
    ) {
        Advancement.Builder builder = Advancement.Builder.create()
            .display(
                icon,
                Text.translatable(titleKey()),
                Text.translatable(descriptionKey()),
                background,
                frame,
                showToast,
                announceToChat,
                hidden
            );

        if (requirements != null)
            builder.requirements(requirements);

        if (rewards != null)
            builder.rewards(rewards);

        if (parent != null)
            builder.parent(parent);

        if (criterionMerger != null)
            builder.criteriaMerger(criterionMerger);

        criteria.forEach(
            builder::criterion
        );

        return builder.build(exporter, id.toString()) ;


    }
}
