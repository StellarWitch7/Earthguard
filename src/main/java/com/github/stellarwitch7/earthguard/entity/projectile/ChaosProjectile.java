package com.github.stellarwitch7.earthguard.entity.projectile;

import com.github.stellarwitch7.earthguard.registry.ModEffects;
import com.github.stellarwitch7.earthguard.util.IExplosiveProjectile;
import com.github.stellarwitch7.earthguard.util.SpecialValues;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ExplosiveProjectileEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Random;

public class ChaosProjectile
		extends ExplosiveProjectileEntity
		implements IAnimatable, IExplosiveProjectile {
	private final AnimationFactory factory = new AnimationFactory(this);
	private Vec3d targetMovement = this.getPos();
	private boolean isCharged = false;
	
	public ChaosProjectile(EntityType<? extends ExplosiveProjectileEntity> entityType,
						   World world) {
		super(entityType, world);
		
		Random rand = new Random();
		
		if (rand.nextInt(0, 100) < 10) {
			this.isCharged = true;
		}
	}
	
	public void setTargetMovement(Vec3d vector) {
		this.targetMovement = vector;
	}
	
	private void summonLightning() {
		if (isCharged) {
			var bolt = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
			bolt.setPosition(this.getPos());
			world.spawnEntity(bolt);
		}
	}
	
	@Override
	public void tick() {
		if (this.getVelocity().length() < targetMovement.length() * 0.7) {
			this.summonLightning();
			this.setVelocity(targetMovement);
		}
		
		super.tick();
	}
	
	@Override
	public float getExplosiveStrength() {
		return 8.0f;
	}
	
	@Override
	public Explosion.DestructionType getDestructionType() {
		if (isCharged) {
			return Explosion.DestructionType.DESTROY;
		}
		
		return Explosion.DestructionType.NONE;
	}
	
	@Override
	protected ParticleEffect getParticleType() {
		return ParticleTypes.ELECTRIC_SPARK;
	}
	
	@Override
	public void onEntityHit(EntityHitResult entityHitResult) {
		super.onEntityHit(entityHitResult);
		
		Entity entity = entityHitResult.getEntity();
		
		if (entity instanceof PlayerEntity player) {
			player.addStatusEffect(new StatusEffectInstance(ModEffects.ATOMIC_ERROSION,
					SpecialValues.TICK_SECOND * 5));
		}
		
		this.summonLightning();
		IExplosiveProjectile.super.explode(this);
		this.kill();
	}
	
	@Override
	public void onCollision(HitResult hitResult) {
		super.onCollision(hitResult);
		
		if (!this.world.isClient) {
			this.summonLightning();
			IExplosiveProjectile.super.explode(this);
			this.kill();
		}
	}
	
	private PlayState predicate(AnimationEvent event) {
		var animations = new AnimationBuilder();
		animations.addAnimation("animation.chaos_projectile.idle", true);
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
