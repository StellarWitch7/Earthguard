package com.github.stellarwitch7.earthguard.client.block;

import com.github.stellarwitch7.earthguard.EarthguardMod;
import com.github.stellarwitch7.earthguard.block.ErrodiumReactorBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ErrodiumReactorBlockModel extends AnimatedGeoModel<ErrodiumReactorBlockEntity> {
	@Override
	public Identifier getModelResource(ErrodiumReactorBlockEntity object) {
		return new Identifier(EarthguardMod.MOD_ID, "geo/errodium_reactor.geo.json");
	}
	
	@Override
	public Identifier getTextureResource(ErrodiumReactorBlockEntity object) {
		return new Identifier(EarthguardMod.MOD_ID, "textures/block/errodium_reactor.png");
	}
	
	@Override
	public Identifier getAnimationResource(ErrodiumReactorBlockEntity animatable) {
		return new Identifier(EarthguardMod.MOD_ID, "animations/errodium_reactor.animation.json");
	}
}