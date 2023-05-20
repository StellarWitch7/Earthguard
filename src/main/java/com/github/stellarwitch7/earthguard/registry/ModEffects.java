package com.github.stellarwitch7.earthguard.registry;

import com.github.stellarwitch7.earthguard.effect.FurguardStatusEffect;
import com.github.stellarwitch7.earthguard.effect.PeacekeeperStatusEffect;
import net.minecraft.entity.effect.StatusEffect;

public class ModEffects {
	//Create new effects here
	public static final StatusEffect PEACEKEEPER =
			ModRegistry.createEffect("Peacekeeper", new PeacekeeperStatusEffect());
	public static final StatusEffect FURGUARD =
			ModRegistry.createEffect("Furguard", new FurguardStatusEffect());
	
	//Call this to load the effects
	public static void load() {}
}
