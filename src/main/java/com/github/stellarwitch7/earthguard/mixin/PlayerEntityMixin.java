package com.github.stellarwitch7.earthguard.mixin;

import com.github.stellarwitch7.earthguard.EarthguardMod;
import com.github.stellarwitch7.earthguard.registry.ModEffects;
import com.github.stellarwitch7.earthguard.util.accessor.IPlayerEntityAccessor;
import com.github.stellarwitch7.earthguard.util.LycanForm;
import com.github.stellarwitch7.earthguard.util.SpecialValues;
import com.mojang.serialization.DataResult;
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
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtOps;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Debug(export = true)
@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin
		extends LivingEntity
		implements IPlayerEntityAccessor {
	@Shadow
	public abstract ItemStack getEquippedStack(EquipmentSlot slot);
	@Shadow
	public abstract HungerManager getHungerManager();
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
	
	protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType,
								World world) {
		super(entityType, world);
	}
	
	@Inject(method = "applyDamage", at = @At("HEAD"), cancellable = true)
	private void earthguard$blockDamage(DamageSource source, float amount,
										CallbackInfo ci) {
		if (this.hasStatusEffect(ModEffects.FURGUARD)) {
			if (!source.bypassesArmor() && !source.isFire()
					&& !source.isFromFalling() && !source.isMagic()) {
				float damageCounted = 1;
				
				if (amount / this.getStatusEffect(ModEffects.FURGUARD)
						.getAmplifier() > damageCounted) {
					damageCounted = amount / this.getStatusEffect(ModEffects.FURGUARD)
							.getAmplifier();
				}
				
				blockedDamage += damageCounted;
				ci.cancel();
			}
		}
	}
	
	@Inject(method = "tick", at = @At("TAIL"))
	private void earthguard$tick(CallbackInfo info) {
		if (this.isAlive()) {
			if (Objects.equals(lycanForm.getId(), LycanForm.MONSTER.getId())) {
				this.addStatusEffect(new StatusEffectInstance(ModEffects.FURGUARD,
						60, this.getHungerManager().getFoodLevel() / 4));
				this.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH,
						60, (int)(this.getMaxHealth() - this.getHealth()) / 4));
				earthguard$setMonsterFormTime(earthguard$getMonsterFormTime() + 1);
				
				if (earthguard$getMonsterFormTime() > monsterFormLength) {
					earthguard$setLycanForm(LycanForm.HUMAN);
				}
			}
		}
	}
	
	@Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
	private void earthguard$readCustomDataFromNbt(NbtCompound nbt,
												  CallbackInfo info) {
		earthguard$setLycanStatus(nbt.getBoolean("isLycan"));
		EarthguardMod.LOGGER.info("Reading NBT: isLycan = " + isLycan); //Debug
		
		earthguard$setMonsterFormTime(nbt.getInt("monsterFormTicks"));
		EarthguardMod.LOGGER.info("Reading NBT: monsterFormTicks = "
				+ ticksPassedAsMonster
				+ " of " + monsterFormLength);
		
		NbtElement element = nbt.get("lycanForm");
		DataResult<LycanForm> value = LycanForm.CODEC.parse(NbtOps.INSTANCE, element);
		lycanForm = value.resultOrPartial(EarthguardMod.LOGGER::error).orElseThrow();
		EarthguardMod.LOGGER.info("Reading NBT: lycanForm = "
				+ lycanForm.getId()); //Debug
	}
	
	@Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
	private void earthguard$writeCustomDataToNbt(NbtCompound nbt,
												 CallbackInfo info) {
		nbt.putBoolean("isLycan", isLycan);
		EarthguardMod.LOGGER.info("Writing NBT: isLycan = " + isLycan); //Debug
		
		nbt.putInt("monsterFormTicks", ticksPassedAsMonster);
		EarthguardMod.LOGGER.info("Writing NBT: monsterFormTicks = "
				+ ticksPassedAsMonster
				+ " of " + monsterFormLength);
		
		DataResult<NbtElement> value =
				LycanForm.CODEC.encodeStart(NbtOps.INSTANCE,
						lycanForm);
		var element = value.resultOrPartial(EarthguardMod.LOGGER::error).orElseThrow();
		nbt.put("lycanForm", element);
		EarthguardMod.LOGGER.info("Writing NBT: lycanForm = "
				+ lycanForm.getId()); //Debug
	}
	
	public void earthguard$decrementHealth() {
		this.setHealth(this.getHealth() - 1.0f);
	}
	
	@Override
	public float earthguard$getBlockedDamage() {
		return blockedDamage;
	}
	
	@Override
	public void earthguard$decrementBlockedDamage() {
		blockedDamage--;
	}
	
	@Override
	public void earthguard$incrementBlockedDamage() {
		blockedDamage++;
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
		if (newForm.getId() == LycanForm.HUMAN.getId()) {
			this.removeStatusEffect(StatusEffects.NIGHT_VISION);
			lycanForm = newForm;
			return true;
		}
		
		if (isLycan) {
			if (lycanForm == LycanForm.MONSTER
					&& newForm != LycanForm.MONSTER) {
				this.removeStatusEffect(StatusEffects.HEALTH_BOOST);
				this.removeStatusEffect(StatusEffects.REGENERATION);
				earthguard$setMonsterFormTime(0);
			} else if (newForm == LycanForm.MONSTER
					&& lycanForm != LycanForm.MONSTER) {
				this.addStatusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST,
						SpecialValues.BIG_INT, 4));
				this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION,
						SpecialValues.BIG_INT));
				earthguard$setMonsterFormTime(0);
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
	
	public int earthguard$getMonsterFormTime() {
		return ticksPassedAsMonster;
	}
	public void earthguard$setMonsterFormTime(int ticks) {
		ticksPassedAsMonster = ticks;
	}
}