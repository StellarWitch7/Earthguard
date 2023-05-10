package com.github.stellarwitch7.earthguard.item.consumable;

import com.github.stellarwitch7.earthguard.item.ModItem;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class RejuvenationOrbItem extends ModItem {
	public RejuvenationOrbItem(Settings settings) {
		super(settings);
	}
	
	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
		StatusEffectInstance current = playerEntity.getStatusEffect(StatusEffects.REGENERATION);
		int newAmplifier = current != null ? current.getAmplifier() + 4 : 4;
		playerEntity.getStackInHand(hand).decrement(1);
		playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION,
				70, newAmplifier));
		playerEntity.playSound(SoundEvents.BLOCK_GLASS_BREAK, 1.0f, 1.0f);
		return TypedActionResult.success(playerEntity.getStackInHand(hand));
	}
}
