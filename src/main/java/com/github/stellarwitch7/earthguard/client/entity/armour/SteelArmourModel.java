package com.github.stellarwitch7.earthguard.client.entity.armour;

import com.github.stellarwitch7.earthguard.EarthguardMod;
import com.github.stellarwitch7.earthguard.item.armour.SteelArmourItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SteelArmourModel extends AnimatedGeoModel<SteelArmourItem> {
	@Override
	public Identifier getModelResource(SteelArmourItem object) {
		return new Identifier(EarthguardMod.MOD_ID, "geo/steel_armour.geo.json");
	}
	
	@Override
	public Identifier getTextureResource(SteelArmourItem object) {
		return new Identifier(EarthguardMod.MOD_ID, "textures/armour/steel_armour.png");
	}
	
	@Override
	public Identifier getAnimationResource(SteelArmourItem object) {
		return new Identifier(EarthguardMod.MOD_ID, "animations/armour.animation.json");
	}
}
