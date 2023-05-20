package io.github.stellarwitch7.earthguard.client.entity.armour;

import io.github.stellarwitch7.earthguard.EarthguardMod;
import io.github.stellarwitch7.earthguard.item.armour.BonemailArmourItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BonemailArmourModel extends AnimatedGeoModel<BonemailArmourItem> {
	@Override
	public Identifier getModelResource(BonemailArmourItem object) {
		return new Identifier(EarthguardMod.MOD_ID, "geo/bonemail_armour.geo.json");
	}
	
	@Override
	public Identifier getTextureResource(BonemailArmourItem object) {
		return new Identifier(EarthguardMod.MOD_ID, "textures/armour/bonemail_armour.png");
	}
	
	@Override
	public Identifier getAnimationResource(BonemailArmourItem animatable) {
		return new Identifier(EarthguardMod.MOD_ID, "animations/armour_animation.json");
	}
}