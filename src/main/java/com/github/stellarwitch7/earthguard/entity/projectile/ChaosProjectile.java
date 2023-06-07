package com.github.stellarwitch7.earthguard.entity.projectile;

import com.github.stellarwitch7.earthguard.registry.ModEffects;
import com.github.stellarwitch7.earthguard.util.IExplosiveProjectile;
import com.github.stellarwitch7.earthguard.util.SpecialValues;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class ChaosProjectile
		extends ProjectileEntity
		implements IAnimatable, IExplosiveProjectile {
	private final AnimationFactory factory = new AnimationFactory(this);
	private Vec3d directionVector = this.getPos();
	private double projectileSpeed = 1.0d;
	private float explosionStrength = 1.5f;
	
	public ChaosProjectile(EntityType<? extends ProjectileEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public ChaosProjectile setValues(Vec3d directionVector, double projectileSpeed,
									 float explosionStrength) {
		this.directionVector = directionVector;
		this.projectileSpeed = projectileSpeed;
		this.explosionStrength = explosionStrength;
		return this;
	}
	
	public ChaosProjectile setValues(Vec3d directionVector, double projectileSpeed) {
		this.directionVector = directionVector;
		this.projectileSpeed = projectileSpeed;
		return this;
	}
	public ChaosProjectile setValues(Vec3d directionVector) {
		this.directionVector = directionVector;
		return this;
	}
	
	@Override
	protected void initDataTracker() {
	
	}
	
	@Override
	public float getExplosiveStrength() {
		return explosionStrength;
	}
	
	@Override
	public Explosion.DestructionType getDestructionType() {
		return Explosion.DestructionType.DESTROY;
	}
	
	@Override
	public void onEntityHit(EntityHitResult entityHitResult) {
		super.onEntityHit(entityHitResult);
		
		Entity entity = entityHitResult.getEntity();
		
		if (entity instanceof PlayerEntity player) {
			player.addStatusEffect(new StatusEffectInstance(ModEffects.ATOMIC_ERROSION,
					SpecialValues.TICK_SECOND * 5));
		}
		
		IExplosiveProjectile.super.explode(this);
		this.kill();
	}
	
	@Override
	public void onCollision(HitResult hitResult) {
		super.onCollision(hitResult);
		
		if (!this.world.isClient) {
			IExplosiveProjectile.super.explode(this);
			this.kill();
		}
		
	}
	
	@Override
	public void tick() {
		this.setVelocity(directionVector.multiply(projectileSpeed));
	}
	
	@Override
	public void registerControllers(AnimationData animationData) {
	
	}
	
	@Override
	public AnimationFactory getFactory() {
		return factory;
	}
}
