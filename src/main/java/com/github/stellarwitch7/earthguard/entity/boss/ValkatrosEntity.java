package com.github.stellarwitch7.earthguard.entity.boss;

import com.github.stellarwitch7.earthguard.EarthguardMod;
import com.github.stellarwitch7.earthguard.util.BossPhase;
import com.github.stellarwitch7.earthguard.util.SpecialValues;
import com.mojang.serialization.DataResult;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtOps;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
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
	private final ServerBossBar bossBar;
	private final double dashDistance = 20.0d;
	private final int dashLength = (int)(SpecialValues.TICK_SECOND * 2.5);
	private final float dashDamage = 10.0f;
	private final double dashSpeed = 2.0d;
	private final int lightningDelay = SpecialValues.TICK_SECOND * 3;
	private AnimationFactory factory = new AnimationFactory(this);
	private BossPhase bossPhase = BossPhase.ONE;
	private boolean approachTarget = false;
	private boolean transitioning = false;
	private boolean isDashing = false;
	private int dashTimer = 0;
	private LivingEntity dashTarget;
	private boolean callingLightning = false;
	private int lightningTimer = 0;
	private Vec3d lightningTargetPos;
	
	public ValkatrosEntity(EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
		this.bossBar = new ServerBossBar(this.getDisplayName(),
				BossBar.Color.RED,
				BossBar.Style.PROGRESS);
	}
	
	
	
	@Override
	public void initGoals() {
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
		dashTarget = target;
		dashTimer = dashLength;
		isDashing = true;
	}
	
	private boolean dashLogic(LivingEntity target) {
		if (dashTimer > 0) {
			dashTimer--;
		} else {
			return false;
		}
		
		if (this.collidesWith(target)) {
			target.damage(DamageSource.CRAMMING, dashDamage);
			return false;
		}
		
		this.setVelocity(this.getPos().subtract(target.getPos()).normalize().multiply(dashSpeed));
		return true;
	}
	
	private void launchChaosProjectile(LivingEntity target) {
	
	}
	
	private void callLightning(LivingEntity target) {
		/*
		Add logic here to summon an entity at the position of the target
		that will simply be a marker to let player's know where the next strike
		will be.
		*/
		
		lightningTargetPos = target.getPos();
		lightningTimer = lightningDelay;
		callingLightning = true;
	}
	
	private boolean lightningLogic(Vec3d targetPos) {
		if (lightningTimer > 0) {
			lightningTimer--;
		} else {
			World world = this.getWorld();
			LightningEntity lightningBolt = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
			lightningBolt.setPosition(targetPos);
			world.spawnEntity(lightningBolt);
			
			return false;
		}
		
		return true;
	}
	
	@Override
	public void tick() {
		super.tick();
		bossBar.setPercent(this.getHealth());
		
		if (isDashing) {
			isDashing = dashLogic(dashTarget);
		}
		
		if (callingLightning) {
			callingLightning = lightningLogic(lightningTargetPos);
		}
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
	
	private PlayState predicate(AnimationEvent event) {
		var animations = new AnimationBuilder();
		
		if (isDashing && bossPhase == BossPhase.ONE) {
			animations.addAnimation("animation.valkatros.dash");
			event.getController().setAnimation(animations);
			return PlayState.CONTINUE;
		}
		
		//Idle
		if (bossPhase == BossPhase.TWO) {
			if (transitioning) {
				animations.addAnimation("animation.valkatros.transition", true);
			} else {
				animations.addAnimation("animation.valkatros.phase_two_idle", true);
			}
		} else if (bossPhase == BossPhase.ONE) {
			animations.addAnimation("animationg.valkatros.phase_one_idle", true);
		}
		
		event.getController().setAnimation(animations);
		return PlayState.CONTINUE;
	}
	
	@Override
	public void registerControllers(AnimationData animationData) {
		animationData.addAnimationController(new AnimationController(this, "controller",
				0, this::predicate));
	}
	
	@Override
	public AnimationFactory getFactory() {
		return factory;
	}
}
