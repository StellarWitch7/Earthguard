package software.stellarwitch7.example;

import software.stellarwitch7.example.datagen.ModLanguageGenerator;
import software.stellarwitch7.example.datagen.ModLootGenerator;
import software.stellarwitch7.example.datagen.ModModelGenerator;
import software.stellarwitch7.example.datagen.ModRecipeGenerator;
import software.stellarwitch7.example.registry.ModRegistry;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class ExampleDataGenerator implements DataGeneratorEntrypoint {
	public static FabricDataGenerator fabricDataGenerator;
	
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator dataGenerator) {
		fabricDataGenerator = dataGenerator;
		ModRegistry.datagenCleanup();
		ExampleMod.LOGGER.info("Generating data files");
		ModLanguageGenerator.generate();
		ModLootGenerator.generate();
		ModRecipeGenerator.generate();
		ModModelGenerator.generate();
	}
}