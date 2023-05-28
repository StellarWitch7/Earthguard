package com.github.stellarwitch7.earthguard.client.entity;

import com.github.stellarwitch7.earthguard.EarthguardMod;
import com.github.stellarwitch7.earthguard.entity.WildLycanEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class WildLycanEntityModel extends AnimatedGeoModel<WildLycanEntity> {
	@Override
	public Identifier getModelResource(WildLycanEntity object) {
		return new Identifier(EarthguardMod.MOD_ID,
				"geo/wild_lycan.geo.json");
	}
	
	@Override
	public Identifier getTextureResource(WildLycanEntity object) {
		return new Identifier(EarthguardMod.MOD_ID,
				"textures/entity/wild_lycan/wild_lycan.png");
	}
	
	@Override
	public Identifier getAnimationResource(WildLycanEntity animatable) {
		return new Identifier(EarthguardMod.MOD_ID,
				"animations/wild_lycan.animation.json");
	}
	
	@Override
	public void setLivingAnimations(WildLycanEntity entity, Integer uniqueID,
									AnimationEvent customPredicate) {
		super.setLivingAnimations(entity, uniqueID, customPredicate);
		IBone head = this.getAnimationProcessor().getBone("head");
		
		EntityModelData extraData = (EntityModelData)
				customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
		if (head != null) {
			head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
			head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
		}
	}
}