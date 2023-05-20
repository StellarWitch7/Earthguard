package io.github.stellarwitch7.earthguard;

import io.github.stellarwitch7.earthguard.datagen.ModLanguageGenerator;
import io.github.stellarwitch7.earthguard.datagen.ModLootGenerator;
import io.github.stellarwitch7.earthguard.datagen.ModModelGenerator;
import io.github.stellarwitch7.earthguard.datagen.ModRecipeGenerator;
import io.github.stellarwitch7.earthguard.registry.ModRegistry;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class EarthguardDataGenerator implements DataGeneratorEntrypoint {
	public static FabricDataGenerator fabricDataGenerator;
	
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator dataGenerator) {
		fabricDataGenerator = dataGenerator;
		ModRegistry.datagenCleanup();
		EarthguardMod.LOGGER.info("Generating data files");
		ModLanguageGenerator.generate();
		ModLootGenerator.generate();
		ModRecipeGenerator.generate();
		ModModelGenerator.generate();
	}
}