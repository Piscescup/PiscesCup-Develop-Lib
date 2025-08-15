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
    include(
        modImplementation("io.github.piscescup:pc_develop_lib:1.1.1+1.21.4")
    )
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
pc_dev_lib_version=1.1.1+1.21.4
```


#### ~~Maven (Deprecated)~~
Add below code in the `dependencies` mark of your `pom.xml` file:
```xml
<dependency>
  <groupId>io.github.piscescup</groupId>
  <artifactId>pc_develop_lib</artifactId>
  <version>1.1.1+1.21.4</version>
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
    <pc_dev_lib_version>1.1.1+1.21.4</pc_dev_lib_version>
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

The Lib provides an abstract class called `PCTagKeyRegister<T, C extends TagKeyContainer<T>>`.<br>
This class maintains a variable: `container`, which is an instance of <code>TagKeyContainer</code>. 
<ul>
    <li>param <code>T</code>: The base type of the <code>TagKey</code>, such as <code>Item</code>, <code>Block</code>. `POI` and so on.</li>
    <li>param <code>C</code>: A container of <code>TagKey</code>, see:
<a href="src/main/java/cn/edu/jlu/renyt1621/datagen/tag/container/TagKeyContainer.java"><code>TagKeyContainer</code></a>.
</ul>
You can create a class which extends it to register tags.

Below is a process to create a custom Tag Register:
##### Step 1

Create a class which implements `TagKeyContainer`:
```java
public class PCItemTagKeyContainer
    implements TagKeyContainer<Item>
{
    private TagKey<Item> tag;

    private final List<Item> items = new ArrayList<>();
    private final List<TagKey<Item>> tags = new ArrayList<>();

    private PCItemTagKeyContainer() {}

    private PCItemTagKeyContainer(TagKey<Item> tag) {
        this.tag = tag;
    }

    public static PCItemTagKeyContainer createFor(TagKey<Item> tag) {
        return new PCItemTagKeyContainer(tag);
    }

    public static PCItemTagKeyContainer createFrom(PCItemTagKeyContainer other) {
        PCItemTagKeyContainer container = new PCItemTagKeyContainer();
        container.tag = other.tag;
        container.items.addAll(other.items);
        container.tags.addAll(other.tags);
        return container;
    }

    public TagKey<Item> getTargetTag() {
        return tag;
    }

    public List<Item> getContainedThings() {
        return items;
    }

    public List<TagKey<Item>> getContainedTags() {
        return tags;
    }

    public boolean addContainedThing(@NotNull Item item) {
        Objects.requireNonNull(item);
        return items.add(item);
    }

    public boolean addContainedTag(@NotNull TagKey<Item> tag) {
        Objects.requireNonNull(tag);
        return tags.add(tag);
    }

    public boolean addContainedThings(@NotNull List<Item> items) {
        Objects.requireNonNull(items);
        this.items.addAll(items);
        return true;
    }

    public final boolean addContainedTags(@NotNull List<TagKey<Item>> tags) {
        Objects.requireNonNull(tags);
        this.tags.addAll(tags);
        return true;
    }

    public boolean isEmpty() {
        return items.isEmpty() && tags.isEmpty();
    }

    @Override
    public boolean removeContainedThing(@NotNull Item thing) {
        Objects.requireNonNull(thing);
        return this.items.remove(thing);
    }

    @Override
    public boolean removeContainedTags(@NotNull List<TagKey<Item>> tags) {
        Objects.requireNonNull(tags);
        return this.tags.removeAll(tags);
    }

    @Override
    public boolean containsThing(@NotNull Item thing) {
        Objects.requireNonNull(thing);
        return this.items.contains(thing);
    }

    @Override
    public boolean removeContainedThings(@NotNull List<Item> things) {
        Objects.requireNonNull(things);
        return this.items.removeAll(things);
    }

    @Override
    public boolean removeContainedTag(@NotNull TagKey<Item> tag) {
        Objects.requireNonNull(tag);
        return this.tags.remove(tag);
    }

    @Override
    public boolean containsTag(@NotNull TagKey<Item> tag) {
        Objects.requireNonNull(tag);
        return this.tags.contains(tag);
    }
}
```

