package cn.edu.jlu.renyt1621;

import cn.edu.jlu.renyt1621.tests.blocks.ModBlocks;
import cn.edu.jlu.renyt1621.tests.itemgroups.ModItemGroups;
import cn.edu.jlu.renyt1621.tests.items.ModItems;
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

		System.out.println(ModItems.ITEM1.getTranslationKey());
		System.out.println(ModBlocks.BLOCK.getTranslationKey());
		System.out.println(ModItems.BLOCK_ITEM.getTranslationKey());
		System.out.println(Items.WAXED_COPPER_GRATE.getTranslationKey());

	}
}