package com.github.stellarwitch7.earthguard.mixin;

import com.github.stellarwitch7.earthguard.registry.ModItems;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Debug(export = true)
@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin {
	@Inject(method = "damage",
			at = @At("HEAD"), locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
	private void damage(DamageSource source, float amount,
						CallbackInfoReturnable<Boolean> info) {
		//Allows active errodium to be immune to explosives
		if (((ItemEntity)(Object)this).getStack().isOf(ModItems.ACTIVE_ERRODIUM)
				&& source.isExplosive()) {
			info.setReturnValue(false);
		}
	}
}