##### Step 2
Create a class which implements `TagKeyContainerList`, the subclass should be a singleton:
```java
public class PCItemTagKeyContainerList
    implements TagKeyContainerList<PCItemTagKeyContainer>
{
    private static volatile PCItemTagKeyContainerList INSTANCE;

    private final List<PCItemTagKeyContainer> itemTagContainers = new ArrayList<>();

    private PCItemTagKeyContainerList() {}

    public static PCItemTagKeyContainerList instance() {
        if (INSTANCE == null) {
            synchronized (PCItemTagKeyContainerList.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PCItemTagKeyContainerList();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public List<PCItemTagKeyContainer> getContainerList() {
        return itemTagContainers;
    }

    @Override
    public boolean addContainer(@NotNull PCItemTagKeyContainer itemTag) {
        Objects.requireNonNull(itemTag);
        return this.itemTagContainers.add(itemTag);
    }

}

```
##### Step 3
Create a class which extends `PCTagKeyRegister<T, C>`. <br>
And when implementing `registerAndBuild()` method, 
you should add the `container` to the `PCItemTagKeyContainerList` you created:
```java
public class PCItemTagKeyRegister
    extends PCTagKeyRegister<Item, PCItemTagKeyContainer>
{
    private PCItemTagKeyRegister(Identifier id) {
        super(id);
        this.t = TagKey.of(RegistryKeys.ITEM, this.id);
        this.container = PCItemTagKeyContainer.createFor(this.t);
    }

    public static PCItemTagKeyRegister createFor(String path) {
        return new PCItemTagKeyRegister(Identifier.of(path));
    }

    public static PCItemTagKeyRegister createFor(Identifier identifier) {
        return new PCItemTagKeyRegister(identifier);
    }

    public static PCItemTagKeyRegister createFor(String namespace, String path) {
        return new PCItemTagKeyRegister(Identifier.of(namespace, path));
    }

    public static PCItemTagKeyRegister createForVanilla(TagKey<Item> vanillaTag) {
        return new PCItemTagKeyRegister(vanillaTag.id());
    }
    
    @Override
    public PCItemTagKeyRegister registerAndBuild() {
        if ( this.container.isEmpty() )
            throw new IllegalArgumentException(
                "`Item` or `Tag` cannot be empty when using PCItemTagKeyRegister."
            );

        PCItemTagKeyContainerList.instance().addContainer(this.container);

        return this;
    }

    @Override
    protected PCItemTagKeyRegister self() {
        return this;
    }
    
}
```

The lib provides some pre-defined `TagKeyRegister`:<br>
_**Portal: [PCItemTagKeyRegister](src/main/java/cn/edu/jlu/renyt1621/register/tag/options/PCItemTagKeyRegister.java) &
[PCBlockTagKeyRegister](src/main/java/cn/edu/jlu/renyt1621/register/tag/options/PCBlockTagKeyRegister.java) & 
[PCPOITagKeyRegister.java](src/main/java/cn/edu/jlu/renyt1621/register/tag/options/PCPOITagKeyRegister.java)**_

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

#### Advancement
_**Portal:
[PCAdvancement](src/main/java/cn/edu/jlu/renyt1621/register/advancement/PCAdvancement.java) &
[PCAdvancementTabContainer.java](src/main/java/cn/edu/jlu/renyt1621/datagen/advancements/PCAdvancementTabContainer.java) &
[PCAdvancementTabGenerator.java](src/main/java/cn/edu/jlu/renyt1621/datagen/advancements/PCAdvancementTabGenerator.java) &
[PCAdvancementProviderFactory.java](src/main/java/cn/edu/jlu/renyt1621/datagen/factories/PCAdvancementProviderFactory.java)**_

The mod provides the class `PCAdvancement.java` to set the information for the advancement to be generated.
The `PCAdvancementTabGenerator.java`: set an advancement tab.
The `PCAdvancementTabContainer.java`: an advancement tab container, which can add `PCAdvancementTabGenerator`.
The `PCAdvancementProviderFactory.java`: create a factory for the advancement provider.

You can create a class which extends the class: `PCAdvancementTabGenerator` , and then implement the method 
`accept(RegistryWrapper.WrapperLookup registries,Consumer<AdvancementEntry> exporter)` to generate the advancement.

