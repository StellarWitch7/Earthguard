package com.github.stellarwitch7.earthguard.client.entity.projectile;

import com.github.stellarwitch7.earthguard.entity.projectile.ChaosProjectile;
import com.github.stellarwitch7.earthguard.util.GeckoModel;
import com.github.stellarwitch7.earthguard.util.ModelType;
import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class ChaosProjectileRenderer extends GeoProjectilesRenderer<ChaosProjectile> {
	public ChaosProjectileRenderer(EntityRendererFactory.Context ctx) {
		super(ctx, new GeckoModel<>("chaos_projectile", ModelType.PROJECTILE));
	}
}
