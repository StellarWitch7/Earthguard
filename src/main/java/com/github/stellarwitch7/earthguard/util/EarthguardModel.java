package com.github.stellarwitch7.earthguard.util;

import com.github.stellarwitch7.earthguard.EarthguardMod;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class EarthguardModel<T extends IAnimatable> extends AnimatedGeoModel<T> {
	private final String id;
	private final ModelType modelType;
	
	public EarthguardModel(String id, ModelType modelType) {
		super();
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
		return new Identifier(EarthguardMod.MOD_ID, "animations/" + id + ".animation.json");
	}
}
