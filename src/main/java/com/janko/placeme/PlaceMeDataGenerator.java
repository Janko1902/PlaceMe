package com.janko.placeme;

import com.janko.placeme.datagen.ModLangProvider;
import com.janko.placeme.datagen.ModLootTableProvider;
import com.janko.placeme.datagen.ModModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class PlaceMeDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
				pack.addProvider(ModModelProvider::new);
				pack.addProvider(ModLootTableProvider::new);
				pack.addProvider(ModLangProvider::new);
	}
}
