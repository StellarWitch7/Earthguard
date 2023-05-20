package com.github.stellarwitch7.earthguard.world.gen;

import com.github.stellarwitch7.earthguard.EarthguardMod;
import com.github.stellarwitch7.earthguard.world.feature.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;

public class ModOreGeneration {
	public static void generate() {
		EarthguardMod.LOGGER.info("Generating ores");
		//Generate ores here
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
				GenerationStep.Feature.UNDERGROUND_ORES,
				ModPlacedFeatures.LIMESTONE_ORE_PLACED.getKey().get());
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
				GenerationStep.Feature.UNDERGROUND_ORES,
				ModPlacedFeatures.AVRILLIUM_ORE_PLACED.getKey().get());
		BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(),
				GenerationStep.Feature.UNDERGROUND_ORES,
				ModPlacedFeatures.CRYSTALLINE_ORE_PLACED.getKey().get());
	}
}