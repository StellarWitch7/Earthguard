package com.github.stellarwitch7.earthguard.effect;

import com.github.stellarwitch7.earthguard.util.SpecialValues;
import com.github.stellarwitch7.earthguard.util.accessor.IPlayerEntityAccessor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
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
			int ticksToAdd = 1;
			
			if ((accessor.earthguard$getBlockedDamage() + 2) / 2 > ticksToAdd) {
				ticksToAdd = (int)(Math.ceil(accessor.earthguard$getBlockedDamage() + 2) / 2);
			}
			
			ticksPassedSinceLastDrain += ticksToAdd;
			
			if (ticksPassedSinceLastDrain >= ticksBetweenDrains
					&& accessor.earthguard$getBlockedDamage() > 0) {
				entity.playSound(SoundEvents.ENTITY_CAT_HISS, 1.0f, 1.0f);
				accessor.earthguard$decrementHealth();
				accessor.earthguard$decrementBlockedDamage();
				ticksPassedSinceLastDrain = 0;
			}
		}
	}
	
	@Override
	public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
		if (entity instanceof PlayerEntity player) {
			var accessor = (IPlayerEntityAccessor)player;
			
			if (player.getHealth() - accessor.earthguard$getBlockedDamage() > 0) {
				player.setHealth(player.getHealth() - accessor.earthguard$getBlockedDamage());
			} else {
				player.setHealth(1.0f);
			}
			
			accessor.earthguard$setBlockedDamage(0.0f);
		}
		
		super.onRemoved(entity, attributes, amplifier);
	}
}
