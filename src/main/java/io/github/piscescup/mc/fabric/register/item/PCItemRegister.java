package io.github.piscescup.mc.fabric.register.item;

import io.github.piscescup.mc.fabric.datagen.factories.PCModelProviderFactory;
import io.github.piscescup.mc.fabric.datagen.models.maps.PCItemModelMap;
import io.github.piscescup.mc.fabric.register.PCRegister;
import io.github.piscescup.mc.fabric.register.recipe.PCShapedRecipe;
import io.github.piscescup.mc.fabric.register.recipe.PCShapelessRecipe;
import net.minecraft.client.data.Model;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * <h1>Description</h1>
 *
 * <p>
 *     A register util for registering {@code Item} in Minecraft.
 * </p>
 *
 * <h1>Usages</h1>
 * Below is a usage:
 * <blockquote><pre>
 *     public static final Item ITEM1 = PCItemRegister.create(MOD_ID, "item1")
 *         .registerAndBuild()
 *         .shapedRecipe(PCShapedRecipe.Builder.create()
 *             .pattern("***")
 *             .pattern("***")
 *             .pattern(" # ")
 *             .definition('*', Items.ACACIA_PLANKS)
 *             .definition('#', Items.ACACIA_BUTTON)
 *             .category(RecipeCategory.BUILDING_BLOCKS)
 *             .criterion("has_item", Items.ACACIA_PLANKS)
 *             .count(4)
 *             .buildEntry()
 *         )
 *         .translate(Language.EN_US, "Test Item1")
 *         .translate(Language.ZH_CN, "测试物品1")
 *         .model(Models.GENERATED)
 *         .get();
 * </pre></blockquote>
 *
 *
 * @author REN YuanTong
 * @Date 2025-04-06
 * @since 1.0.0
 */
