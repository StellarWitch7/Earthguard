package com.github.stellarwitch7.earthguard.mixin;

import com.github.stellarwitch7.earthguard.EarthguardMod;
import com.github.stellarwitch7.earthguard.registry.ModEffects;
import com.github.stellarwitch7.earthguard.util.IPlayerEntityAccessor;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
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
	private float blockedDamage = 0;
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
	private void earthguard$applyBlockedDamage(CallbackInfo info) {
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
				this.applyDamage(DamageSource.MAGIC, blockedDamage);
				blockedDamage = 0;
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
	public void earthguard$setLycanStatus(boolean isLycan) {
		this.isLycan = isLycan;
	}
	
	@Override
	public float earthguard$getBlockedDamage() {
		return blockedDamage;
	}
	@Override
	public boolean earthguard$getLycanStatus() {
		return isLycan;
	}
	
	@Shadow
	protected abstract void applyDamage(DamageSource source, float amount);
	
	@Shadow
	public abstract ItemStack getEquippedStack(EquipmentSlot slot);
}