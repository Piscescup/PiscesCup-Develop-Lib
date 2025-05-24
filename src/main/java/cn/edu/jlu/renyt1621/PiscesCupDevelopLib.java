package cn.edu.jlu.renyt1621;

import cn.edu.jlu.renyt1621.tests.advancements.ModAdvancements;
import cn.edu.jlu.renyt1621.tests.blocks.ModBlocks;
import cn.edu.jlu.renyt1621.tests.itemgroups.ModItemGroups;
import cn.edu.jlu.renyt1621.tests.items.ModItems;
import cn.edu.jlu.renyt1621.tests.tags.ModTags;
import net.fabricmc.api.ModInitializer;

import net.minecraft.item.Items;

import static cn.edu.jlu.renyt1621.References.*;

public class PiscesCupDevelopLib implements ModInitializer {
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		MOD_LOGGER.info("Hello Fabric world!");
		MOD_LOGGER.info("Hello, " + MOD_NAME);

		ModItems.register();
		ModBlocks.register();
		ModItemGroups.register();
		ModTags.register();
		ModAdvancements.register();

	}
}