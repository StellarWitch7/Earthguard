package software.stellarwitch7.earthguard.item.consumable;

import software.stellarwitch7.earthguard.registry.ModEffects;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class EnchantedFertilizerItem extends Item {
	public EnchantedFertilizerItem(Settings settings) {
		super(settings);
	}
	
	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
		var client = MinecraftClient.getInstance();
		
		if (!playerEntity.getActiveStatusEffects().containsKey(ModEffects.PEACEKEEPER)
				&& client.crosshairTarget.getType().equals(HitResult.Type.BLOCK)) {
			playerEntity.getStackInHand(hand).decrement(1);
			return TypedActionResult.success(playerEntity.getStackInHand(hand));
		} else {
			return TypedActionResult.fail(playerEntity.getStackInHand(hand));
		}
	}
}