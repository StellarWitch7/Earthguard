package com.github.stellarwitch7.earthguard.client.entity;

import com.github.stellarwitch7.earthguard.EarthguardMod;
import com.github.stellarwitch7.earthguard.entity.WildLycanEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class WildLycanEntityRenderer extends GeoEntityRenderer<WildLycanEntity> {
	public WildLycanEntityRenderer(EntityRendererFactory.Context ctx) {
		super(ctx, new WildLycanEntityModel());
	}
	
	@Override
	public Identifier getTextureLocation(WildLycanEntity instance) {
		return new Identifier(EarthguardMod.MOD_ID,
				"textures/entity/wild_lycan.png");
	}
}