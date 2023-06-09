package com.github.stellarwitch7.earthguard.client.entity.boss;

import com.github.stellarwitch7.earthguard.entity.boss.ValkatrosEntity;
import com.github.stellarwitch7.earthguard.util.GeckoModel;
import com.github.stellarwitch7.earthguard.util.ModelType;
import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ValkatrosEntityRenderer extends GeoEntityRenderer<ValkatrosEntity> {
	public ValkatrosEntityRenderer(EntityRendererFactory.Context ctx) {
		super(ctx, new GeckoModel<>("valkatros", ModelType.BOSS));
	}
}
