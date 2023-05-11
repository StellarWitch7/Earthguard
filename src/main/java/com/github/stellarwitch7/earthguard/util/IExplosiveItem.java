package com.github.stellarwitch7.earthguard.util;

import net.minecraft.entity.ItemEntity;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public interface IExplosiveItem {
//	For the item to actually explode, you will need to
//	write the following method into the item's class.
//	Don't worry, the interface will require that you implement this method.
//	This will cause it to explode when the item is destroyed.
//	For other triggers, simply call explode() from another location.

//	@Override
//	public void onItemEntityDestroyed(ItemEntity entity) {
//		IExplosiveItem.super.explode(entity);
//	}
	
	float getExplosiveStrength();
	Explosion.DestructionType getDestructionType();
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