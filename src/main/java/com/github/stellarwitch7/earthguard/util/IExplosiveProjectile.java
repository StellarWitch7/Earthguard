package com.github.stellarwitch7.earthguard.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public interface IExplosiveProjectile {
	float getExplosiveStrength();
	Explosion.DestructionType getDestructionType();
	/**
	 *Overrides a method from the Minecraft ProjectileEntity class.
	 * To make the projectile explode when it hits and entity,
	 * call IExplosiveProjectile.super.explode(entity)
	 * @param entityHitResult
	 */
	void onEntityHit(EntityHitResult entityHitResult);
	/**
	 *Overrides a method from the Minecraft ProjectileEntity class.
	 * To make the projectile explode when it hits a block,
	 * call IExplosiveProjectile.super.explode(entity)
	 * @param hitResult
	 */
	void onCollision(HitResult hitResult);
	/**
	 *Creates an explosion at the location of the projectile. Call it from onCollision
	 * to make the projectile explode when it hits a block.
	 * Call it from onEntityHit to make it explode when it hits an entity.
	 * @param entity
	 */
	default void explode(Entity entity) {
		World world = entity.getWorld();
		world.createExplosion(entity,
				entity.getX(),
				entity.getY(),
				entity.getZ(),
				getExplosiveStrength(), getDestructionType());
	}
}