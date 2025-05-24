# PiscesCup Develop Library

## Description
The lib provides some convenient methods for developers, 
such as:
   - **Registering**: registering items, blocks, item groups, and tags, and so on. 
    You can set the recipes and simple models when you register them.
   - **Utils**: Some useful utils, such as `Language.java`, and so on
   - **Data Generating**: Provide factories for getting `DataProvider`
   - ~~**GUI**: Creating a GUI (under developing)~~

The examples are in the 
[Examples Folder](src/main/java/cn/edu/jlu/renyt1621/tests).

## Install
#### Gradle
Add below code in the `dependencies` part of your `build.gradle` file:
```groovy
dependencies {
    // Add this to the dependencies
    include(modImplementation("io.github.piscescup:pc_develop_lib:1.0.0"))
}
```
To update the version easily, you can use below way:
```groovy
dependencies {
    include(
        modImplementation("io.github.piscescup:pc_develop_lib:${pc_dev_lib_version}")
    )
}
```
And then add the property (The name of the property is up to you) in the `gradle.properties` file:
```properties
pc_dev_lib_version=1.0.1
```


#### ~~Maven (Deprecated)~~
Add below code in the `dependencies` mark of your `pom.xml` file:
```xml
<dependency>
  <groupId>io.github.piscescup</groupId>
  <artifactId>pc_develop_lib</artifactId>
  <version>1.0.0</version>
</dependency>
```
To update the version easily, you can use below way:
```xml
<dependency>
  <groupId>io.github.piscescup</groupId>
  <artifactId>pc_develop_lib</artifactId>
  <version>${pc_dev_lib_version}</version>
</dependency>
```
And then add a property mark (The name of the property mark is up to you) in the `properties` mark of your `pom.xml` file:
```xml
<properties>
    <pc_dev_lib_version>1.0.1</pc_dev_lib_version>
</properties>
```


## Usage
### Registering


<strong style="color:red">
If you want to set translations, models, recipes, 
you should use the method <code>registerAndBuild()</code> first.
</strong>

Otherwise, an `IllegalArgumentException` will be thrown.



#### Item
_**Portal: [PCItemRegister](src/main/java/cn/edu/jlu/renyt1621/register/item/PCItemRegister.java)**_

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
_**Portal: [PCBlockRegister](src/main/java/cn/edu/jlu/renyt1621/register/block/PCBlockRegister.java)**_

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
_**Portal: [PCItemGroupRegister](src/main/java/cn/edu/jlu/renyt1621/register/itemgroup/PCItemGroupRegister.java)**_

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

#### Tags
_**Portal: [PCItemTagKeyRegister](src/main/java/cn/edu/jlu/renyt1621/register/tag/PCItemTagKeyRegister.java)**_

The mod provides ways to register `ItemTag` and `BlockTag` 
by using the class `PCItemTagKeyRegister.java` and `PCBlockTagKeyRegister.java`.

Before you use the method `registerAndBuild()`, you should use the method `add()` to add things at least once.


Below is an example for registering `ItemTag`:
```java
public static final TagKey<Item> TAG_KEY_1 = PCItemTagKeyRegister.create(MOD_ID, "tag/tag1")
    .add(ModItems.ITEM1)
    .add(ModItems.ITEM2)
    .registerAndBuild()
    .get();
```

Below is an example for registering `BlockTag`:
```java
public static final TagKey<Block> BLOCK_TAG = PCBlockTagKeyRegister.create(MOD_ID, "tag/block_tag")
    .add(ModBlocks.BLOCK)
    .registerAndBuild()
    .get();
```

Both `PCItemTagKeyRegister` and `PCBlockTagKeyRegister` provide a method `createForVanilla(TagKey vanillaTagKey)` for creating 
a `PCItemTagKeyRegister` and `PCBlockTagKeyRegister` from a vanilla tag.

Below are some examples:
```java
public static final TagKey<Block> VANILLA_NEED_IRON_TOOLS_TAG =
        PCBlockTagKeyRegister.createForVanilla(BlockTags.NEEDS_IRON_TOOL)
            .addBlock(ModBlocks.PC_BLOCK)
            .registerAndBuild()
            .get();

public static final TagKey<Block> VANILLA_PICKAXE_TAG =
    PCBlockTagKeyRegister.createForVanilla(BlockTags.PICKAXE_MINEABLE)
        .addBlock(ModBlocks.PC_BLOCK)
        .registerAndBuild()
        .get();
```

#### Recipes
_**Portal: [PCShapedRecipe](src/main/java/cn/edu/jlu/renyt1621/datagen/recipes/craft/PCShapedRecipe.java) 
& [PCShapelessRecipe](src/main/java/cn/edu/jlu/renyt1621/datagen/recipes/craft/PCShapelessRecipe.java)**_


The mod provides `PCShapedRecipe.java` and `PCShapelessRecipe.java` to set the recipes for the item.

Below are some usages:

- Create a shaped recipe by `PCShapedRecipe.java`:
    ```java
    public static final PCShapedRecipe SHAPED_RECIPE = PCShapedRecipe.Builder.create()
        .pattern("***")
        .pattern("***")
        .pattern(" # ")
        .definition('*', Items.ACACIA_PLANKS)
        .definition('#', Items.ACACIA_BUTTON)
        .category(RecipeCategory.BUILDING_BLOCKS)
        .criterion("has_item", Items.ACACIA_PLANKS)
        .count(4)
        .build();
    ```
