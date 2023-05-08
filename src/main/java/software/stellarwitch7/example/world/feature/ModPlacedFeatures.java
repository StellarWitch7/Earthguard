package software.stellarwitch7.example.world.feature;

import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class ModPlacedFeatures {
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