package cn.edu.jlu.renyt1621;

import cn.edu.jlu.renyt1621.datagen.advancements.PCAdvancementTabContainer;
import cn.edu.jlu.renyt1621.datagen.factories.*;
import cn.edu.jlu.renyt1621.tests.advancements.ModTabAdvancement;
import cn.edu.jlu.renyt1621.utils.constant.Language;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class PiscesCupDevelopLibDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		PCLanguageProviderFactory.languagesProvider(Language.EN_US, Language.ZH_CN)
			.forEach(pack::addProvider);

		PCModelProviderFactory.modelProvider()
			.forEach(pack::addProvider);

		PCRecipesProviderFactory.recipesProvider()
			.forEach(pack::addProvider);

		PCTagProviderFactory.allTagProviderFactories()
			.forEach(pack::addProvider);

		PCLootTableProviderFactory.allLootTableProviderFactories()
			.forEach(pack::addProvider);

		PCAdvancementProviderFactory.createFor(
			PCAdvancementTabContainer.instance()
				.addAdvancementTab(ModTabAdvancement::new)
		)
			.forEach(pack::addProvider);
	}
}
