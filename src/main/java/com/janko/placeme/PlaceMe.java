package com.janko.placeme;

import com.janko.placeme.block.ModBlockEntities;
import com.janko.placeme.block.ModBlocks;
import com.janko.placeme.item.ModItems;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlaceMe implements ModInitializer {
	public static final String MOD_ID = "placeme";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}

	@Override
	public void onInitialize() {
		ModBlocks.registerBlocks();
		ModItems.registerModItems();
		ModBlockEntities.registerBlockEntities();
	}
}