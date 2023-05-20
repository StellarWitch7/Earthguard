package io.github.stellarwitch7.earthguard.item.simple;

import io.github.stellarwitch7.earthguard.item.ModItem;
import io.github.stellarwitch7.earthguard.util.IExplosiveItem;
import net.minecraft.entity.ItemEntity;
import net.minecraft.world.explosion.Explosion;

public class ActiveErrodiumItem extends ModItem implements IExplosiveItem {
	public ActiveErrodiumItem(Settings settings) {
		super(settings);
	}
	
	@Override
	public float getExplosiveStrength() {
		return 20;
	}
	
	@Override
	public Explosion.DestructionType getDestructionType() {
		return Explosion.DestructionType.BREAK;
	}
	
	@Override
	public void onItemEntityDestroyed(ItemEntity entity) {
		IExplosiveItem.super.explode(entity);
	}
}
