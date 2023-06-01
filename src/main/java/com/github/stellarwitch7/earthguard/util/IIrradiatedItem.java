package com.github.stellarwitch7.earthguard.util;

import com.github.stellarwitch7.earthguard.registry.ModEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IIrradiatedItem {
	void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected);
	
	default void applyRadiation(World world, Entity entity) {
		if (!world.isClient()) {
			if (entity instanceof PlayerEntity player) {
				player.addStatusEffect(new StatusEffectInstance(ModEffects.ATOMIC_ERROSION,
						120, 1));
			}
		}
	}
}
