package com.github.stellarwitch7.earthguard.util;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.world.World;

public interface ICursedItem {
	void onItemEntityDestroyed(ItemEntity entity);
	default void summonLightning(ItemEntity entity) {
		World world = entity.getWorld();
		LightningEntity lightningBolt = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
		lightningBolt.setPosition(entity.getPos());
		world.spawnEntity(lightningBolt);
	}
}
