# PiscesCup Develop Library

## Description
The mod provides some convenient methods for developers, 
such as:
   - **Registering**: registering items, blocks, item groups, and so on. 
    You can set the recipes and simple models when you register them.
   - **Utils**: Some useful utils, such as `Language.java`, and so on
   - **Data Generating**: Provide factories for get `DataProvider`
   - ~~**GUI**: Creating a GUI (under developing)~~

## Usage
### Registering
#### Item
You can use the class `PCItemRegister.java` and 
`PCBlockItemRegister.java` to register items and block items.

Below are some examples:
```java
public static final Item ITEM1 = PCItemRegister.create(MOD_ID, "item1") // Creat the item with the given Identifier
    // Register and build
    .registerAndBuild() 
    // Create the recipe for the item
    .shapedRecipe(PCShapedRecipe.Builder.create()
        .pattern("***")
        .pattern("***")
        .pattern(" # ")
        .definition('*', Items.ACACIA_PLANKS)
        .definition('#', Items.ACACIA_BUTTON)
        .category(RecipeCategory.BUILDING_BLOCKS)
        .criterion("has_item", Items.ACACIA_PLANKS)
        .count(4)
        .build()
    )
    // Add translations.
    .translate(Language.EN_US, "Test Item1")
    .translate(Language.ZH_CN, "测试物品1")
    // Set the model of the item.
    .model(Models.GENERATED)
    // Return the item to be registered.
    .get();                                    

public static final Item ITEM2 = PCItemRegister.create(MOD_ID, "item2")
    .registerAndBuild()
    .shapelessRecipe(PCShapelessRecipe.Builder.create()
        .category(RecipeCategory.BUILDING_BLOCKS)
        .input(ItemTags.PLANKS)
        .input(ItemTags.BUTTONS)
        .input(Items.IRON_INGOT)
        .count(4)
        .criterion("has_planks", Items.IRON_INGOT)
        .build()
    )
    .translate(Language.EN_US, "Test Item2")
    .translate(Language.ZH_CN, "测试物品2")
    .model(Models.GENERATED)
    .get();


public static final Item BLOCK_ITEM = PCBlockItemRegister.create(BLOCK)
    .settings(new Item.Settings()
        .maxCount(16)
        .fireproof()
        .rarity(Rarity.COMMON)
    )
    .registerAndBuild()
    .shapelessRecipe(PCShapelessRecipe.Builder.create()
        .category(RecipeCategory.BUILDING_BLOCKS)
        .input(ItemTags.PLANKS)
        .input(ItemTags.BUTTONS)
        .input(Items.IRON_INGOT)
        .count(4)
        .criterion("has_planks", Items.IRON_INGOT)
        .build()
    )
    .get();
```
#### Blocks
You can use the class `PCBlockRegister.java` to register blocks.

Below are some examples:
```java
public static final Block BLOCK = PCBlockRegister.create(MOD_ID, "block1")
    .settings(AbstractBlock.Settings.create()
        .burnable()
        .mapColor(DyeColor.BROWN)
        .hardness(1.0f)
    )
    .registerAndBuild()
    .translate(Language.EN_US, "Test Block1")
    .translate(Language.ZH_CN, "测试方块1")
    .simpleCubeAll()
    .get();
```

#### Item Groups
You can use the class `PCItemGroupRegister.java` to register item groups.

Below are some examples:
```java
public static final ItemGroup ITEM_GROUP1 = PCItemGroupRegister.create(MOD_ID, "item_group1")
    .itemGroupBuilder(ItemGroup.create(ItemGroup.Row.BOTTOM, 7)
        .icon(() -> new ItemStack(Items.DIAMOND))
        .entries(
            (text, entries) -> {
                entries.add(ModItems.ITEM1);
                entries.add(ModBlocks.BLOCK);
            }
        )
    )
    .registerAndBuild()
    .translate(Language.EN_US, "Test ItemGroup 1")
    .translate(Language.ZH_CN, "测试物品组1")
    .get();
```
