package com.github.stellarwitch7.earthguard.entity.boss;

import com.github.stellarwitch7.earthguard.EarthguardMod;
import com.github.stellarwitch7.earthguard.util.BossPhase;
import com.mojang.serialization.DataResult;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtOps;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Random;

/*
Valkatros, deity of death and destruction.
Fire, explosions, raw chaos
Phase one: human-like, on the ground, short-range attacks
Phase two: exposed core, in the air, long ranged attacks
Lightning, explosive projectiles
*/

public class ValkatrosEntity extends HostileEntity implements RangedAttackMob, IAnimatable {
	private AnimationFactory factory = new AnimationFactory(this);
	private BossPhase bossPhase = BossPhase.ONE;
	private boolean isDashing = false;
	private boolean approachTarget = false;
	private double dashDistance = 20.0d;
	
	protected ValkatrosEntity(EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
	}
	
	@Override
	protected void initGoals() {
		this.goalSelector.add(2, new ProjectileAttackGoal(this, 1.0, 40, 20.0f));
		this.goalSelector.add(5, new FlyGoal(this, 1.0));
		this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
		this.goalSelector.add(7, new LookAroundGoal(this));
		
		this.targetSelector.add(2, new ActiveTargetGoal<>(this, LivingEntity.class, true));
	}
	
	@Override
	public void attack(LivingEntity target, float pullProgress) {
		this.attackSelector(target);
	}
	
	private void attackSelector(LivingEntity target) {
		Random random = new Random();
		
		if (bossPhase == BossPhase.ONE) {
			if (dashDistance > this.getPos().distanceTo(target.getPos())) {
				this.dashAttack(target);
			} else {
				approachTarget = true;
			}
		} else if (bossPhase == BossPhase.TWO) {
			if (random.nextBoolean()) {
				this.launchChaosProjectile(target);
			} else {
				this.callLightning(target);
			}
		}
	}
	
	private void dashAttack(LivingEntity target) {
		this.setVelocity(target.getPos().multiply(2));
		isDashing = true;
	}
	
	private void launchChaosProjectile(LivingEntity target) {
	
	}
	
	private void callLightning(LivingEntity target) {
		World world = this.getWorld();
		LightningEntity lightningBolt = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
		lightningBolt.setPosition(target.getPos());
		world.spawnEntity(lightningBolt);
	}
	
	@Override
	public void writeCustomDataToNbt(NbtCompound nbt) {
		super.writeCustomDataToNbt(nbt);
		
		DataResult<NbtElement> value =
				BossPhase.CODEC.encodeStart(NbtOps.INSTANCE,
						bossPhase);
		NbtElement element = value.resultOrPartial(EarthguardMod.LOGGER::error).orElseThrow();
		nbt.put("bossPhase", element);
	}
	
	@Override
	public void readCustomDataFromNbt(NbtCompound nbt) {
		super.readCustomDataFromNbt(nbt);
		
		NbtElement element = nbt.get("bossPhase");
		DataResult<BossPhase> value = BossPhase.CODEC.parse(NbtOps.INSTANCE, element);
		bossPhase = value.resultOrPartial(EarthguardMod.LOGGER::error).orElseThrow();
	}
	
	@Override
	public boolean handleFallDamage(float fallDistance, float damageMultiplier,
									DamageSource damageSource) {
		return false;
	}
	
	@Override
	public boolean addStatusEffect(StatusEffectInstance effect, @Nullable Entity source) {
		return false;
	}
	
	@Override
	public boolean canUsePortals() {
		return false;
	}
	
	@Override
	public void registerControllers(AnimationData animationData) {
	
	}
	
	@Override
	public AnimationFactory getFactory() {
		return factory;
	}
}
