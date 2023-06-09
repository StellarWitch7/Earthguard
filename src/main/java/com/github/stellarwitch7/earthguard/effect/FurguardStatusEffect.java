package com.github.stellarwitch7.earthguard.effect;

import com.github.stellarwitch7.earthguard.util.SpecialValues;
import com.github.stellarwitch7.earthguard.util.accessor.IPlayerEntityAccessor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Unique;

public class FurguardStatusEffect extends StatusEffect {
	private final int ticksBetweenDrains = (int)(SpecialValues.TICK_MINUTE * 0.8);
	private int ticksPassedSinceLastDrain = 0;
	
	public FurguardStatusEffect() {
		super(StatusEffectCategory.BENEFICIAL, 0xa4fcbc);
	}
	
	@Override
	public boolean canApplyUpdateEffect(int duration, int amplifier) {
		return true;
	}
	
	@Override
	public void applyUpdateEffect(LivingEntity entity, int amplifier) {
		if (entity instanceof PlayerEntity player) {
			var accessor = ((IPlayerEntityAccessor)player);
			ticksPassedSinceLastDrain +=
					(accessor.earthguard$getBlockedDamage() + 2) / 2;
			
			if (ticksPassedSinceLastDrain >= ticksBetweenDrains
					&& accessor.earthguard$getBlockedDamage() > 0) {
				entity.playSound(SoundEvents.ENTITY_CAT_HISS, 1.0f, 1.0f);
				player.damage(DamageSource.MAGIC, 1.0f);
				accessor.earthguard$decrementBlockedDamage();
				ticksPassedSinceLastDrain = 0;
			}
		}
	}
}
