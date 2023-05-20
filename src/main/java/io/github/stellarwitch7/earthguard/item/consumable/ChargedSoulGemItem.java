package io.github.stellarwitch7.earthguard.item.consumable;

import io.github.stellarwitch7.earthguard.item.ModItem;
import io.github.stellarwitch7.earthguard.registry.ModEffects;
import io.github.stellarwitch7.earthguard.util.IConsumableItem;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ChargedSoulGemItem extends ModItem implements IConsumableItem {
	public ChargedSoulGemItem(Settings settings) {
		super(settings);
	}
	
	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
		if (!playerEntity.getActiveStatusEffects().containsKey(ModEffects.PEACEKEEPER)) {
			playerEntity.getStackInHand(hand).decrement(1);
			playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE,
					1000));
			playerEntity.addStatusEffect(new StatusEffectInstance(ModEffects.PEACEKEEPER,
					2000));
			playerEntity.playSound(SoundEvents.BLOCK_END_PORTAL_SPAWN, 1.0f, 1.0f);
			return TypedActionResult.success(playerEntity.getStackInHand(hand));
		} else {
			return TypedActionResult.fail(playerEntity.getStackInHand(hand));
		}
	}
}
