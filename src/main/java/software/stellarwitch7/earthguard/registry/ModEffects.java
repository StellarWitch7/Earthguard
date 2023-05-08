package software.stellarwitch7.earthguard.registry;

import software.stellarwitch7.earthguard.effect.PeacekeeperStatusEffect;
import net.minecraft.entity.effect.StatusEffect;

public class ModEffects {
	//Create new effects here
	public static final StatusEffect PEACEKEEPER =
			ModRegistry.createEffect("Peacekeeper", new PeacekeeperStatusEffect());
	
	//Call this to load the effects
	public static void load() {}
}
