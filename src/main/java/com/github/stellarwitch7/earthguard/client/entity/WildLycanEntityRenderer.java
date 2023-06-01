package com.github.stellarwitch7.earthguard.client.entity;

import com.github.stellarwitch7.earthguard.entity.WildLycanEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class WildLycanEntityRenderer extends GeoEntityRenderer<WildLycanEntity> {
	public WildLycanEntityRenderer(EntityRendererFactory.Context ctx) {
		super(ctx, new WildLycanEntityModel());
	}
}