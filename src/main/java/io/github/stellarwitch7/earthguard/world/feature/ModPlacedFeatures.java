package io.github.stellarwitch7.earthguard.world.feature;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class ModPlacedFeatures {
	//Create new placed ores here
	public static final RegistryEntry<PlacedFeature> LIMESTONE_ORE_PLACED =
			PlacedFeatures.register("limestone_ore_placed",
					ModConfiguredFeatures.LIMESTONE_ORE, modifiersWithCount(9,
							HeightRangePlacementModifier.trapezoid(YOffset.fixed(-80),
									YOffset.fixed(80))));
	public static final RegistryEntry<PlacedFeature> AVRILLIUM_ORE_PLACED =
			PlacedFeatures.register("avrillium_ore_placed",
					ModConfiguredFeatures.AVRILLIUM_ORE, modifiersWithCount(5,
							HeightRangePlacementModifier.trapezoid(YOffset.fixed(-80),
									YOffset.fixed(25))));
	public static final RegistryEntry<PlacedFeature> CRYSTALLINE_ORE_PLACED =
			PlacedFeatures.register("crystalline_ore_placed",
					ModConfiguredFeatures.CRYSTALLINE_ORE, modifiersWithCount(4,
							HeightRangePlacementModifier.uniform(YOffset.fixed(-90),
									YOffset.fixed(90))));
	
	//NO TOUCHY
	//Important code for ore gen
	private static List<PlacementModifier> modifiers(PlacementModifier countModifier,
													 PlacementModifier heightModifier) {
		return List.of(countModifier,
				SquarePlacementModifier.of(),
				heightModifier,
				BiomePlacementModifier.of());
	}
	
	private static List<PlacementModifier> modifiersWithCount(int count,
															  PlacementModifier heightModifier) {
		return modifiers(CountPlacementModifier.of(count),
				heightModifier);
	}
	
	private static List<PlacementModifier> modifiersWithRarity(int chance,
															   PlacementModifier heightModifier) {
		return modifiers(RarityFilterPlacementModifier.of(chance),
				heightModifier);
	}
}