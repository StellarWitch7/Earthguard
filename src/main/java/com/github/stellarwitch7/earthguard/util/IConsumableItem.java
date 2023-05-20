package com.github.stellarwitch7.earthguard.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public interface IConsumableItem {
	TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand);
}
