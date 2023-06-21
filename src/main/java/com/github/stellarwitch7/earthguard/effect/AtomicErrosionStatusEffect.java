package com.github.stellarwitch7.earthguard.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

import java.util.Random;

public class AtomicErrosionStatusEffect extends StatusEffect {
	public AtomicErrosionStatusEffect() {
		super(StatusEffectCategory.HARMFUL, 0x98D982);
	}
	
	@Override
	public boolean canApplyUpdateEffect(int duration, int amplifier) {
		if (new Random().nextBoolean()) {
			return new Random().nextBoolean();
		}
		
		return false;
	}
	
	@Override
	public void applyUpdateEffect(LivingEntity entity, int amplifier) {
		if (entity instanceof PlayerEntity player) {
			if (amplifier <= 0) {
				amplifier = 1;
			}
			
			double tweakedAmplifier = amplifier * -0.5;
			Vec3d velocity = player.getVelocity();
			
			player.addExperience((int)(tweakedAmplifier * 20));
			player.addVelocity(velocity.x * tweakedAmplifier,
					0,
					velocity.z * tweakedAmplifier);
			player.damage(DamageSource.WITHER, amplifier * 2);
		}
	}
}
