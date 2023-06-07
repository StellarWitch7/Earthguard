package com.github.stellarwitch7.earthguard.util;

import com.github.stellarwitch7.earthguard.EarthguardMod;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GeckoModel<T extends IAnimatable> extends AnimatedGeoModel<T> {
	private final boolean noAnimation;
	private final String id;
	private final ModelType modelType;
	
	public GeckoModel(String id, ModelType modelType) {
		super();
		this.noAnimation = false;
		this.id = id;
		this.modelType = modelType;
	}
	
	public GeckoModel(String id, ModelType modelType, boolean hasNoAnimation) {
		super();
		this.noAnimation = hasNoAnimation;
		this.id = id;
		this.modelType = modelType;
	}
	
	@Override
	public Identifier getModelResource(T object) {
		return new Identifier(EarthguardMod.MOD_ID, "geo/" + id + ".geo.json");
	}
	
	@Override
	public Identifier getTextureResource(T object) {
		return new Identifier(EarthguardMod.MOD_ID, "textures/" + modelType.getPath() + id + ".png");
	}
	
	@Override
	public Identifier getAnimationResource(T object) {
		if (noAnimation) {
			return new Identifier(EarthguardMod.MOD_ID, "animations/empty.animation.json");
		} else {
			return new Identifier(EarthguardMod.MOD_ID, "animations/" + id + ".animation.json");
		}
	}
}
