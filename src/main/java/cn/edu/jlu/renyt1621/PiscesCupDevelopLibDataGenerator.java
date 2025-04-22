package cn.edu.jlu.renyt1621;

import cn.edu.jlu.renyt1621.datagen.factories.PCLanguageProviderFactory;
import cn.edu.jlu.renyt1621.datagen.factories.PCModelProviderFactory;
import cn.edu.jlu.renyt1621.datagen.factories.PCRecipesProviderFactory;
import cn.edu.jlu.renyt1621.datagen.factories.PCTagProviderFactory;
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

		PCTagProviderFactory.ITEM_TAG_PROVIDER.getFactories()
			.forEach(pack::addProvider);

		PCTagProviderFactory.BLOCK_TAG_PROVIDER.getFactories()
			.forEach(pack::addProvider);

	}
}
