package com.github.stellarwitch7.earthguard.entity;

import com.github.stellarwitch7.earthguard.util.SpecialValues;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
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

public class SeekerEntity extends HostileEntity implements RangedAttackMob, IAnimatable {
	private final AnimationFactory factory = new AnimationFactory(this);
	private final double specialAttackDistance = 0.8d;
	private final int drainCooldown = SpecialValues.TICK_SECOND * 4;
	private final int dashLength = SpecialValues.TICK_SECOND;
	private final int dashCooldown = SpecialValues.TICK_SECOND * 8;
	private final double dashSpeed = 1.8d;
	private boolean isDashing = false;
	private int dashTimer = 0;
	private int dashCooldownLeft = 0;
	private LivingEntity dashTarget;
	private Vec3d dashVector;
	private boolean isHealing;
	private int drainCooldownLeft;
	
	public SeekerEntity(EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
		this.moveControl = new FlightMoveControl(this, 10, true);
		this.setHealth(2.0f);
		this.experiencePoints = 2;
		isHealing = true;
	}
	
	@Override
	protected EntityNavigation createNavigation(World world) {
		BirdNavigation birdNavigation = new BirdNavigation(this, world);
		birdNavigation.setCanPathThroughDoors(false);
		birdNavigation.setCanSwim(true);
		birdNavigation.setCanEnterOpenDoors(true);
		return birdNavigation;
	}
	
	public static DefaultAttributeContainer.Builder setAttributes() {
		return HostileEntity.createMobAttributes()
				.add(EntityAttributes.GENERIC_MAX_HEALTH, 32.0D)
				.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 12.0f)
				.add(EntityAttributes.GENERIC_ATTACK_SPEED, 3.5f)
				.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.7f)
				.add(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, 4.0f)
				.add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 3.5f)
				.add(EntityAttributes.GENERIC_FLYING_SPEED, 3.0f)
				.add(EntityAttributes.GENERIC_FOLLOW_RANGE, 30.0f);
	}
	
	@Override
	public void initGoals() {
		this.goalSelector.add(1, new ProjectileAttackGoal(this, 4.5d, 20, 12.0f));
		this.goalSelector.add(2, new FlyGoal(this, 1.5d));
		this.goalSelector.add(3, new LookAtEntityGoal(this, PlayerEntity.class, 16.0f));
		this.goalSelector.add(4, new LookAroundGoal(this));
		
		this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
	}
	
	@Override
	public void attack(LivingEntity target, float pullProgress) {
		if (dashCooldownLeft <= 0) {
			this.beginDashing(target);
		}
	}
	
	private void beginDashing(LivingEntity target) {
		dashTarget = target;
		dashTimer = dashLength;
		dashVector = target.getPos()
				.subtract(this.getPos())
				.normalize()
				.multiply(dashSpeed);
		isDashing = true;
		if (!isHealing) {
			this.damage(DamageSource.WITHER, 1.0f);
		}
	}
	
	private boolean dashLogic(LivingEntity target) {
		if (dashTimer > 0) {
			dashTimer--;
		} else {
			return false;
		}
		
		if (target.getPos().subtract(this.getPos()).length() < specialAttackDistance) {
			this.tryAttack(target);
			this.setVelocity(target.getPos()
					.subtract(this.getPos())
					.normalize()
					.multiply(dashSpeed * -0.3));
			return false;
		}
		
		this.setVelocity(dashVector);
		return true;
	}
	
	private boolean regenLogic() {
		this.heal(1.0f);
		return this.getHealth() >= this.getMaxHealth();
	}
	
	@Override
	public void tick() {
		this.noClip = true;
		super.tick();
		this.noClip = false;
		this.setNoGravity(true);
		
		if (isHealing) {
			isHealing = regenLogic();
		} else if (drainCooldownLeft <= 0) {
			this.damage(DamageSource.WITHER, 1.0f);
			drainCooldownLeft = drainCooldown;
		}
		
		if (isDashing) {
			isDashing = dashLogic(dashTarget);
		}
		
		dashCooldownLeft--;
		drainCooldownLeft--;
	}
	
	@Override
	public void move(MovementType movementType, Vec3d movement) {
		super.move(movementType, movement);
		this.checkBlockCollision();
	}
	
	@Override
	public boolean damage(DamageSource source, float amount) {
		if (source.isExplosive() || source.isFire()) {
			return false;
		}
		if (source == DamageSource.LIGHTNING_BOLT) {
			return false;
		}
		
		return super.damage(source, amount);
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
		
		if (isDashing) {
			animations.addAnimation("animation.seeker.dash");
			event.getController().setAnimation(animations);
			return PlayState.CONTINUE;
		}
		
		animations.addAnimation("animation.seeker.idle", true);
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
