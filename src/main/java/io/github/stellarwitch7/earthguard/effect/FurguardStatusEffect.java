package io.github.stellarwitch7.earthguard.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.sound.SoundEvents;

public class FurguardStatusEffect extends StatusEffect {
	private int ticker = 0;
	public FurguardStatusEffect() {
		super(
				StatusEffectCategory.BENEFICIAL,
				0x98D982);
	}
	
	@Override
	public boolean canApplyUpdateEffect(int duration, int amplifier) {
		ticker = ticker <= 0 ? 80 : ticker - 1;
		return ticker <= 0;
	}
	
	@Override
	public void applyUpdateEffect(LivingEntity entity, int amplifier) {
		entity.playSound(SoundEvents.ENTITY_CAT_HISS, 1.0f, 1.0f);
	}
}