The `PCAdvancementTabContainer` is a singleton class, which can add `PCAdvancementTabGenerator`.

The `PCAdvancementProviderFactory.java` is a factory for the advancement provider. 
You can use it to create a factory for a `PCAdvancementTabContainer`.

Below is a procedure for creating advancement:
##### Step 1
Use the `PCAdvancement.java` to create advancements:
```java
public final class ModAdvancements {
    public static final PCAdvancement PC_TEST_ADVANCEMENT1 =
        PCAdvancement.of(Identifier.of(MOD_ID, "pc_test_advancement1"), "pc_test_advancement1")
            .icon(ModItems.PC_ITEM1)
            .background(null)
            .frame(AdvancementFrame.TASK)
            .announce(true, true, false)
            .titleTranslation(Language.EN_US, "PiscesCup Test Advancement 1")
            .titleTranslation(Language.ZH_CN, "PiscesCup 测试进度 1")
            .descriptionTranslation(Language.EN_US, "This is a test advancement 1.")
            .descriptionTranslation(Language.ZH_CN, "这是测试进度1。")
            .rewards(AdvancementRewards.Builder.experience(1000))
            .get();

    public static final PCAdvancement PC_ADVANCEMENT_2 =
        PCAdvancement.of(Identifier.of(MOD_ID, "pc_advancement2"), "pc_advancement2")
            .icon(Items.ACACIA_BUTTON)
            .background(null)
            .frame(AdvancementFrame.CHALLENGE)
            .announce(true, true, true)
            .titleTranslation(Language.EN_US, "PiscesCup Test Advancement 2")
            .titleTranslation(Language.ZH_CN, "PiscesCup 测试进度 2")
            .descriptionTranslation(Language.EN_US, "This is a test advancement 2.")
            .descriptionTranslation(Language.ZH_CN, "这是测试进度2。")
            .rewards(AdvancementRewards.Builder.experience(200000))
            .get();


    public static void register() {}
}
```
##### Step 2
Create a class which extends the class: `PCAdvancementTabGenerator` , and then implement the method to generate the advancement:
```java
public final class ModTabAdvancement
    extends PCAdvancementTabGenerator
{
    public ModTabAdvancement() {}

    @Override
    public void accept(RegistryWrapper.WrapperLookup registries, Consumer<AdvancementEntry> exporter) {
        AdvancementEntry ROOT = ModAdvancements.PC_TEST_ADVANCEMENT1
            .applyParentAndCriterion(
                null,
                Map.of("test1", InventoryChangedCriterion.Conditions.items(Items.DIAMOND_BLOCK)),
                exporter
            );
        
        AdvancementEntry TEST_ADVANCEMENT2 = ModAdvancements.PC_ADVANCEMENT_2
            .applyParentAndCriterion(
                ROOT,
                Map.of("test2", InventoryChangedCriterion.Conditions.items(Items.IRON_BLOCK)),
                exporter
            );
        // Other advancements...
    }
}
```

##### Step 3
Register the advancements in the mod entry:
```java
public class PiscesCupDevelopLib implements ModInitializer {
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		MOD_LOGGER.info("Hello Fabric world!");
		MOD_LOGGER.info("Hello, " + MOD_NAME);

		// ModItems.register();
		// ModBlocks.register();
		// ModItemGroups.register();
		// ModTags.register();
		ModAdvancements.register();
		// KeyAction.registerTranslations();
        //
		// ModVillagerPOIs.register();
		// ModVillagerProfessions.register();
		// ModVillagers.register();
		// ModVillagerTrades.register();
		MOD_LOGGER.info("Finish registering");
	}
}
```

##### Step 4
In the entry of the `DataGenerator` provided by `Fabric`, use the `PCAdvancementProviderFactory.java` to create a factory for the advancement tabs:
```java
public class PiscesCupDevelopLibDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        
		PCAdvancementProviderFactory.createFor(
			PCAdvancementTabContainer.instance()
				.addAdvancementTab(ModTabAdvancement::new)
                // Add advancement tabs
		)
			.forEach(pack::addProvider);

	}
}
```

##### Step 5
Run the common task `runDataGen` to generate the advancements.


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