- Create a shapeless recipe by `PCShapelessRecipe.java`:
    ```java
    public static final PCShapelessRecipe SHAPELESS_RECIPE = PCShapelessRecipe.Builder.create()
        .category(RecipeCategory.BUILDING_BLOCKS)
        .input(ItemTags.PLANKS)
        .input(ItemTags.BUTTONS)
        .input(Items.IRON_INGOT)
        .count(4)
        .criterion("has_planks", Items.IRON_INGOT)
        .build();
    ```

#### Model
The registers provide ways to set the model of the item.

For items:
You can use the method `model(Model model)` 
in the class `PCItemRegister.java` to set the model of the item.

For blocks:
The mod only provide the method `simpleCubeAll()` 
in the class `PCBlockRegister.java` to set the cube all model of the block.


### Data Generation
The mod provides some `DataProviderFactory` to get the data providers.

#### Language Provider
_**Portal: [PCLanguageProvider](src/main/java/cn/edu/jlu/renyt1621/datagen/lang/PCLanguageProvider.java)
 & [PCLanguageProviderFactory](src/main/java/cn/edu/jlu/renyt1621/datagen/factories/PCLanguageProviderFactory.java)**_


The mod provides the class `PCTranslationProviderFactory.java` to get the translation provider.

Use the `PCLanguageProviderFactory.java` to get specific language provider:

- `languageProvider(Language lang)`: Get a list of `FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider>` 
of the provider for the given language.
- `languagesProvider(Language... langs)` & `languagesProvider(List<Language> langs)`: Get a list of `FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider>`
of the provider for the given languages.
- `allLanguagesProvider()`: Get a list of `FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider>`
of the provider for all languages.

<strong style="color:red;">
    You shouldn't add the same language provider.
</strong>

Below are some examples:

```java
import java.util.List;

public class PiscesCupDevelopLibDataGenerator
    implements DataGeneratorEntrypoint 
{
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        PCLanguageProviderFactory.languagesProvider(Language.EN_US, Language.ZH_CN)
            .forEach(pack::addProvider);

        PCLanguageProviderFactory.allLanguagesProvider()
            .forEach(pack::addProvider);

        PCLanguageProviderFactory.languagesProvider(
            List.of(Language.EN_US, Language.ZH_CN)
        )
            .forEach(pack::addProvider);

    }
}
```

#### Model Provider
_**Portal: [PCModelProvider](src/main/java/cn/edu/jlu/renyt1621/datagen/models/PCModelProvider.java)
& [PCModelProviderFactory](src/main/java/cn/edu/jlu/renyt1621/datagen/factories/PCModelProviderFactory.java)**_


The mod provides the class `PCModelProviderFactory.java` to get the model provider.

Use the `PCModelProviderFactory.java` to get specific model provider:

- `modelProvider()`: Get a list of `FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider>`
of the provider.

Below is an example:
```java
public class PiscesCupDevelopLibDataGenerator 
    implements DataGeneratorEntrypoint 
{
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
    	FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

    	PCModelProviderFactory.modelProvider()
    		.forEach(pack::addProvider);
    }
}
```

#### Recipe Provider
_**Portal: [PCRecipeProvider](src/main/java/cn/edu/jlu/renyt1621/datagen/recipes/PCRecipeProvider.java)
 & [PCRecipesProviderFactory](src/main/java/cn/edu/jlu/renyt1621/datagen/factories/PCRecipesProviderFactory.java)**_


The mod provides the class `PCRecipeProviderFactory.java` to get the recipe provider.

Use the `PCRecipeProviderFactory.java` to get specific recipe provider:

- `recipesProvider()`: Get a list of `FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider>`
of the provider.

Below is an example:
```java
public class PiscesCupDevelopLibDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
    	FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

    	PCRecipesProviderFactory.recipesProvider()
    		.forEach(pack::addProvider);
    }
}
```

#### Tag Provider
_**Portal: [PCBlockTagProviderFactory](src/main/java/cn/edu/jlu/renyt1621/datagen/tag/PCBlockTagProvider.java)
 & [PCTagProviderFactory](src/main/java/cn/edu/jlu/renyt1621/datagen/factories/PCTagProviderFactory.java)**_

The mod provides an enum class: `PCTagProviderFactory.java`. Its fields are the tag providers.

The method `factories()` of the fields can return a list of 
`FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider>` of the provider.
And then you can use the method `forEach()` to add the provider to the pack.

The enum class also provides a static method `allTagProviderFactories()` 
, which can return a `List` of `FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider>` 
for all the tag providers.

Below is the example of the fields:
```java
public class PiscesCupDevelopLibDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
    	FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

    	PCTagProviderFactory.ITEM_TAG_PROVIDER.getFactories()
    		.forEach(pack::addProvider);

    	PCTagProviderFactory.BLOCK_TAG_PROVIDER.getFactories()
    		.forEach(pack::addProvider);

    }
}
```

Below is an example of the static method `allTagProviderFactories()`:
```java
public class PiscesCupDevelopLibDataGenerator 
    implements DataGeneratorEntrypoint 
{
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
    	FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        PCTagProviderFactory.allTagProviderFactories()
            .forEach(pack::addProvider);
    }
}
```




## Contact
If you have some bugs or suggestions, you can contact me by the following ways:
- [GitHub Issue](https://github.com/Piscescup/PiscesCup-Develop-Lib/issues)
- Emails:
  - piscescup@outlook.com
  - renyt1621@mails.jlu.edu.cn
  - piscescup@qq.com
