package com.github.stellarwitch7.earthguard.client.entity;

import com.github.stellarwitch7.earthguard.EarthguardMod;
import com.github.stellarwitch7.earthguard.entity.WildLycanEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class WildLycanEntityModel extends AnimatedGeoModel<WildLycanEntity> {
	@Override
	public Identifier getModelResource(WildLycanEntity object) {
		return new Identifier(EarthguardMod.MOD_ID,
				"geo/wild_lycan.geo.json");
	}
	
	@Override
	public Identifier getTextureResource(WildLycanEntity object) {
		return new Identifier(EarthguardMod.MOD_ID,
				"textures/entity/wild_lycan.png");
	}
	
	@Override
	public Identifier getAnimationResource(WildLycanEntity animatable) {
		return new Identifier(EarthguardMod.MOD_ID,
				"animations/wild_lycan.animation.json");
	}
}