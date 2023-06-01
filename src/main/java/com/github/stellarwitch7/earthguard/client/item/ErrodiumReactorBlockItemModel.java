package com.github.stellarwitch7.earthguard.client.item;

import com.github.stellarwitch7.earthguard.EarthguardMod;
import com.github.stellarwitch7.earthguard.item.block.ErrodiumReactorBlockItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ErrodiumReactorBlockItemModel extends AnimatedGeoModel<ErrodiumReactorBlockItem> {
	@Override
	public Identifier getModelResource(ErrodiumReactorBlockItem object) {
		return new Identifier(EarthguardMod.MOD_ID, "geo/errodium_reactor.geo.json");
	}
	
	@Override
	public Identifier getTextureResource(ErrodiumReactorBlockItem object) {
		return new Identifier(EarthguardMod.MOD_ID, "textures/block/errodium_reactor.png");
	}
	
	@Override
	public Identifier getAnimationResource(ErrodiumReactorBlockItem object) {
		return new Identifier(EarthguardMod.MOD_ID, "animations/errodium_reactor.animation.json");
	}
}