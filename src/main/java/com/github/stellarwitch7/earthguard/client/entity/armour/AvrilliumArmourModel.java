package com.github.stellarwitch7.earthguard.client.entity.armour;

import com.github.stellarwitch7.earthguard.EarthguardMod;
import com.github.stellarwitch7.earthguard.item.armour.AvrilliumArmourItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AvrilliumArmourModel extends AnimatedGeoModel<AvrilliumArmourItem> {
	@Override
	public Identifier getModelResource(AvrilliumArmourItem object) {
		return new Identifier(EarthguardMod.MOD_ID, "geo/avrillium_armour.geo.json");
	}
	
	@Override
	public Identifier getTextureResource(AvrilliumArmourItem object) {
		return new Identifier(EarthguardMod.MOD_ID, "textures/armour/avrillium_armour.png");
	}
	
	@Override
	public Identifier getAnimationResource(AvrilliumArmourItem animatable) {
		return new Identifier(EarthguardMod.MOD_ID, "animations/armour_animation.json");
	}
}
