package io.github.stellarwitch7.earthguard.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;

public class PeacekeeperStatusEffect extends StatusEffect {
	public PeacekeeperStatusEffect() {
		super(StatusEffectCategory.BENEFICIAL, 0x98D982);
	}
	
	@Override
	public void applyUpdateEffect(LivingEntity entity, int amplifier) {
		if (entity instanceof PlayerEntity) {
		
		}
	}
	
	@Override
	public boolean canApplyUpdateEffect(int duration, int amplifier) {
		return true;
	}
}
