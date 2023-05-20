package io.github.stellarwitch7.earthguard.world.feature;

import io.github.stellarwitch7.earthguard.EarthguardMod;
import io.github.stellarwitch7.earthguard.registry.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;

import java.util.List;

public class ModConfiguredFeatures {
	private static final List<OreFeatureConfig.Target> OVERWORLD_LIMESTONE_ORES =
			List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES,
							ModBlocks.LIMESTONE_BLOCK.getDefaultState()),
					OreFeatureConfig.createTarget(new BlockMatchRuleTest(Blocks.ANDESITE),
							ModBlocks.LIMESTONE_BLOCK.getDefaultState()),
					OreFeatureConfig.createTarget(new BlockMatchRuleTest(Blocks.DIORITE),
							ModBlocks.LIMESTONE_BLOCK.getDefaultState()),
					OreFeatureConfig.createTarget(new BlockMatchRuleTest(Blocks.GRANITE),
							ModBlocks.LIMESTONE_BLOCK.getDefaultState()),
					OreFeatureConfig.createTarget(new BlockMatchRuleTest(Blocks.GRAVEL),
							ModBlocks.LIMESTONE_BLOCK.getDefaultState()),
					OreFeatureConfig.createTarget(new BlockMatchRuleTest(Blocks.DIRT),
							ModBlocks.LIMESTONE_BLOCK.getDefaultState()));
	public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> LIMESTONE_ORE =
			ConfiguredFeatures.register("limestone_ore", Feature.ORE,
					new OreFeatureConfig(OVERWORLD_LIMESTONE_ORES, 9));
	private static final List<OreFeatureConfig.Target> OVERWORLD_AVRILLIUM_ORES =
			List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES,
					ModBlocks.AVRILLIUM_ORE.getDefaultState()));
	public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> AVRILLIUM_ORE =
			ConfiguredFeatures.register("avrillium_ore", Feature.ORE,
					new OreFeatureConfig(OVERWORLD_AVRILLIUM_ORES, 5));
	private static final List<OreFeatureConfig.Target> NETHER_CRYSTALLINE_ORES =
			List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.NETHERRACK,
					ModBlocks.CRYSTALLINE_ESSENCE_BLOCK.getDefaultState()));
	public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> CRYSTALLINE_ORE =
			ConfiguredFeatures.register("crystalline_ore", Feature.ORE,
					new OreFeatureConfig(NETHER_CRYSTALLINE_ORES, 4));
	
	public static void register() {
		EarthguardMod.LOGGER.info("Registering configured features");
	}
}
