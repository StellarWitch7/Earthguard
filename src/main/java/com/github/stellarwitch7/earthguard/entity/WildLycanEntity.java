package com.github.stellarwitch7.earthguard.entity;

import com.github.stellarwitch7.earthguard.util.IPlayerEntityAccessor;
import net.minecraft.client.util.math.Vector3d;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Random;

public class WildLycanEntity extends HostileEntity implements IAnimatable {
	private float snarlRange = 10;
	private int snarlCooldown = 140;
	private int timeSinceLastSnarl;
	private AnimationFactory factory = new AnimationFactory(this);
	
	public WildLycanEntity(EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public static DefaultAttributeContainer.Builder setAttributes() {
		return HostileEntity.createMobAttributes()
				.add(EntityAttributes.GENERIC_MAX_HEALTH, 32.0D)
				.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 8.0f)
				.add(EntityAttributes.GENERIC_ATTACK_SPEED, 3.0f)
				.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.4f)
				.add(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, 3.0f)
				.add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 3.0f);
	}
	
	protected void initGoals() {
		this.goalSelector.add(1, new SwimGoal(this));
		this.goalSelector.add(2, new MeleeAttackGoal(this, 1.2D, false));
		this.goalSelector.add(3, new WanderAroundFarGoal(this, 0.75f, 1));
		this.goalSelector.add(4, new LookAroundGoal(this));
		
		this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
		this.targetSelector.add(2, new ActiveTargetGoal<>(this, VillagerEntity.class, true));
		this.targetSelector.add(2, new ActiveTargetGoal<>(this, MerchantEntity.class, true));
	}
	
	
	
	private PlayState predicate(AnimationEvent event) {
		var animations = new AnimationBuilder();
		
		if (this.isAttacking()) {
			animations.addAnimation("animation.wild_lycan.attack", true);
		}
		
		if (event.isMoving()) {
			animations.addAnimation("animation.wild_lycan.walk", true);
		}
		
		if (this.getPositionTargetRange() < snarlRange) {
			animations.addAnimation("animation.wild_lycan.snarl", true);
		} else {
			animations.addAnimation("animation.wild_lycan.idle", true);
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
	
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_CAT_AMBIENT;
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.ENTITY_CAT_HURT;
	}
	
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_CAT_DEATH;
	}
	
	@Override
	public ActionResult interactMob(PlayerEntity player, Hand hand) {
		return null;
	}
	
	@Override
	public void onAttacking(Entity target) {
		var random = new Random();
		boolean setLycan = random.nextBoolean();
		
		if (setLycan) {
			if (target instanceof PlayerEntity player) {
				((IPlayerEntityAccessor)player).earthguard$setLycanStatus(true);
				player.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS,
								20));
			} else {
				var knockback = new Vector3d(this.getPos().x - target.getPos().x,
						this.getPos().y - target.getPos().y,
						this.getPos().z - target.getPos().z);
				
				target.addVelocity(knockback.x, knockback.y, knockback.z);
			}
		}
		
		super.onAttacking(target);
	}
	
	@Override
	public void tick() {
		super.tick();
		
		if (getPositionTargetRange() < snarlRange && timeSinceLastSnarl > snarlCooldown) {
			this.playSound(SoundEvents.ENTITY_ENDER_DRAGON_GROWL, 1.0f, 1.0f);
			timeSinceLastSnarl = 0;
		} else {
			timeSinceLastSnarl++;
		}
	}
}