public final class PCItemRegister
    extends PCRegister<Item, PCItemRegister, ItemPostRegisterConfig<PCItemRegister>>
    implements ItemPreRegisterConfig<PCItemRegister>, ItemPostRegisterConfig<PCItemRegister>
{
    private Item.Settings settings = new Item.Settings();
    private Function<Item.Settings, Item> factory = Item::new;
    private UnaryOperator<Item.Settings> postProcessSettings = settings -> settings;


    private PCItemRegister(Identifier id) {
        super(id);
        this.key = RegistryKey.of(RegistryKeys.ITEM, id);
    }

    /**
     * <p>
     * Registers the item and returns the registered instance.
     * </p>
     *
     * <p>
     * Automatically handles block item registration when applicable
     * by adding to {@link Item#BLOCK_ITEMS}.
     * </p>
     *
     * @return The register.
     */
    @Override
    public ItemPostRegisterConfig<PCItemRegister> registerAndBuild() {
        Item.Settings newSettings = this.postProcessSettings.apply(settings);

        Item item = factory.apply(newSettings.registryKey(this.key));

        if ( item instanceof BlockItem blockItem )
            blockItem.appendBlocks(Item.BLOCK_ITEMS, item);

        this.targetRegistered = Registry.register(Registries.ITEM, key, item);
        return this;
    }

    /**
     * <p>
     *     Creates a new item register with the given path.
    * </p>
    * @param path the path of the block to be registered
     * @return a new block register
     */
    @Contract("_ -> new")
    public static @NotNull ItemPreRegisterConfig<PCItemRegister> createFor(String path) {
        return new PCItemRegister(Identifier.of(path));
    }

    /**
     * <p>
     *     Creates an item register with the specified namespace and path.
     * </p>
     *
     * @param namespace The namespace for the item
     * @param path The item path
     * @return A new item register
     */
    @Contract("_, _ -> new")
    public static @NotNull ItemPreRegisterConfig<PCItemRegister> createFor(String namespace, String path) {
        return new PCItemRegister(Identifier.of(namespace, path));
    }


    @Contract("_ -> new")
    public static @NotNull ItemPreRegisterConfig<PCItemRegister> createFor(Identifier identifier) {
        return new PCItemRegister(identifier);
    }

    public static ItemPreRegisterConfig<PCItemRegister> createSwordItem(
        String namespace, String path,
        ToolMaterial material, float attackDamage, float attackSpeed
    ) {
        PCItemRegister pcItemRegister = new PCItemRegister(Identifier.of(namespace, path));
        pcItemRegister.postProcessSettings = settings ->
            settings.sword(material, attackDamage, attackSpeed);
        return pcItemRegister;
    }

    public static ItemPreRegisterConfig<PCItemRegister> createPickaxeItem(
        String namespace, String path,
        ToolMaterial material, int attackDamage, float attackSpeed
    ) {
        PCItemRegister pcItemRegister = new PCItemRegister(Identifier.of(namespace, path));
        pcItemRegister.postProcessSettings = settings ->
            settings.pickaxe(material, attackDamage, attackSpeed);
        return pcItemRegister;
    }

    public static ItemPreRegisterConfig<PCItemRegister> createAxeItem(
        String namespace, String path,
        ToolMaterial material, int attackDamage, float attackSpeed
    ) {
        PCItemRegister pcItemRegister = new PCItemRegister(Identifier.of(namespace, path));
        pcItemRegister.postProcessSettings = settings ->
            settings.axe(material, attackDamage, attackSpeed);
        return pcItemRegister;
    }

    public static ItemPreRegisterConfig<PCItemRegister> createShovelItem(
        String namespace, String path,
        ToolMaterial material, float attackDamage, float attackSpeed
    ) {
        PCItemRegister pcItemRegister = new PCItemRegister(Identifier.of(namespace, path));
        pcItemRegister.postProcessSettings = settings ->
            settings.shovel(material, attackDamage, attackSpeed);
        return pcItemRegister;
    }

    public static ItemPreRegisterConfig<PCItemRegister> createHoeItem(
        String namespace, String path,
        ToolMaterial material, float attackDamage, float attackSpeed
    ) {
        PCItemRegister pcItemRegister = new PCItemRegister(Identifier.of(namespace, path));
        pcItemRegister.postProcessSettings = settings ->
            settings.hoe(material, attackDamage, attackSpeed);
        return pcItemRegister;
    }

    public static ItemPreRegisterConfig<PCItemRegister> createArmorItem(
        String namespace, String path,
        ArmorMaterial material, EquipmentType type
    ) {
        PCItemRegister pcItemRegister = new PCItemRegister(Identifier.of(namespace, path));
        pcItemRegister.postProcessSettings = settings ->
            settings.armor(material, type);
        return pcItemRegister;
    }

    public static ItemPreRegisterConfig<PCItemRegister> createSwordItem(
        String fullPath,
        ToolMaterial material, float attackDamage, float attackSpeed
    ) {
        PCItemRegister pcItemRegister = new PCItemRegister(Identifier.tryParse(fullPath));
        pcItemRegister.postProcessSettings = settings ->
            settings.sword(material, attackDamage, attackSpeed);
        return pcItemRegister;
    }

    public static ItemPreRegisterConfig<PCItemRegister> createPickaxeItem(
        String fullPath,
        ToolMaterial material, int attackDamage, float attackSpeed
    ) {
        PCItemRegister pcItemRegister = new PCItemRegister(Identifier.tryParse(fullPath));
        pcItemRegister.postProcessSettings = settings ->
            settings.pickaxe(material, attackDamage, attackSpeed);
        return pcItemRegister;
    }

    public static ItemPreRegisterConfig<PCItemRegister> createAxeItem(
        String fullPath,
        ToolMaterial material, int attackDamage, float attackSpeed
    ) {
        PCItemRegister pcItemRegister = new PCItemRegister(Identifier.tryParse(fullPath));
        pcItemRegister.postProcessSettings = settings ->
            settings.axe(material, attackDamage, attackSpeed);
        return pcItemRegister;
    }

    public static ItemPreRegisterConfig<PCItemRegister> createShovelItem(
        String fullPath,
        ToolMaterial material, float attackDamage, float attackSpeed
    ) {
        PCItemRegister pcItemRegister = new PCItemRegister(Identifier.tryParse(fullPath));
        pcItemRegister.postProcessSettings = settings ->
            settings.shovel(material, attackDamage, attackSpeed);
        return pcItemRegister;
    }

    public static ItemPreRegisterConfig<PCItemRegister> createHoeItem(
        String fullPath,
        ToolMaterial material, float attackDamage, float attackSpeed
    ) {
        PCItemRegister pcItemRegister = new PCItemRegister(Identifier.tryParse(fullPath));
        pcItemRegister.postProcessSettings = settings ->
            settings.hoe(material, attackDamage, attackSpeed);
        return pcItemRegister;
    }

    public static ItemPreRegisterConfig<PCItemRegister> createArmorItem(
        String fullPath,
        ArmorMaterial material, EquipmentType type
    ) {
        PCItemRegister pcItemRegister = new PCItemRegister(Identifier.tryParse(fullPath));
        pcItemRegister.postProcessSettings = settings ->
            settings.armor(material, type);
        return pcItemRegister;
    }

    public static ItemPreRegisterConfig<PCItemRegister> createSwordItem(
        Identifier id,
        ToolMaterial material, float attackDamage, float attackSpeed
    ) {
        PCItemRegister pcItemRegister = new PCItemRegister(id);
        pcItemRegister.postProcessSettings = settings ->
            settings.sword(material, attackDamage, attackSpeed);
        return pcItemRegister;
    }

    public static ItemPreRegisterConfig<PCItemRegister> createPickaxeItem(
        Identifier id,
        ToolMaterial material, int attackDamage, float attackSpeed
    ) {
        PCItemRegister pcItemRegister = new PCItemRegister(id);
        pcItemRegister.postProcessSettings = settings ->
            settings.pickaxe(material, attackDamage, attackSpeed);
        return pcItemRegister;
    }

    public static ItemPreRegisterConfig<PCItemRegister> createAxeItem(
        Identifier id,
        ToolMaterial material, int attackDamage, float attackSpeed
    ) {
        PCItemRegister pcItemRegister = new PCItemRegister(id);
        pcItemRegister.postProcessSettings = settings ->
            settings.axe(material, attackDamage, attackSpeed);
        return pcItemRegister;
    }

    public static ItemPreRegisterConfig<PCItemRegister> createShovelItem(
        Identifier id,
        ToolMaterial material, float attackDamage, float attackSpeed
    ) {
        PCItemRegister pcItemRegister = new PCItemRegister(id);
        pcItemRegister.postProcessSettings = settings ->
            settings.shovel(material, attackDamage, attackSpeed);
        return pcItemRegister;
    }

    public static ItemPreRegisterConfig<PCItemRegister> createHoeItem(
        Identifier id,
        ToolMaterial material, float attackDamage, float attackSpeed
    ) {
        PCItemRegister pcItemRegister = new PCItemRegister(id);
        pcItemRegister.postProcessSettings = settings ->
            settings.hoe(material, attackDamage, attackSpeed);
        return pcItemRegister;
    }

    public static ItemPreRegisterConfig<PCItemRegister> createArmorItem(
        Identifier id,
        ArmorMaterial material, EquipmentType type
    ) {
        PCItemRegister pcItemRegister = new PCItemRegister(id);
        pcItemRegister.postProcessSettings = settings ->
            settings.armor(material, type);
        return pcItemRegister;
    }

    /**
     * <p>
     * Sets the item settings configuration.
     * </p>
     *
     * <p>
     * Default settings are empty. Use this to configure stack size,
     * durability, or other item properties.
     * </p>
     *
     * @param settings The item settings to apply
     * @return This item register
     */
    @Override
    public ItemPreRegisterConfig<PCItemRegister> settings(Item.@NotNull Settings settings) {
        this.settings = settings;
        return this;
    }

    /**
     * <p>
     *     Specifies a custom factory for item creation.
     * </p>
     *
     * <p>
     *     Default factory creates basic {@link Item} instances.
     * </p>
     *
     * @param factory The item creation factory
     * @return This item register
     */
    public ItemPreRegisterConfig<PCItemRegister> factory(@NotNull Function<Item.Settings, Item> factory) {
        this.factory = factory;
        return this;
    }

    /**
     * <p>
     *     Set the model of the item.
     * </p>
     *
     * <p>
     *     The model will be added to the {@link PCItemModelMap}.<br>
     *     Use {@link PCModelProviderFactory} to generate the model.
     * </p>
     * @param model The model of the item
     * @return This item register
     * @see PCModelProviderFactory
     */
    public ItemPostRegisterConfig<PCItemRegister> model(@NotNull Model model) {
        PCItemModelMap.instance().put(this.targetRegistered, model);
        return this;
    }

    @Deprecated
    public ItemPostRegisterConfig<PCItemRegister> shapedRecipe(@NotNull PCShapedRecipe shapedRecipe) {
        // PCShapedRecipeMap.instance().put(shapedRecipe, this.targetRegistered);
        return this;
    }

    @Deprecated
    public ItemPostRegisterConfig<PCItemRegister> shapelessRecipe(@NotNull PCShapelessRecipe shapelessRecipe) {
        // PCShapelessRecipeMap.instance().put(shapelessRecipe, this.targetRegistered);
        return this;
    }

}
