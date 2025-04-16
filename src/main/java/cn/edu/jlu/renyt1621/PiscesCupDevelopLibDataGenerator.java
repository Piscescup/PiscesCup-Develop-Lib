package cn.edu.jlu.renyt1621;

import cn.edu.jlu.renyt1621.datagen.factorys.PCLanguageProviderFactory;
import cn.edu.jlu.renyt1621.datagen.models.PCDLModelProvider;
import cn.edu.jlu.renyt1621.deprecated.annotations.scanners.datagen.lang.LangMap;
import cn.edu.jlu.renyt1621.datagen.lang.PCDLLanguageProvider;
import cn.edu.jlu.renyt1621.utils.constant.Language;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.data.DataProvider;

import java.time.LocalDateTime;
import java.util.List;

public class PiscesCupDevelopLibDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		PCLanguageProviderFactory.languageProvider(Language.EN_US, Language.ZH_CN)
			.forEach(pack::addProvider);
		//  											-------------------- cost 461 ms

	}
}
