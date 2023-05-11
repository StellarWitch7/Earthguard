package com.github.stellarwitch7.earthguard.util;

import net.minecraft.entity.ItemEntity;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public interface IExplosiveItem {
	float getExplosiveStrength();
	Explosion.DestructionType getDestructionType();
	/**
	 *This method overrides a method from the Minecraft Item class.
	 * To make the item explode when it is destroyed, call IExplosiveItem.super.explode(entity)
	 * @param entity
	 */
	void onItemEntityDestroyed(ItemEntity entity);
	default void explode(ItemEntity entity) {
		World world = entity.getWorld();
		world.createExplosion(entity,
				entity.getX(),
				entity.getY(),
				entity.getZ(),
				getExplosiveStrength(), getDestructionType());
	}
}