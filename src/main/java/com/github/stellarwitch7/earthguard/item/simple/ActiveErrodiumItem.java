package com.github.stellarwitch7.earthguard.item.simple;

import com.github.stellarwitch7.earthguard.item.ModItem;
import com.github.stellarwitch7.earthguard.util.IExplosiveItem;
import com.github.stellarwitch7.earthguard.util.IIrradiatedItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class ActiveErrodiumItem extends ModItem implements IExplosiveItem, IIrradiatedItem {
	public ActiveErrodiumItem(Settings settings) {
		super(settings);
	}
	
	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity,
							  int slot, boolean selected) {
		IIrradiatedItem.super.applyRadiation(world, entity);
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
