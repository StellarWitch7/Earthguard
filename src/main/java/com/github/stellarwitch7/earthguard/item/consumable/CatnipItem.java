package com.github.stellarwitch7.earthguard.item.consumable;

import com.github.stellarwitch7.earthguard.item.ModItem;
import com.github.stellarwitch7.earthguard.util.IConsumableItem;
import com.github.stellarwitch7.earthguard.util.accessor.IPlayerEntityAccessor;
import com.github.stellarwitch7.earthguard.util.LycanForm;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class CatnipItem extends ModItem implements IConsumableItem {
	public CatnipItem(Settings settings) {
		super(settings);
	}
	
	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
		if (playerEntity instanceof IPlayerEntityAccessor) {
			if (((IPlayerEntityAccessor)playerEntity).earthguard$getLycanStatus()) {
				((IPlayerEntityAccessor)playerEntity).earthguard$setLycanForm(LycanForm.MONSTER);
				playerEntity.getStackInHand(hand).decrement(1);
				return TypedActionResult.success(playerEntity.getStackInHand(hand));
			}
		}
		
		return TypedActionResult.fail(playerEntity.getStackInHand(hand));
	}
}
