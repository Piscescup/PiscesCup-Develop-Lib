package cn.edu.jlu.renyt1621;

import cn.edu.jlu.renyt1621.deprecated.annotations.scanners.datagen.lang.LangMap;
import cn.edu.jlu.renyt1621.datagen.lang.PCDLLanguageProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.data.DataProvider;

public class PiscesCupDevelopLibDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		LangMap.instance().get()
			.forEach(
				(lang, langMap) -> pack.addProvider(
					(FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider>)
						(output, lookUp) ->
							PCDLLanguageProvider.Builder.create()
								.dataOutput(output)
								.lang(lang)
								.langMap(langMap)
								.name("Language" + lang.name())
								.registryLookup(lookUp)
								.build()
				)
			);
	}
}
