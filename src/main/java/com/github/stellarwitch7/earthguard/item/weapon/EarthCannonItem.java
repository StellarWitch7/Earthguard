package com.github.stellarwitch7.earthguard.item.weapon;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.function.Predicate;

public class EarthCannonItem extends CannonItem {
	public EarthCannonItem(Settings settings) {
		super(settings);
	}
	
	@Override
	public void fireProjectile() {
	
	}
}