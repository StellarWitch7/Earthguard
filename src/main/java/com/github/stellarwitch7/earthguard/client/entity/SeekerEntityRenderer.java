package com.github.stellarwitch7.earthguard.client.entity;

import com.github.stellarwitch7.earthguard.entity.SeekerEntity;
import com.github.stellarwitch7.earthguard.util.GeckoModel;
import com.github.stellarwitch7.earthguard.util.ModelType;
import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SeekerEntityRenderer extends GeoEntityRenderer<SeekerEntity> {
	public SeekerEntityRenderer(EntityRendererFactory.Context ctx) {
		super(ctx, new GeckoModel<>("seeker", ModelType.ENTITY));
	}
}