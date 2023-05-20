package com.github.stellarwitch7.earthguard.item.weapon;

import com.github.stellarwitch7.earthguard.EarthguardMod;
import com.github.stellarwitch7.earthguard.registry.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.function.Predicate;

public class CannonItem extends RangedWeaponItem {
	public static final Predicate<ItemStack> CANNON_AMMUNITION =
			stack -> stack.isOf(ModItems.ERRODIUM_VIAL);
	
	public CannonItem(Settings settings) {
		super(settings);
	}
	
	@Override
	public Predicate<ItemStack> getProjectiles() {
		return CANNON_AMMUNITION;
	}
	
	@Override
	public int getRange() {
		return 50;
	}
	
	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
		ItemStack itemStack = playerEntity.getStackInHand(hand);
		ItemStack ammo = playerEntity.getArrowType(itemStack);
		
		if (ammo.isEmpty()) {
			return TypedActionResult.fail(itemStack);
		}
		
		fireProjectile();
		ammo.decrement(1);
		return TypedActionResult.success(itemStack);
	}
	
	public void fireProjectile() {
		EarthguardMod.LOGGER.error("Cannon projectile not implemented", this);
	}
}