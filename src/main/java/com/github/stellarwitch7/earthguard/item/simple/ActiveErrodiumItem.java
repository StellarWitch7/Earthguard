package com.github.stellarwitch7.earthguard.item.simple;

import com.github.stellarwitch7.earthguard.item.ModItem;
import net.minecraft.entity.ItemEntity;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class ActiveErrodiumItem extends ModItem {
	public ActiveErrodiumItem(Settings settings) {
		super(settings);
	}
	
	//Alpha, may not work
	@Override
	public void onItemEntityDestroyed(ItemEntity entity) {
		World world = entity.getWorld();
		world.createExplosion(entity,
				entity.getX(),
				entity.getY(),
				entity.getZ(),
				5, Explosion.DestructionType.NONE);
	}
}
