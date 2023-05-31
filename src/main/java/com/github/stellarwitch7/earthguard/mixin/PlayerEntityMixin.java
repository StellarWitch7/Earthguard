package com.github.stellarwitch7.earthguard.mixin;

import com.github.stellarwitch7.earthguard.EarthguardMod;
import com.github.stellarwitch7.earthguard.registry.ModEffects;
import com.github.stellarwitch7.earthguard.util.IPlayerEntityAccessor;
import com.github.stellarwitch7.earthguard.util.LycanForm;
import com.github.stellarwitch7.earthguard.util.SpecialValues;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Debug(export = true)
@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin
		extends LivingEntity
		implements IPlayerEntityAccessor {
	@Unique
	private boolean isLycan = false;
	@Unique
	private LycanForm lycanForm = LycanForm.HUMAN;
	@Unique
	private float blockedDamage = 0;
	@Unique
	private int monsterFormLength = 16 * SpecialValues.TICK_MINUTE;
	@Unique
	private int ticksPassedAsMonster = 0;
	@Unique
	private int ticksBetweenDrains = 900;
	@Unique
	private int ticksPassedSinceLastDrain = 0;
	
	protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType,
								World world) {
		super(entityType, world);
	}
	
	@Inject(method = "applyDamage", at = @At("HEAD"), cancellable = true)
	private void earthguard$blockDamage(DamageSource source, float amount,
										CallbackInfo info) {
		if (this.hasStatusEffect(ModEffects.FURGUARD)) {
			if (!source.bypassesArmor() && !source.isFire()
					&& !source.isFromFalling() && !source.isMagic()) {
				blockedDamage += amount / this.getStatusEffect(ModEffects.FURGUARD)
						.getAmplifier();
				info.cancel();
			}
		}
	}
	
	@Inject(method = "tick", at = @At("HEAD"))
	private void earthguard$tick(CallbackInfo info) {
		if (this.isAlive()) {
			if (this.hasStatusEffect(ModEffects.FURGUARD)) {
				ticksPassedSinceLastDrain += (blockedDamage + 2) / 2;
				
				if (ticksPassedSinceLastDrain >= ticksBetweenDrains) {
					this.applyDamage(DamageSource.MAGIC,
							blockedDamage - (blockedDamage - 1));
					blockedDamage = blockedDamage < 1 ? 0 : blockedDamage - 1;
					ticksPassedSinceLastDrain = 0;
				}
			} else {
				if (blockedDamage > this.getHealth()) {
					this.applyDamage(DamageSource.MAGIC, this.getHealth() - 1);
				} else {
					this.applyDamage(DamageSource.MAGIC, blockedDamage);
				}
				
				blockedDamage = 0;
			}
			
			if (lycanForm == LycanForm.MONSTER) {
				this.addStatusEffect(new StatusEffectInstance(ModEffects.FURGUARD,
						60, this.getHungerManager().getFoodLevel() / 4));
				this.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH,
						60, (int)(this.getMaxHealth() - this.getHealth()) / 4));
				ticksPassedAsMonster++;
				
				if (ticksPassedAsMonster > monsterFormLength) {
					earthguard$setLycanForm(LycanForm.HUMAN);
				}
			}
		} else {
			blockedDamage = 0;
		}
	}
	
	@Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
	private void earthguard$readCustomDataFromNbt(NbtCompound nbt,
												  CallbackInfo info) {
		isLycan = nbt.getBoolean("isLycan");
		EarthguardMod.LOGGER.info("Reading NBT: isLycan = " + isLycan); //Debug
	}
	
	@Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
	private void earthguard$writeCustomDataToNbt(NbtCompound nbt,
												 CallbackInfo info) {
		nbt.putBoolean("isLycan", isLycan);
		EarthguardMod.LOGGER.info("Writing NBT: isLycan = " + isLycan); //Debug
	}
	
	@Override
	public float earthguard$getBlockedDamage() {
		return blockedDamage;
	}
	
	@Override
	public boolean earthguard$getLycanStatus() {
		return isLycan;
	}
	
	@Override
	public void earthguard$setLycanStatus(boolean newLycanStatus) {
		isLycan = newLycanStatus;
	}
	
	@Override
	public LycanForm earthguard$getLycanForm() {
		return this.lycanForm;
	}
	
	@Override
	public boolean earthguard$setLycanForm(LycanForm newForm) {
		if (newForm == LycanForm.HUMAN) {
			this.removeStatusEffect(StatusEffects.NIGHT_VISION);
			lycanForm = newForm;
			return true;
		}
		
		if (isLycan) {
			if (lycanForm == LycanForm.MONSTER && newForm != LycanForm.MONSTER) {
				this.removeStatusEffect(StatusEffects.HEALTH_BOOST);
				this.removeStatusEffect(StatusEffects.REGENERATION);
				ticksPassedAsMonster = 0;
			} else if (newForm == LycanForm.MONSTER && lycanForm != LycanForm.MONSTER) {
				this.addStatusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST,
						SpecialValues.BIG_INT, 2));
				this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION,
						SpecialValues.BIG_INT));
				ticksPassedAsMonster = 0;
			}
			
			if (newForm != LycanForm.HUMAN) {
				this.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION,
						SpecialValues.BIG_INT));
			}
			
			lycanForm = newForm;
			return true;
		}
		
		return false;
	}
	
	@Shadow
	protected abstract void applyDamage(DamageSource source, float amount);
	@Shadow
	public abstract ItemStack getEquippedStack(EquipmentSlot slot);
	@Shadow
	public abstract HungerManager getHungerManager();
}