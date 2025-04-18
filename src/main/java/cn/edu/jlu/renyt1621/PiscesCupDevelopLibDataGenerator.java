package cn.edu.jlu.renyt1621;

import cn.edu.jlu.renyt1621.datagen.factorys.PCLanguageProviderFactory;
import cn.edu.jlu.renyt1621.datagen.factorys.PCModelProviderFactory;
import cn.edu.jlu.renyt1621.datagen.factorys.PCRecipesProviderFactory;
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

	}
}
